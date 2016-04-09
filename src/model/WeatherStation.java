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
	String bomLink;
	
	HashMap<Date, WeatherStationEntry> entries = new HashMap<Date, WeatherStationEntry>();
	
	/**
	 * 
	 * @param name The name of the station
	 * @param bomLink The link to this stations observations on the bom site.
	 */
	public WeatherStation(String name, String bomLink)
	{
		this.name = name;
		this.bomLink = bomLink;
	}
	
	/**
	 * @param name The name of this station ie. "Laverton, Victoria"
	 * @param entries A map of entries to their dates.
	 */
	public WeatherStation(String name, String bomLink, HashMap<Date, WeatherStationEntry> entries)
	{
		this(name, bomLink);
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
