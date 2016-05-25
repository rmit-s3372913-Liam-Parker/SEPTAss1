package view;

import javax.swing.JPanel;
import javax.swing.JTable;

import interfaces.IWeatherSystemCallback;
import model.WeatherStation;

public class WeatherStationDataTable extends JPanel implements IWeatherSystemCallback {
	private static final long serialVersionUID = 5280729831640510542L;

	private WeatherStation station;

	static String[] forecastCols = {"Date",
            "Temp",
            "Rel Hum",
            "Wind Dir",
            "Wind Spd Kts",
            "PressureQNH",
            "Rain MM"};
	
	public WeatherStationDataTable(WeatherStation station) 
	{

		super();
		this.station = station;
	}

	@Override
	public void Refresh() {
		// TODO Update table data here

	}
}
