package model;

import java.util.Date;
import java.util.HashMap;

public interface ForecastFactory {
	HashMap<Date, WeatherDataPoint> GetWeatherForecast();

	double MPH_2_KMH = 1.609344; //TODO: Move to interface perhaps??
	double KMH_2_KTS = 0.539956; // conversion macros
}
