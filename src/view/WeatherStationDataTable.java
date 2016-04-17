package view;

import javax.swing.JTable;

import model.WeatherStation;

public class WeatherStationDataTable extends JTable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5280729831640510542L;
	
	private WeatherStation station;
	
	public WeatherStationDataTable(WeatherStation station)
	{
		super(1, 10);
		this.station = station;
		
	}
	
	
}
