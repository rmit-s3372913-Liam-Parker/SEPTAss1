package main;

import model.WeatherModelImpl;
import model.WeatherSystem;
import view.MainView;

public class ProgramEntry 
{
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		WeatherSystem weatherSystem = new WeatherModelImpl();
		weatherSystem.refreshWeatherData();
		MainView frame = new MainView(weatherSystem);
	}
}
