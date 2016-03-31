import java.awt.*;
import javax.swing.*;

public class WeatherStations extends JFrame
{
	
	JPanel panel1 = new JPanel();
	
	public WeatherStations()
	{
		panel1.setLayout(new GridLayout(6,2));
		setBounds(5,5,100,100);
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JScrollPane scrollPane = new JScrollPane();
		
		
		for(int i= 0; i<=9; i++)
		{
			panel1.add(new JLabel("" + i)); //numbers each cell
		}

		this.add(scrollPane);
		this.add(panel1);
		this.setTitle("Weather Stations | Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
		
	}
	
	
	
}
