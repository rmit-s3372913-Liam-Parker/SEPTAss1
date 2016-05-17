package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.WeatherStation;

public class WeatherStationTest 
{
	WeatherStation station;
	
	@Before
	public void setUp() throws Exception 
	{
		station = new WeatherStation("Test Station", "invalidbomlink.com");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
