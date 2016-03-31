package model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WeatherStation 
{
	HashMap<Date, WeatherStationEntry> entries = new HashMap<Date, WeatherStationEntry>();
	
	public WeatherStation(HashMap<Date, WeatherStationEntry> entries)
	{
		this.entries = entries;
	}
	
	public Map<Date, WeatherStationEntry> GetEntries()
	{
		return Collections.unmodifiableMap(entries);
	}
}
