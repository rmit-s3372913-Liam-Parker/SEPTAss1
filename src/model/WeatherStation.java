package model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores all information on a specific weather station, its name and all day to
 * day entries.
 */
public class WeatherStation {
	private static Logger logger = Logger.getLogger("Weather Stations");

	/**
	 * The name of the station.
	 */
	private String name;

	/**
	 * The link to this stations weather observations on the bom website.
	 */
	private String bomLink;

	/**
	 * Latitude of station can be useful for making calls to APIs other than BOM
	 */
	private String lat = "";
	/**
	 * Longtitude of station can be useful for making calls to APIs other than
	 * BOM
	 */
	private String lon = "";

	/**
	 * Used to signify which source this station will draw from. To be set
	 * statically from the relevant controller.
	 */
	public static ForecastSource forecastSource = ForecastSource.FORECAST_IO;

	/**
	 * List of historical weather data for this station
	 */
	Map<Date, WeatherDataPoint> historicalDataPoints = new TreeMap<Date, WeatherDataPoint>();

	/**
	 * List of forecast weather data for this station
	 */
	Map<Date, ForecastDataPoint> forecastDataPoints = new TreeMap<Date, ForecastDataPoint>();

	/**
	 * 
	 * @param name
	 *            The name of the station
	 * @param bomLink
	 *            The link to this stations observations on the bom site.
	 */
	public WeatherStation(String name, String bomLink) {
		this.name = name;
		this.bomLink = bomLink;
	}

	/**
	 * Snapshot entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * 
	 * @param date
	 *            The date associated to the entry
	 * @param snapshotEntry
	 *            The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addHistoricalDataPoint(Date date, WeatherDataPoint snapshotEntry) {
		if (historicalDataPoints.containsKey(date) || date == null || snapshotEntry == null)
			return false;

		historicalDataPoints.put(date, snapshotEntry);
		return true;
	}

	/**
	 * Datapoint entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * 
	 * @param date
	 *            The date associated to the entry
	 * @param snapshotEntry
	 *            The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addForecastDataPoint(Date date, ForecastDataPoint snapshotEntry) {
		if (forecastDataPoints.containsKey(date) || date == null || snapshotEntry == null)
			return false;

		forecastDataPoints.put(date, snapshotEntry);
		return true;
	}

	/**
	 * Gets historical entries for station.
	 * 
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, WeatherDataPoint> getHistoricalDataPoints() {
		return Collections.unmodifiableMap(historicalDataPoints);
	}

	/**
	 * Gets forecast entries for station.
	 * 
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, ForecastDataPoint> getForecastDataPoints() {
		return Collections.unmodifiableMap(forecastDataPoints);
	}

	/**
	 * @return The name of the station
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The bom link to the station's online data.
	 */
	public String getBomLink() {
		return bomLink;
	}



	/**
	 * Scrapes forecast and historical data from the bomlink &
	 * forecast.io/openweathermap and stores them internally.
	 */
	public synchronized void scrapeEntries() {
		logger.entering("WeatherStation", "scrapeEntries");
		logger.log(Level.INFO, "Scraping " + name + " entries on new thread.");

		scrapeHistoricalData();

		ForecastFactory factory;
		switch (forecastSource) {
		case FORECAST_IO:
			factory = new ForecastIOFactory(lat, lon);
			break;
		case OPEN_WEATHER_MAP:
			factory = new OpenWeatherMapFactory(lat, lon);
			break;
		default:
			factory = new ForecastIOFactory(lat, lon);
			break;
		}

		forecastDataPoints = factory.GetWeatherForecast();
	}

	/**
	 * Pulls historical data from the BOM
	 */
	private void scrapeHistoricalData() {
		String entriesJson = "";

		try {
			// Scrapes from the JSON file URL that has been assigned to
			// WeatherStation
			Scanner sc = new Scanner(new URL(bomLink).openStream());
			while (sc.hasNext()) {
				entriesJson += sc.nextLine();
			}

			sc.close();
		} catch (IOException e) {
			logger.log(Level.WARNING, "Couldn't establish a connection with weather server at " + bomLink);
			// TODO: Find a better way to display error to user. This function is called per station.
			JOptionPane.showMessageDialog(null, "ERROR: Could not establish connection!", "Connection Failure",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JSONObject root = new JSONObject(entriesJson);
		JSONObject observations = root.getJSONObject("observations");
		JSONArray entriesArray = observations.getJSONArray("data");

		Iterator<Object> entriesItr = entriesArray.iterator();

		// Loop for all recording entries the station has (including those on
		// the same day)
		while (entriesItr.hasNext()) {
			JSONObject entry = (JSONObject) entriesItr.next();

			// For each recording, grab the relevant info
			// Date parsing
			String dateString = entry.getString("local_date_time_full");
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);

			try {
				Date date = format.parse(dateString);

				lat = entry.optString("lat"); // Latitude and longitude are stored in each data point
				lon = entry.optString("lon");

				// Temperatures and humidity
				float temp = (float) entry.optDouble("air_temp");
				float appTemp = (float) entry.optDouble("apparent_t");
				float dewPoint = (float) entry.optDouble("dewpt");
				int relHum = entry.optInt("rel_hum");
				float deltaT = (float) entry.optDouble("delta_t");

				// Wind & gust
				CompassDirection windDir = entry.optEnum(CompassDirection.class, "wind_dir");
				int windSpeedKmh = entry.optInt("wind_spd_kmh");
				int gustSpeedKmh = entry.optInt("gust_kmh");
				int windSpeedKts = entry.optInt("wind_spd_kt");
				int gustSpeedKts = entry.optInt("gust_kt");

				// Pressure
				float pressQNH = (float) entry.optDouble("press_qnh");
				float pressMSL = (float) entry.optDouble("press_msl");

				// Precipitation
				float rainSinceNineAM = (float) entry.optDouble("rain_trace");

				// Create a snapshot entry using all the scraped data
				WeatherDataPoint snapshotEntry = new WeatherDataPoint(date, temp, appTemp, dewPoint, relHum, deltaT,
						windDir, windSpeedKmh, gustSpeedKmh, windSpeedKts, gustSpeedKts, pressQNH, pressMSL,
						rainSinceNineAM);

				// Add the entry to this station's hashmap
				addHistoricalDataPoint(date, snapshotEntry);
			} catch (ParseException e) {
				logger.log(Level.SEVERE, "The BOM api has changed or the parsing code has been modified! Important!!");
				e.printStackTrace();
			}
		}
	}

}
