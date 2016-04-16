package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
	
	
	public void scrapeEntries()
	{

		String entriesJson = "";
	
		try
		{
			//Scrapes from the JSON file URL that has been assigned to WeatherStation
			Scanner sc = new Scanner(new URL(bomLink).openStream());
			
			while(sc.hasNext())
			{
				entriesJson += sc.nextLine();
		
			}
				
			sc.close();
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		JSONArray scrapedArray = new JSONArray(entriesJson);
		JSONArray entriesArray = scrapedArray.getJSONArray(0).getJSONArray(2);
		
		Iterator<Object> entriesItr = entriesArray.iterator();
		
		//Loop for all recording entries the station has (including those on the same day)
		while(entriesItr.hasNext())
		{
			JSONObject entry = (JSONObject)entriesItr.next();
			
			/* Code for reference; ideally we want to populate each of these variables the same way city and url are done. However the roadblock 
			 * is that the JSON does not contain all of these values and hence we must work out the best way to either calculate or scrape these
			 *
	    	String city = entry.getString("city");
	    	String url = entry.getString("url");
	    	
	    	Date date;
	    	float minTemp, maxTemp;
	    	float mmRain, mmEvap;
	    	int sun; // Bright sunlight in hours per day
	    	
	    	// Max Wind data
	    	CompassDirection dir;
	    	int spd;
	    	Date time;
			*/
			
			
			
			//For each recording, grab the relevant info
			
			
			//Then call addEntry() using the grabbed info
			
			
		}
		
	}
	
}
