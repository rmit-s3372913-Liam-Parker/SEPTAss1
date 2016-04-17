package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.WeatherStation;

/**
 * This class represents a single weather station entry into
 * the favorites view. It contains aa table of the stations current available data
 * alngside a graph and other related information.
 * @author Liam
 */
public class FavoriteWeatherStationView extends JPanel 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	WeatherStationDataTable dataTable;
	JLabel stationLabel;
	
	public FavoriteWeatherStationView(WeatherStation station)
	{
		stationLabel = new JLabel(station.getName());
		dataTable = new WeatherStationDataTable();
		this.setLayout(new BorderLayout());
		
		this.add(stationLabel, BorderLayout.WEST);
		this.add(dataTable, BorderLayout.EAST);
	}
}
