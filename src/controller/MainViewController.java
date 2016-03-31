package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import model.WeatherSystem;

public class MainViewController implements ActionListener 
{
	JFrame stations, favorites;
	WeatherSystem weatherSystem;
	
	public MainViewController(JFrame stations, JFrame favorites, WeatherSystem system)
	{
		this.stations = stations;
		this.favorites = favorites;
	}
	
	// TODO: Implement these as anonymous inner classes instead.
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "Weather Stations":
			stations.setVisible(!stations.isVisible());
			break;
		case "Favourites":
			favorites.setVisible(!favorites.isVisible());
			break;
		case "Refresh":
			weatherSystem.RefreshWeatherData();
			break;
		}
	}
}
