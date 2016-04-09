import java.awt.*;

import model.WeatherModelImpl;
import model.WeatherSystem;

public class ProgramEntry 
{
	public static void main(String[] args) 
	{
		WeatherSystem weatherSystem = new WeatherModelImpl();
		weatherSystem.refreshWeatherData();
		MainView frame = new MainView(weatherSystem);
	}
}
