package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.*;

public class WeatherModelImpl implements WeatherSystem 
{
	static final String stationLinksFP = "stations.json";
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
	public WeatherStation getWeatherStation(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<WeatherStation> getAllWeatherStations() {
		// TODO Auto-generated method stub
		return null;
	}
	
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
            
		} catch (IOException e) 
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
	    System.out.println("");
	}
}
