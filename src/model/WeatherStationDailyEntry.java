package model;

import java.util.Date;

/**
 * Weather Stations store an entry for each day of the month with data
 * related to the climate on that particular day.
 * 
 * TODO: Getters for data.
 * TODO: Decide how to store Time for time variable.
 */
public class WeatherStationDailyEntry 
{
	private Date date;
	private float minTemp, maxTemp;
	private float mmRain, mmEvap;
	private int sun; // Bright sunlight in hours per day
	
	// Max Wind data
	private CompassDirection dir;
	private int spd;
	private Date time;
	
	private ClimateTimeSample nineAMSample, threePMSample;
	
	public WeatherStationDailyEntry(Date date, float minTemp, float maxTemp,
			float mmRain, float mmEvap, int sun, CompassDirection dir, int spd, Date time)
	{
		this.date = date;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.mmRain = mmRain;
		this.mmEvap = mmEvap;
		this.sun = sun;
		
		this.dir = dir;
		this.spd = spd;
		this.time = time;
	}
	
	/**
	 * Get the 9AM time sample for this entry.
	 * @return A ClimateTimeSample representing the sample.
	 * @see ClimateTimeSample
	 */
	public ClimateTimeSample GetNineAMTimeSample()
	{
		return nineAMSample;
	}
	
	/**
	 * Get the 3PM time sample for this entry.
	 * @return A ClimateTimeSample representing the sample.
	 * @see ClimateTimeSample
	 */
	public ClimateTimeSample GetThreePMTimeSample()
	{
		return threePMSample;
	}
	
	/**
	 * Sample data is taken twice a day by BOM. At 9AM and again at 3PM.
	 * We store these samples if available
	 * 
	 * @param nineAM The nine AM climate sample data for this station.
	 * @param threePM The three PM climate sample data for this station.
	 * @see ClimateTimeSample
	 */
	public void SetTimeSamples(ClimateTimeSample nineAM, ClimateTimeSample threePM)
	{
		nineAMSample = nineAM;
		threePMSample = threePM;
	}
}
