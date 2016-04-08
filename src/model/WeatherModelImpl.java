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
        String filePath = "stations.json";
        String line = null;
        
		try 
		{
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while((line = bufferedReader.readLine()) != null) 
            {
                System.out.println(line);
            }   
            
            bufferedReader.close();
            
		} catch (IOException e) 
		{
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
