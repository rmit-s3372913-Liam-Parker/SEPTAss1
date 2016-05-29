package model;

import java.util.Date;
import java.util.HashMap;

public abstract class ForecastFactory {
	
	String lat, lon;
	
	protected Date date;
	protected String summary;
	protected float temp;
	protected int relHum;
	protected int windDir;
	protected float windSpeedKmh;
	protected float pressure;
	
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
