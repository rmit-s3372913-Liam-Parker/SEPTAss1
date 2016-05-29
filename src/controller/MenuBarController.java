package controller;

import model.ForecastSource;
import model.WeatherStation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuBarController implements ActionListener
{
	public static Logger logger = Logger.getLogger("Menu Bar");
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		logger.entering("MenuBarController","actionPerformed");
		switch(arg0.getActionCommand())
		{
		case "Forecast.io":
			WeatherStation.forecastSource = ForecastSource.FORECAST_IO;
			logger.log(Level.INFO, "Forecast.io has been chosen");
			break;
		case "Open Weather Map":
			WeatherStation.forecastSource = ForecastSource.OPEN_WEATHER_MAP;
			logger.log(Level.INFO, "Open Weather Map has been chosen");
			break;
		}
		
	}
}
