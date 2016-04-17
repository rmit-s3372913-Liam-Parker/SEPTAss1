package model;

import java.util.Date;

public class WeatherStationSnapshotEntry
{

	//Temperatures and humidity
	private Date date;
	private float temp;
	private float appTemp;
	private float dewPoint;
	private int relHum;
	private float deltaT;
	
	//Wind & gust
	private CompassDirection windDir;
	private int windSpeedKmh;
	private int gustSpeedKmh;
	private int windSpeedKts;
	private int gustSpeedKts;
	
	//Pressure
	private float pressQNH;
	private float pressMSL;
	
	//Precipitation
	private float rainSinceNineAM;
	
	
	
	public WeatherStationSnapshotEntry(Date date, float temp, float appTemp, float dewPoint, int relHum, float deltaT, CompassDirection windDir, int windSpeedKm, int gustSpeedKm, int windSpeedKts, int gustSpeedKts, float pressQNH, float pressMSL, float rainSinceNineAM)
	{
		
		this.date = date;
		this.temp = temp;
		this.appTemp = appTemp;
		this.dewPoint = dewPoint;
		this.relHum = relHum;
		this.deltaT = deltaT;
		
		this.windDir = windDir;
		this.windSpeedKmh = windSpeedKm;
		this.gustSpeedKmh = gustSpeedKm;
		this.windSpeedKts = windSpeedKts;
		this.gustSpeedKts = gustSpeedKts;
		
		this.pressQNH = pressQNH;
		this.pressMSL = pressMSL;
		
		this.rainSinceNineAM = rainSinceNineAM;
			
	}
	
	
	
	
	
	
}


