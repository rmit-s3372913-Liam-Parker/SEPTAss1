package model;

import java.util.ArrayList;
import java.util.List;

import view.IWeatherSystemCallback;

public interface WeatherSystem 
{
	/**
	 * A request to the system to download latest
	 * weather information for all favourited stations.
	 * NOTE: This should probably be thread safe if we decide to
	 * go for the thread safety challenge.
	 */
	void refreshFavoriteWeatherData();
	
	/**
	 * Adds a station to the favourites list by name.
	 * @param name of the station to add to favorites.
	 */
	boolean addFavoriteStation(String name);
	
	/**
	 * Removes a station from the favourites list by name.
	 * @param name of the station to remove from favorites.
	 */
	boolean removeFavoriteStation(String name);
	
	/**
	 * Gets favorite station.
	 * @return A list of favorite stations NOTE: This list is unmodifiable, attempts
	 * to modify the data will result in an exception being thrown.
	 */
	List<WeatherStation> getFavoriteStations();
	
	/**
	 * Gets favorite station.
	 * @return A favorite station or null if it doesn't
	 * exist
	 * @param name The name of the station.
	 */
	WeatherStation getFavoriteStation(String name);
	
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
	
	/**
	 * Registers a refreshable interface with the system. Each time a change is made
	 * the system will call refresh on the views.
	 * @param cb The interface to call refresh on.
	 */
	void registerRefreshableCallback(IWeatherSystemCallback cb);
}
