package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import interfaces.IWeatherSystemCallback;
import interfaces.WeatherSystem;

/**
 * Concrete implementation of WeatherSystem, provides most
 * functionality for program. Can take callback objects for
 * notifications on change in data.
 * @author Liam, Michael
 */
public class WeatherModelImpl implements WeatherSystem 
{
	private static final String stationLinksFP = "stations.json";
	public static Logger logger = Logger.getLogger("");
	
	/**
	 * Just to make it clear, this hashmap stores australian
	 * states as a key and a hashmap of weatherstations as a value.
	 * The weather stations hashmap uses the town name as a key and the actual weather
	 * station object itself as a value.
	 */
	private HashMap<State, HashMap<String, WeatherStation>> stations =
			new HashMap<State, HashMap<String, WeatherStation>>();
	
	/**
	 * List of all favorited weather stations for easy access.
	 */
	private ArrayList<WeatherStation> favorites = new ArrayList<WeatherStation>();
	
	private List<IWeatherSystemCallback> cbList = new ArrayList<IWeatherSystemCallback>();
	
	/**
	 * Constructs a new weatherModel. Will attempt to instantiate
	 * the stations data with stations.json data.
	 */
	public WeatherModelImpl()
	{
		PopulateStations(stationLinksFP);
		
	}
	
	public WeatherModelImpl(String stationJson)
	{
		PopulateStations(stationJson);
	}
	
	@Override
	public void refreshFavoriteWeatherData() 
	{
		logger.entering("WeatherModelImpl", "refreshWeatherData()");
		Runnable thread = new Runnable()
		{
			
			@Override
			public void run() 
			{
				for(WeatherStation station : favorites)
				{
					station.scrapeEntries();		
				}
				
				invokeCallbacks();
			}
		};
		thread.run();
		
	}

	@Override
	public WeatherStation getWeatherStation(String name) 
	{
		
		WeatherStation foundStation = null;
		for(HashMap<String, WeatherStation> stationList : stations.values())
		{
			for(WeatherStation station : stationList.values())
			{
				if(station.getName() == name)
				{
					foundStation = station;
					break;
				}
			}
		}
		return foundStation;
	}
	
	@Override
	public ArrayList<WeatherStation> getWeatherStations(State state) 
	{
		ArrayList<WeatherStation> allStations = new ArrayList<WeatherStation>();
		for(State curState : stations.keySet())
		{
			if(curState == state)
			{
				allStations.addAll(stations.get(curState).values());
			}
		}
		return allStations;
	}

	@Override
	public ArrayList<WeatherStation> getWeatherStations(String subStr) 
	{
		ArrayList<WeatherStation> allStations = new ArrayList<WeatherStation>();
		for(HashMap<String, WeatherStation> stationList : stations.values())
		{
			// We add all stations from each state in the map.
			for(WeatherStation station : stationList.values())
			{
				if(station.getName().contains(subStr))
					allStations.add(station);
			}
		}
		return allStations;
	}
	
	@Override
	public ArrayList<WeatherStation> getWeatherStations() 
	{
		ArrayList<WeatherStation> allStations = new ArrayList<WeatherStation>();
		for(HashMap<String, WeatherStation> stationList : stations.values())
		{
			// We add all stations from each state in the map.
			for(WeatherStation station : stationList.values())
				allStations.add(station);
		}
		return allStations;
	}
	
