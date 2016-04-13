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
	 * Sources all stations available in a specified state.
	 * @param state An enum value representing the state you want stations for.
	 * @return An arrayList containing all stations belonging to the passed in state.
	 */
	ArrayList<WeatherStation> getWeatherStations(State state);
	
	/**
	 * Searches for stations with a specified sub-string in their name.
	 * @param subStr The sub-string to match against stations.
	 * @return A list of all stations with the specified string contained
	 * within their name.
	 */
	ArrayList<WeatherStation> getWeatherStations(String subStr);
	
	/**
	 * Get all weather stations currently stored internally by the weather
	 * system.
	 * @return A list containing all weather stations.
	 */
	ArrayList<WeatherStation> getWeatherStations();
}
