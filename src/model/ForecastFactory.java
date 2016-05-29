package model;

import java.util.Date;
import java.util.HashMap;

public abstract class ForecastFactory {
	
	String lat, lon;
	
	Date date;
	String summary;
	float temp;
	int relHum;
	int windDir;
	float windSpeedKmh;
	float pressure;
	
	public ForecastFactory(String lat, String lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	
	HashMap<Date, ForecastDataPoint> GetWeatherForecast()
	{
		return null;
	}

	double MPH_2_KMH = 1.609344; //TODO: Move to interface perhaps??
	double KMH_2_KTS = 0.539956; // conversion macros
}
