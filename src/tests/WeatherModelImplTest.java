package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import interfaces.WeatherSystem;
import model.WeatherModelImpl;

public class WeatherModelImplTest {

	WeatherSystem model;
	
	@Before
	public void setUp() throws Exception 
	{
		model = new WeatherModelImpl("test_stations.json");
	}
	
	@Test
	public void addFavoriteStationTestA()
	{
		//Test for inserting null name
		assertFalse(model.addFavoriteStation(null));	
	}
	
	@Test
	public void addFavoriteStationTestB()
	{
		assertTrue(model.addFavoriteStation("Test Station 1"));
	}
	
	@Test
	public void addFavoriteStationTestC()
	{
		assertFalse(model.addFavoriteStation("Invalid Station - faasddahwerbdf"));
	}

}
