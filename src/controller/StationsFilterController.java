package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.WeatherSystem;
import model.State;

public class StationsFilterController implements ActionListener 
{
	WeatherSystem system;
	
	public StationsFilterController(WeatherSystem system)
	{
		this.system = system;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		system.refreshFavoriteWeatherData();
	}

}
