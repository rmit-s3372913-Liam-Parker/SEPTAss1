package view;

import javax.swing.JTable;

import interfaces.IWeatherSystemCallback;
import model.WeatherStation;

public class WeatherStationDataTable extends JTable implements IWeatherSystemCallback
{
	public static final int numCols = 14;
	
	private static String[] colNames = {"Date/Time", "Temperature (Celsius)", "Apparent Temperature (Celsius)", "Dew Point (Celsius)",
			"Relative Humidity (%)", "deltaT (Celsius)", "Wind Direction", "Wind Speed (kmh)", "Gust Speed (kmh)",
			"Wind Speed (kts)", "Gust Speed (kts)", "QNH Pressure(hPa)", "MSL Pressure (hPa)", "Rain since 9am (mm)"};
	
	private static String[][] dataValues = {};
	/**
	 * 
	 */
	private static final long serialVersionUID = 5280729831640510542L;
	
	private WeatherStation station;
	
	public WeatherStationDataTable(WeatherStation station)
	{
		super(dataValues, colNames);
		this.station = station;
		setAutoResizeMode(AUTO_RESIZE_OFF);
		doLayout();
	}

	@Override
	public void Refresh() {
		// TODO Auto-generated method stub
		
	}
	
	
}
