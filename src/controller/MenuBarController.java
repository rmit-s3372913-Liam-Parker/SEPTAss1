package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.ForecastSource;
import model.WeatherStation;

public class MenuBarController implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand())
		{
		case "Forecast.io":
			WeatherStation.forecastSource = ForecastSource.FORECAST_IO;
			break;
		case "Open Weather Map":
			WeatherStation.forecastSource = ForecastSource.OPEN_WEATHER_MAP;
			break;
		}
		
	}
}