	/**
	 * Populates the stations hashmap with data from the json file
	 * passed in. The expected format is an array of state objects each containing
	 * a number of stations.
	 * 
	 * @param stationFilePath The filepath to the json file
	 */
	private void PopulateStations(String stationFilePath)
	{
		logger.log(Level.INFO, "Populating stations with " + stationFilePath);
		
		//Read stations
        String stationsJson = "";
        
		try 
		{
            FileReader reader = new FileReader(stationFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            
            while((line = bufferedReader.readLine()) != null) 
            {
                stationsJson += line;
            }   
            
            bufferedReader.close();
            
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null,
				    "stations.json couldn't be found, check your"
				    + " installation. Terminating program!",
				    "CRITICAL ERROR",
				    JOptionPane.ERROR_MESSAGE);
		}
		
		//TODO: Needs cleaning up, loads of obscure variable names.
		JSONTokener tokener = new JSONTokener(stationsJson);
		JSONArray stateArray = new JSONArray(tokener);
		
		//Iterate over each state within file.
		Iterator<Object> stateItr = stateArray.iterator();
	    while(stateItr.hasNext()) 
	    {
	    	JSONObject state = (JSONObject)stateItr.next();
	    	
	    	String stateName = state.getString("state");
	    	
	    	//Iterate over each station of a state
	    	JSONArray stationJsonArray = state.getJSONArray("stations");
	    	HashMap<String, WeatherStation> stationList = new HashMap<String, WeatherStation>();
	    	Iterator<Object> stationItr = stationJsonArray.iterator();
		    while(stationItr.hasNext())
		    {
		    	JSONObject station = (JSONObject)stationItr.next();
		    	String city = station.getString("city");
		    	String url = station.getString("url");
		    	
		    	logger.log(Level.INFO, "Loading: " + stateName + " " + city);
		    	stationList.put(city, new WeatherStation(city, url));
		    }
	    	stations.put(State.fromString(stateName), stationList);
	    }
	}
	
	/**
	 * Loads user selected favorite stations from disk ready
	 * to be refreshed.
	 */
	private void LoadFavoritesFromDisk()
	{
		logger.log(Level.WARNING, "Not Implemented");
	}
	
	/**
	 * Saves user selected favorite stations to disk
	 * ready to be loaded on next application start.
	 */
	private void SaveFavoritesToDisk()
	{
		logger.log(Level.WARNING, "Not Implemented");
	}
	
	@Override
	public boolean addFavoriteStation(String name) 
	{
		if(getFavoriteStation(name) != null)
			return false;
		
		for(State curState : stations.keySet())
		{
			for(WeatherStation station : stations.get(curState).values())
			{
				if(station.getName() == name)
				{
					favorites.add(station);
					refreshFavoriteWeatherData();
					invokeCallbacks();
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean removeFavoriteStation(String name) 
	{
		for(WeatherStation station : favorites)
		{
			if(station.getName() == name)
			{
				favorites.remove(station);
				refreshFavoriteWeatherData();
				invokeCallbacks();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<WeatherStation> getFavoriteStations() 
	{	
		return new ArrayList<WeatherStation>(Collections.unmodifiableList(favorites));
	}

	@Override
	public WeatherStation getFavoriteStation(String name)
	{
		WeatherStation station = null;
		
		for(WeatherStation s : favorites)
		{
			if(s.getName() == name)
				station = s;
		}
		
		return station;
	}

	@Override
	public void registerRefreshableCallback(IWeatherSystemCallback cb) 
	{
		cbList.add(cb);
	}
	
	/**
	 * Iterates over the callback list invoking
	 * Refresh() on each.
	 */
	private void invokeCallbacks()
	{
		logger.log(Level.INFO, "Invoking Callbacks");
		// We close the callback array because during refresh objects may register themselves
		// thus modifying the length of the callbackList before refresh is complete.
		List<IWeatherSystemCallback> clone = new ArrayList<IWeatherSystemCallback>(cbList);
		
		for(IWeatherSystemCallback cb : clone)
		{
			cb.Refresh();
		}
	}

	@Override
	public ArrayList<WeatherStation> getWeatherStations(State state, String subStr) 
	{
		ArrayList<WeatherStation> s = new ArrayList<WeatherStation>();
		
		for(State curState : stations.keySet())
		{
			if(curState == state)
			{
				for(WeatherStation station : stations.get(curState).values())
				{
					if(station.getName().contains(subStr))
						s.add(station);
				}
				return s;
			}
		}
		return s;
	}
}
