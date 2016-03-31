package model;

/**
 * Represents sample data from a specific point in time during the day. Measures
 * by a weather station.
 * @author Liam
 */
public class ClimateTimeSample 
{
	// Variables are named as they appear in raw bom data.
	private float temp;
	private int rh; // Relative humidity
	private int cld; // Fraction of sky obscured
	private CompassDirection dir;
	private int spd; // Wind speed
	private float mlsp; // Atmospheric pressure
	
	public ClimateTimeSample(float temp, int rh, int cld, CompassDirection dir, int spd, float mlsp)
	{
		this.temp = temp;
		this.rh = rh;
		this.cld = cld;
		this.dir = dir;
		this.spd = spd;
		this.mlsp = mlsp;
	}
	
	// TODO: getters
}
