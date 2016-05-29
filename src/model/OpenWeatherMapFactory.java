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
	public HashMap<Date, ForecastDataPoint> GetWeatherForecast() {
		
		String json = "";
		final String weathermapAPIKey = "9cc8ba2255f54825be22ccf6207290ec";
		final String unitSettings = "&units=metric";
		final String weathermapAPIString = "http://api.openweathermap.org/data/2.5/forecast?lat=" 
		+ lat + "&lon=" + lon + "&appid=" + weathermapAPIKey + unitSettings;
	
		HashMap<Date, ForecastDataPoint> data = new HashMap<>();

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
		
		System.out.println(json);

		JSONObject root = new JSONObject(json);
		JSONArray entriesArray = root.getJSONArray("list");
		
		Iterator<Object> entriesItr = entriesArray.iterator();

		// Loop for all recording entries the data block contains.
        // Entries are for every 3 hours over the next 5 days.
		while (entriesItr.hasNext()) {
			JSONObject entry = (JSONObject) entriesItr.next();

	
			//Summary
			long unixTime = entry.optLong("dt");
			Date date = new Date(unixTime*1000L);
			String summary = entry.optString("summary");

			// Temperatures and humidity
			float temp = (float)entry.optDouble("temp");
			int relHum = (int)(entry.optDouble("humidity")*100);

			// Wind & gust
			float windDir = (float)entry.optDouble("windBearing");
			float windSpeedKmh = (float)(entry.optDouble("windSpeed")* MPH_2_KMH);

			// Pressure
			float pressure = (float)entry.optDouble("pressure");
			
			ForecastDataPoint dataPoint = new ForecastDataPoint(date, summary, temp, relHum, windDir, 
					windSpeedKmh, pressure);

			// Add the entry to this station's hashmap
			data.put(date, dataPoint);
				
		}

		return data;

	}
}
