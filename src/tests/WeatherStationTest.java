package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.WeatherStation;

public class WeatherStationTest {
	WeatherStation station;

	@Before
	public void setUp() throws Exception {
		station = new WeatherStation("Test Station", "invalidbomlink.com");
	}

	@Test
	public void testConstructor() {
		assertEquals("Test Station", station.getName());
		assertEquals("invalidbomlink.com", station.getBomLink());
	}
}
