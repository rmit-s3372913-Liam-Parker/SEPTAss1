import java.awt.*;

import model.WeatherModelImpl;
import model.WeatherSystem;

public class ProgramEntry 
{
	public static void main(String[] args) 
	{
		WeatherSystem weatherSystem = new WeatherModelImpl();
		MainView frame = new MainView(weatherSystem);

		Color c = new Color(255,255,255);
		frame.getContentPane().setBackground(c);
	}
}
