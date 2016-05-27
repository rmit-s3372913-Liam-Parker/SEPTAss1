package controller;

import interfaces.WeatherSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StationsFilterController implements ActionListener {
	WeatherSystem system;

	public StationsFilterController(WeatherSystem system) {
		this.system = system;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		system.refreshFavoriteWeatherData();
	}

}
