package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.WeatherStation;
import model.WeatherSystem;

public class StationFavoriteButtonController implements ActionListener
{
	WeatherStation station;
	WeatherSystem system;
	JButton button;
	
	public StationFavoriteButtonController(WeatherStation station, WeatherSystem system, JButton btn)
	{
		this.station = station;
		this.system = system;
		this.button = btn;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		WeatherStation temp = system.getFavoriteStation(station.getName());
		if(temp == null)
		{
			system.addFavoriteStation(station.getName());
			button.setText("Unfavorite");
		}
		else
		{
			system.removeFavoriteStation(station.getName());
			button.setText("Favorite");
		}
	}
}
