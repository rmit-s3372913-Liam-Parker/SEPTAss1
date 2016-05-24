package main;

import interfaces.WeatherSystem;
import model.WeatherModelImpl;
import view.MainView;

public class ProgramEntry {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		WeatherSystem weatherSystem = new WeatherModelImpl();
		weatherSystem.refreshFavoriteWeatherData();
		MainView frame = new MainView(weatherSystem);
	}
}
