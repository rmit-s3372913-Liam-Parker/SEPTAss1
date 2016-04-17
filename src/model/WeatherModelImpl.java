package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.*;

import view.IWeatherSystemCallback;

public class WeatherModelImpl implements WeatherSystem 
{
	static final String stationLinksFP = "stations.json";
	
	/**
	 * Just to make it clear, this hashmap stores australian
	 * states as a key and a hashmap of weatherstations as a value.
	 * The weather stations hashmap uses the town name as a key and the actual weather
	 * station object itself as a value.
	 */
	private HashMap<String, HashMap<String, WeatherStation>> stations =
			new HashMap<String, HashMap<String, WeatherStation>>();
	
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
	
	@Override
	public void refreshFavoriteWeatherData() 
	{
		for(WeatherStation station : favorites)
		{
			station.scrapeEntries();		
		}
				
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
		for(String curState : stations.keySet())
		{
			if(curState == state.getString())
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
			System.out.println("ERROR - Couldn't read stations.json, check the file exists.");
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
		    	
		    	stationList.put(city, new WeatherStation(city, url));
		    }
	    	
	    	stations.put(stateName, stationList);
	    }
	}
	
	/**
	 * Loads user selected favorite stations from disk ready
	 * to be refreshed.
	 */
	private void LoadFavoritesFromDisk()
	{
		//TODO
	}
	
	/**
	 * Saves user selected favorite stations to disk
	 * ready to be loaded on next application start.
	 */
	private void SaveFavoritesToDisk()
	{
		//TODO
		
		//try 
    	//{
		//	PrintWriter writer = new PrintWriter("WindowStates.json");
		//	writer.print(windowArray.toString());
		//	writer.close();
		//} 
    	//catch (FileNotFoundException e1){ }
	}
	
	@Override
	public boolean addFavoriteStation(String name) 
	{
		if(getFavoriteStation(name) != null)
			return false;
		
		for(String curState : stations.keySet())
		{
			for(WeatherStation station : stations.get(curState).values())
			{
				if(station.getName() == name)
				{
					favorites.add(station);
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
	
	private void invokeCallbacks()
	{
		for(IWeatherSystemCallback cb : cbList)
		{
			cb.Refresh();
		}
	}

	@Override
	public ArrayList<WeatherStation> getWeatherStations(State state, String subStr) 
	{
		ArrayList<WeatherStation> s = new ArrayList<WeatherStation>();
		
		for(String curState : stations.keySet())
		{
			if(curState == state.getString())
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
