package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.WeatherSystem;
import view.FavouritesView;
import view.MainView;
import view.WeatherStationsView;

/**
 * Handles events on the MainView class
 * 
 * @author Liam
 */
public class MainViewController implements ActionListener
{
	JPanel mainPanel;
	WeatherSystem system;
	WeatherStationsView weatherStationView; // WeatherStation View
	FavouritesView favoritesView;
	
	public MainViewController(JPanel panel, WeatherSystem sys,
			WeatherStationsView stationsView, FavouritesView favView)
	{
		this.mainPanel = panel;
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
			JOptionPane.showMessageDialog(mainPanel, "Latest data pulled from BOM.");
			break;
		}
	}
}
