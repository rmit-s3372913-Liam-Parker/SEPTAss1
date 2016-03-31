import java.awt.*;
import javax.swing.*;

public class WeatherStations extends JFrame
{
	public void Stations()
	{
		JFrame frame = new JFrame();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,2));
		
		for(int i= 0; i<=10; i++)
		{
			panel1.add(new JLabel("" + i)); //numbers each cell
		}
	}
	
	
}
