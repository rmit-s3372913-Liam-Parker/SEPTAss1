package tests;

import model.WeatherStation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
