package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.StationFavoriteButtonController;
import interfaces.WeatherSystem;
import model.WeatherStation;

/**
 * Stores GUI related functionality of an individual
 * station entry on the WeatherStations JFrame.
 * @author Liam
 *
 */
public class StationEntryView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	JPanel leftPanel;
	JButton favoriteButton;
	StationFavoriteButtonController controller;
	
	public StationEntryView(WeatherStation station, WeatherSystem system)
	{	
		
		InitializeStationView(station.getName(), system);
		
		favoriteButton.addActionListener(
				new StationFavoriteButtonController(station,system, favoriteButton));
	}
	
	private void InitializeStationView(String station, WeatherSystem system)
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(station));
		
		if(system.getFavoriteStation(station) == null)
			favoriteButton = new JButton("Favorite");
		else
			favoriteButton = new JButton("Unfavorite");
		
		
		this.add(favoriteButton, BorderLayout.CENTER);
	}
}
