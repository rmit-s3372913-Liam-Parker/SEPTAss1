package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.WeatherSystem;
import view.FavouritesView;
import view.MainView;
import view.WeatherStationsView;

/**
 * Handles events on the MainView class
 * @author Liam
 *
 */
public class MainViewController implements ActionListener 
{
	WeatherSystem system;
	WeatherStationsView weatherStationView; // WeatherStation View
	FavouritesView favoritesView;
	
	public MainViewController(WeatherSystem sys, WeatherStationsView stationsView, FavouritesView favView)
	{
		this.system = sys;
		this.weatherStationView = stationsView;
		this.favoritesView = favView;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{
		case MainView.STATIONS_LABEL:
			weatherStationView.setVisible(!weatherStationView.isVisible());
			break;
		case MainView.FAVS_LABEL:
			favoritesView.setVisible(!favoritesView.isVisible());
			break;
		case MainView.REFRESH_LABEL:
			system.refreshWeatherData();
			JOptionPane.showMessageDialog(weatherStationView, "Latest data pulled from BOM.");
			break;
		}
	}
}
