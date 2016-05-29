package model;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenWeatherMapFactory implements ForecastFactory {
	
	String lat, lon;

	public OpenWeatherMapFactory(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public HashMap<Date, WeatherDataPoint> GetWeatherForecast() {
		
		String json = "";
		final String weathermapAPIKey = "9cc8ba2255f54825be22ccf6207290ec";
		final String unitSettings = "&units=metric";
		final String weathermapAPIString = "http://api.openweathermap.org/data/2.5/forecast?lat=" 
		+ lat + "&lon=" + lon + "&appid=" + weathermapAPIKey + unitSettings;
	
		HashMap<Date, WeatherDataPoint> data = new HashMap<>();

		try {
			// Scrapes from the JSON file URL that has been assigned to
			// WeatherStation
			Scanner sc = new Scanner(new URL(weathermapAPIString).openStream());
			while (sc.hasNext()) {
				json += sc.nextLine();
			}

			sc.close();
		} catch (IOException e) {
			//logger.log(Level.WARNING, "Couldn't establish a connection with weather server at " + forecastAPIString);
			return data;
		}
		

		/*JSONObject root = new JSONObject(json);
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
			long unixTime = entry.getLong("time"); // daily represents seconds to midnight in API

			Date date = new Date(unixTime*1000L);

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
		}

		return data;*/
		
		
		
		
		
		return null;
	}
}
