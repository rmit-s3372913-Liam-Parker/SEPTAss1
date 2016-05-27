package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

public class ForecastIOFactory implements ForecastFactory {

	String lat, lon;

    public static final double MPH_2_KMH = 1.609344; //TODO: Move to interface perhaps??
    public static final double KMH_2_KTS = 0.539956; // conversion macros

	public ForecastIOFactory(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public HashMap<Date, WeatherDataPoint> GetWeatherForecast()
	{
		String json = "";
		final String forecastAPIString = "https://api.forecast.io/forecast/e75c50c1bc3a622832329f007ff1ab4b/";

		HashMap<Date, WeatherDataPoint> data = new HashMap<>();

		try {
			// Scrapes from the JSON file URL that has been assigned to
			// WeatherStation
			Scanner sc = new Scanner(new URL(forecastAPIString + lat + "," + lon).openStream());
			while (sc.hasNext()) {
				json += sc.nextLine();
			}

			sc.close();
		} catch (IOException e) {
			//logger.log(Level.WARNING, "Couldn't establish a connection with weather server at " + forecastAPIString);
			return data;
		}

		JSONObject root = new JSONObject(json);
		JSONObject observations = root.getJSONObject("daily");
		JSONArray entriesArray = observations.getJSONArray("data");

		Iterator<Object> entriesItr = entriesArray.iterator();

		// Loop for all recording entries the data block contains.
        // Each element represents aggregate weather data for a single day.
        // https://developer.forecast.io/docs/v2
		while (entriesItr.hasNext()) {
			JSONObject entry = (JSONObject) entriesItr.next();

			// For each recording, grab the relevant info
			// Date parsing
			String dateString = entry.getString("daily"); // daily represents seconds to midnight in API
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);

			try {
				Date date = format.parse(dateString);

				// Temperatures and humidity
				float temp = (float) entry.optDouble(" temperatureMax");
				float appTemp = (float) entry.optDouble("apparentTemperatureMax");
				float dewPoint = (float) entry.optDouble("dewPoint");
				int relHum = entry.optInt("humidity");
				float deltaT = 0.0f;// = (float) entry.optDouble("delta_t"); TODO: figure out equiv in API

				// Wind & gust //TODO figure out enum conversion.
				CompassDirection windDir = CompassDirection.CALM; //entry.optEnum(CompassDirection.class, "wind_dir");
				int windSpeedKmh = (int)(entry.optInt("windSpeed") * MPH_2_KMH); // API returns MPH
				int gustSpeedKmh = 0;// = entry.optInt("gust_kmh");
				int windSpeedKts = (int)(windSpeedKmh * KMH_2_KTS);
				int gustSpeedKts = 0;// entry.optInt("gust_kt");

				// Pressure
                float millibarPres = (float) entry.optDouble("press_qnh");
				float pressQNH = millibarPres; // TODO conversions
				float pressMSL = millibarPres;

				// Precipitation
				float rainSinceNineAM = (float) entry.optDouble("precipAccumulation");

				// Create a snapshot entry using all the scraped data
				WeatherDataPoint snapshotEntry = new WeatherDataPoint(date, temp, appTemp, dewPoint, relHum, deltaT,
						windDir, windSpeedKmh, gustSpeedKmh, windSpeedKts, gustSpeedKts, pressQNH, pressMSL,
						rainSinceNineAM);

				// Add the entry to this station's hashmap
                data.put(date, snapshotEntry);
			} catch (ParseException e) {
				//logger.log(Level.SEVERE, "The forecast.io api has changed or the parsing code has been modified! Important!!");
				e.printStackTrace();
			}
		}

		return data;
	}
}
