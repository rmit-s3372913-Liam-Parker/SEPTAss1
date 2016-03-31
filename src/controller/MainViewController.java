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
			ToggleWindowVisibility(stations);
			break;
		case "Favourites":
			ToggleWindowVisibility(favorites);
			break;
		case "Refresh":
			weatherSystem.RefreshWeatherData();
			break;
		}
	}
	
	/*
	 * Toggles a JFrames visibility.
	 * 
	 * @param frame The JFrame to toggle visibility on.
	 */
	private void ToggleWindowVisibility(JFrame frame)
	{
		frame.setVisible(!frame.isVisible());
	}
}
