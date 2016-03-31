package model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores all information on a specific weather station, its name and all day to day entries.
 * 
 *  @see WeatherStationEntry
 */
public class WeatherStation 
{
	String name;
	HashMap<Date, WeatherStationEntry> entries = new HashMap<Date, WeatherStationEntry>();
	
	/**
	 * 
	 * @param name The name of this station ie. "Laverton, Victoria"
	 * @param entries A map of entries to their dates.
	 */
	public WeatherStation(String name, HashMap<Date, WeatherStationEntry> entries)
	{
		if(entries != null)
			this.entries = entries;
	}
	
	/**
	 * Gets entries for station.
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, WeatherStationEntry> GetEntries()
	{
		return Collections.unmodifiableMap(entries);
	}
}
