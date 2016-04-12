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
	private String name;
	private String bomLink;
	/**
	 * True if this station has been selected as a favorite
	 * by the user. False otherwise.
	 */
	public boolean isFavorite = false;
	
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
	 * Entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * @param date The date associated to the entry
	 * @param entry The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addEntry(Date date, WeatherStationEntry entry)
	{
		if(entries.containsKey(date) || date == null || entry == null)
			return false;
		
		entries.put(date, entry);
		return true;
	}
	
	/**
	 * Gets entries for station.
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, WeatherStationEntry> getEntries()
	{
		return Collections.unmodifiableMap(entries);
	}
	
	/**
	 * Returns the name and
	 * @return
	 */
	public String getName() 
	{
		return name;
	}
	
	public String getBomLink()
	{
		return bomLink;
	}
}
