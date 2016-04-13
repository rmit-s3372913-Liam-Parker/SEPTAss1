package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.json.*;

public class WeatherModelImpl implements WeatherSystem 
{
	static final String stationLinksFP = "stations.json";
	
	/**
	 * Just to make it clear, this hashmap stores asutralian
	 * states as a key and a hashmap of weatherstations as a value.
	 * The weather stations hashmap uses the town name as a key and the actual weather
	 * station object itself as a value.
	 */
	HashMap<String, HashMap<String, WeatherStation>> stations =
			new HashMap<String, HashMap<String, WeatherStation>>();
	
	/**
	 * Constructs a new weatherModel. Will attempt to instantiate
	 * the stations data with stations.json data.
	 */
	public WeatherModelImpl()
	{
		PopulateStations(stationLinksFP);
	}
	
	@Override
	public void refreshWeatherData() 
	{
		
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
}
