package model;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenWeatherMapFactory extends ForecastFactory {
	
	public OpenWeatherMapFactory(String lat, String lon) {
		super(lat, lon);
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
		
		JSONObject root = new JSONObject(json);
		JSONArray entriesArray = root.getJSONArray("list");
		
		Iterator<Object> entriesItr = entriesArray.iterator();

		// Loop for all recording entries the data block contains.
        // Entries are for every 3 hours over the next 5 days.
		while (entriesItr.hasNext()) {
			JSONObject entry = (JSONObject) entriesItr.next();

	
			//Summary
			long unixTime = entry.optLong("dt");
			date = new Date(unixTime*1000L);
			summary = entry.getJSONArray("weather").getJSONObject(0).optString("main");
			
			// Temperatures and humidity
			JSONObject main = entry.getJSONObject("main");
			temp = (float)main.optDouble("temp");
			relHum = (main.optInt("humidity"));

			// Wind & gust
			JSONObject wind = entry.getJSONObject("wind");
			windDir = (int)wind.optDouble("deg");
			windSpeedKmh = (float)(wind.optDouble("speed"));

			// Pressure
			pressure = (float)main.optDouble("pressure");
			
			ForecastDataPoint dataPoint = new ForecastDataPoint(date, summary, temp, relHum, windDir, 
					windSpeedKmh, pressure);

			// Add the entry to this station's hashmap
			data.put(date, dataPoint);
				
		}

		return data;

	}
}
