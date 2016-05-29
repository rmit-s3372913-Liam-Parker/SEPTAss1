package controller;

import interfaces.WeatherSystem;
import model.WeatherStation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationFavoriteButtonController implements ActionListener {
	WeatherStation station;
	WeatherSystem system;
	JButton button;

	public static Logger logger = Logger.getLogger("Station Favourite Button");
	
	public StationFavoriteButtonController(WeatherStation station, WeatherSystem system, JButton btn) {
		this.station = station;
		this.system = system;
		this.button = btn;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		logger.entering("StationFavouriteButtonController", "actionPerformed");
		WeatherStation temp = system.getFavoriteStation(station.getName());
		if (temp == null) {
			system.addFavoriteStation(station.getName());
			button.setText("Unfavorite");
			logger.log(Level.INFO, "Added a favourite" + station.getName());
		} else {
			system.removeFavoriteStation(station.getName());
			button.setText("Favorite");
			logger.log(Level.INFO, "Removed a favourite" + station.getName());
		}
	}
}
