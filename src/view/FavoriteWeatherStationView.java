package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.WeatherStation;

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
		
		this.add(stationLabel);
		this.add(dataTable);
	}
	
	
}
