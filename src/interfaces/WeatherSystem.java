package interfaces;

import java.util.ArrayList;
import java.util.List;

import model.State;
import model.WeatherStation;

public interface WeatherSystem {
	/**
	 * A request to the system to download latest weather information for all
	 * favourited stations. NOTE: This should probably be thread safe if we
	 * decide to go for the thread safety challenge.
	 */
	void refreshFavoriteWeatherData();

	/**
	 * Adds a station to the favourites list by name.
	 * 
	 * @param name
	 *            of the station to add to favorites.
	 * @return True if the system managed to add the favourite, false otherwise.
	 */
	boolean addFavoriteStation(String name);

	/**
	 * Removes a station from the favourites list by name.
	 * 
	 * @param name
	 *            of the station to remove from favorites.
	 * @return True if the system managed to remove the favourite, false
	 *         otherwise.
	 */
	boolean removeFavoriteStation(String name);

	/**
	 * Gets favorite station.
	 * 
	 * @return A list of favorite stations NOTE: This list is unmodifiable,
	 *         attempts to modify the data will result in an exception being
	 *         thrown.
	 */
	List<WeatherStation> getFavoriteStations();

	/**
	 * Gets favorite station.
	 * 
	 * @return A favorite station or null if it doesn't exist
	 * @param name
	 *            The name of the station.
	 */
	WeatherStation getFavoriteStation(String name);

	/**
	 * Gets data for the first weather station with name found.
	 * 
	 * @param name
	 *            the string name for the station ie. "Laverton, Victoria"
	 * @return The weather station object requested if available, null
	 *         otherwise.
	 */
	WeatherStation getWeatherStation(String name);

	/**
	 * Gets a weather station froma specific state and containing a substring in
	 * its name.
	 * 
	 * @param state
	 *            The state to search for the station
	 * @param subStr
	 *            The substring to match against the station name
	 * @return A List of stations matching the search
	 */
	ArrayList<WeatherStation> getWeatherStations(State state, String subStr);

	/**
	 * Sources all stations available in a specified state.
	 * 
	 * @param state
	 *            An enum value representing the state you want stations for.
	 * @return An arrayList containing all stations belonging to the passed in
	 *         state.
	 */
	ArrayList<WeatherStation> getWeatherStations(State state);

	/**
	 * Searches for stations with a specified sub-string in their name.
	 * 
	 * @param subStr
	 *            The sub-string to match against stations.
	 * @return A list of all stations with the specified string contained within
	 *         their name.
	 */
	ArrayList<WeatherStation> getWeatherStations(String subStr);

	/**
	 * Get all weather stations currently stored internally by the weather
	 * system.
	 * 
	 * @return A list containing all weather stations.
	 */
	ArrayList<WeatherStation> getWeatherStations();

	/**
	 * Registers a refreshable interface with the system. Each time a change is
	 * made the system will call refresh on the views.
	 * 
	 * @param cb
	 *            The interface to call refresh on.
	 */
	void registerRefreshableCallback(IWeatherSystemCallback cb);
	
	/**
	 * Unregisters a refreshable interface with the system.
	 * 
	 * @param cb
	 *            The interface to remove if it exists.
	 */
	void unregisterRefreshableCallback(IWeatherSystemCallback cb);
}
