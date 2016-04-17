package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.State;
import model.WeatherSystem;

public class StationsFilterController implements ActionListener 
{
	WeatherSystem system;
	JTextField stationSearch;
	JComboBox<State> comboBox;
	JPanel panel;
	
	public StationsFilterController(JComboBox<State> comboBox, JTextField stationSearch, JPanel panel)
	{
		this.stationSearch = stationSearch;
		this.comboBox = comboBox;
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		system.refreshFavoriteWeatherData();
	}

}
