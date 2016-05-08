package view;

import javax.swing.JTable;

import interfaces.IWeatherSystemCallback;
import model.WeatherStation;

public class WeatherStationDataTable extends JTable implements IWeatherSystemCallback
{
	private static final long serialVersionUID = 5280729831640510542L;
	
	private WeatherStation station;
	
	public WeatherStationDataTable(WeatherStation station)
	{
		
		super();
		this.station = station;
		setAutoResizeMode(AUTO_RESIZE_OFF);
		doLayout();
	}
	
	@Override
	public void Refresh() 
	{
		//TODO Update table data here
		
	}
}

