package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WeatherModelImpl implements WeatherSystem 
{
	
	
	@Override
	public void refreshWeatherData() 
	{
		//Read stations
        String fileName = "stations.json";
        String line = null;
        
		try 
		{
			// FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("stations.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: Parse station links
		
		//TODO: Load individual stations into WeatherStation object
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

}
