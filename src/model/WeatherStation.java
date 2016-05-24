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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Stores all information on a specific weather station, its name and all day to day entries.
 * 
 *  @see WeatherStationDailyEntry
 */
public class WeatherStation 
{
	private static Logger logger = Logger.getLogger("Weather Stations");
	
	/**
	 * The name of the station.
	 */
	private String name;
	
	/**
	 * The link to this stations weather observations
	 * on the bom website.
	 */
	private String bomLink;
	
	/**
	 * Latitude of station can be useful for making calls to APIs other than BOM
	 */
	private String lat;
	/**
	 * Longtitude of station can be useful for making calls to APIs other than BOM
	 */
	private String lon;
	
	/**
	 * True if this station has been selected as a favorite
	 * by the user. False otherwise.
	 */
	public boolean isFavorite = false;
	
	/**
	 * 
	 */
	HashMap<Date, WeatherDataPoint> historicalDataPoints = new HashMap<Date, WeatherDataPoint>();
	HashMap<Date, WeatherDataPoint> forecastDataPoints = new HashMap<Date, WeatherDataPoint>();	
	
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
	 * Snapshot entries can be added to a station provided the station doesn't
	 * already have an entry for the provided date.
	 * @param date The date associated to the entry
	 * @param snapshotEntry The entry to add into this stations list.
	 * @return Returns true if the entry was added, false otherwise.
	 */
	public boolean addSnapshotEntry(Date date, WeatherDataPoint snapshotEntry)
	{
		if(historicalDataPoints.containsKey(date) || date == null || snapshotEntry == null)
			return false;
		
		historicalDataPoints.put(date, snapshotEntry);
		return true;
	}	
	
	/**
	 * Gets entries for station.
	 * @return An unmodifiable map of entries for this station.
	 */
	public Map<Date, WeatherDataPoint> getSnapshots()
	{
		return Collections.unmodifiableMap(historicalDataPoints);
	}
	
	/**
	 * @return The name of the station
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * @return The bom link to the station's online data.
	 */
	public String getBomLink()
	{
		return bomLink;
	}
	
	/**
	 * Scrapes weather snapshots from the bomlink and stores them internally.
	 */
	public void scrapeEntries()
	{
		logger.entering("WeatherStation", "scrapeEntries");
		logger.log(Level.INFO, "Scraping " + name + " entries on new thread.");
		Thread thread = new Thread( new Runnable()
		{
			@Override
			public void run() 
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
				}
				catch (IOException e)
				{
					logger.log(Level.WARNING, "Couldn't establish a connection with weather server at " + bomLink);
					JOptionPane.showMessageDialog(null, "ERROR: Could not establish connection!",
							"Connection Failure", JOptionPane.ERROR_MESSAGE);
				}
			
				//JSONTokener tokener = new JSONTokener(entriesJson);
				JSONObject root = new JSONObject(entriesJson);		
				JSONObject observations = root.getJSONObject("observations");
				JSONArray entriesArray = observations.getJSONArray("data");
				
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
						float temp = (float)entry.optDouble("air_temp");
						float appTemp = (float)entry.optDouble("apparent_t");
						float dewPoint = (float)entry.optDouble("dewpt");
						int relHum = entry.optInt("rel_hum");
						float deltaT = (float)entry.optDouble("delta_t");
					
						//Wind & gust
						CompassDirection windDir = entry.optEnum(CompassDirection.class, "wind_dir");		
						int windSpeedKmh = entry.optInt("wind_spd_kmh");
						int gustSpeedKmh = entry.optInt("gust_kmh");
						int windSpeedKts = entry.optInt("wind_spd_kt");
						int gustSpeedKts = entry.optInt("gust_kt");
					
						//Pressure
						float pressQNH = (float)entry.optDouble("press_qnh");
						float pressMSL = (float)entry.optDouble("press_msl");
					
						//Precipitation
						float rainSinceNineAM = (float)entry.optDouble("rain_trace");
					
						//Create a snapshot entry using all the scraped data
						WeatherDataPoint snapshotEntry = new WeatherDataPoint(date, temp, appTemp, dewPoint,
							relHum, deltaT, windDir, windSpeedKmh, gustSpeedKmh, windSpeedKts, gustSpeedKts, pressQNH, pressMSL, 
							rainSinceNineAM);
					
						//Add the entry to this station's hashmap
						addSnapshotEntry(date, snapshotEntry);
					} 
					catch (ParseException e)
					{
						logger.log(Level.SEVERE, "The BOM api has changed or the parsing code has been modified! Important!!");
						e.printStackTrace();
					}
				}
				
			}
			
		});
		thread.start();
	}
	
}
