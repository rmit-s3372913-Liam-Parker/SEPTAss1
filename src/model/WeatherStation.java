package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Stores all information on a specific weather station, its name and all day to day entries.
 * 
 *  @see WeatherStationDailyEntry
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
	
	HashMap<Date, WeatherStationDailyEntry> dailyEntries = new HashMap<Date, WeatherStationDailyEntry>();
	
	HashMap<Date, WeatherStationSnapshotEntry> snapshotEntries = new HashMap<Date, WeatherStationSnapshotEntry>();
		
	
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
	public WeatherStation(String name, String bomLink, HashMap<Date, WeatherStationDailyEntry> entries)
	{
		this(name, bomLink);
		if(entries != null)
			this.dailyEntries = entries;
	}
	
	/**
	 * Daily entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * @param date The date associated to the entry
	 * @param dailyEntry The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addDailyEntry(Date date, WeatherStationDailyEntry dailyEntry)
	{
		if(dailyEntries.containsKey(date) || date == null || dailyEntry == null)
			return false;
		
		dailyEntries.put(date, dailyEntry);
		return true;
	}	
	
	/**
	 * Snapshot entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * @param date The date associated to the entry
	 * @param snapshotEntry The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addSnapshotEntry(Date date, WeatherStationSnapshotEntry snapshotEntry)
	{
		if(snapshotEntries.containsKey(date) || date == null || snapshotEntry == null)
			return false;
		
		snapshotEntries.put(date, snapshotEntry);
		return true;
	}	
	
	/**
	 * Gets entries for station.
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, WeatherStationDailyEntry> getEntries()
	{
		return Collections.unmodifiableMap(dailyEntries);
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
			
			
			//For each recording, grab the relevant info
			//Date parsing
			String dateString = entry.getString("local_date_time_full");
			DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
			try
			{
				Date date = format.parse(dateString);	
			
			//Temperatures and humidity
			float temp = (float)entry.getDouble("air_temp");
			float appTemp = (float)entry.getDouble("apparent_t");
			float dewPoint = (float)entry.getDouble("dewpt");
			int relHum = entry.getInt("rel_hum");
			float deltaT = (float)entry.getDouble("delta_t");
			
			//Wind & gust
			//String windDirString = entry.getString("wind_dir");
			//CompassDirection windDir = CompassDirection.valueOf(windDirString);
			CompassDirection windDir = entry.getEnum(CompassDirection.class, "wind_dir");
			
			
			int windSpeedKmh = entry.getInt("wind_spd_kmh");
			int gustSpeedKmh = entry.getInt("gust_kmh");
			int windSpeedKts = entry.getInt("wind_spd_kt");
			int gustSpeedKts = entry.getInt("gust_kt");
			
			//Pressure
			float pressQNH = (float)entry.getDouble("press_qnh");
			float pressMSL = (float)entry.getDouble("press_msl");
			
			//Precipitation
			float rainSinceNineAM = (float)entry.getDouble("rain_trace");
			
			
			
			//Create a snapshot entry using all the scraped data
			WeatherStationSnapshotEntry snapshotEntry = new WeatherStationSnapshotEntry(date, temp, appTemp, dewPoint,
					relHum, deltaT, windDir, windSpeedKmh, gustSpeedKmh, windSpeedKts, gustSpeedKts, pressQNH, pressMSL, 
					rainSinceNineAM);
			
			
			//Add the entry to this station's hashmap
			addSnapshotEntry(date, snapshotEntry);
			
			
			} catch (ParseException e)
			{
				//Invalid date format
				e.printStackTrace();
			}
					
			
		}
		
	}
	
}
