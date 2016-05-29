package controller;

import interfaces.WeatherSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationsFilterController implements ActionListener {
	WeatherSystem system;

	public static Logger logger = Logger.getLogger("Stations Filter");
	
	public StationsFilterController(WeatherSystem system) {
		this.system = system;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		logger.entering("StationsFilterController", "actionPerformed");
		system.refreshFavoriteWeatherData();
		logger.log(Level.INFO, "The system has been refreshed");
	}

}
