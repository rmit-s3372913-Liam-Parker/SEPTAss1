package model;

import java.util.Date;
import java.util.HashMap;

public interface ForecastFactory 
{
	HashMap<Date, WeatherDataPoint> GetWeatherForecast();
}
