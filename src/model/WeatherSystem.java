package model;

import java.util.ArrayList;

public interface WeatherSystem 
{
	/**
	 * A request to the system to download latest
	 * weather information for all favorited stations.
	 * NOTE: This should probably be thread safe if we decide to
	 * go for the thread safety challenge.
	 */
	void refreshWeatherData();
	
	/**
	 * Gets data for a weather station.
	 * @param name the string name for the station ie. "Laverton, Victoria"
	 */
	WeatherStation getWeatherStation(String name);
	
	/**
	 * Get all weather stations currently stored internally by the weather
	 * system.
	 * @return A list containing all weather stations.
	 */
	ArrayList<WeatherStation> getAllWeatherStations();
}
