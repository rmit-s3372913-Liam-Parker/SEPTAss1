import java.awt.*;
import javax.swing.*;

import controller.MainViewController;

public class MainView extends JFrame 
{
	public void ShowBorderLayout()
	{
		MainViewController controller = new MainViewController(null, null);
		JPanel panel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel weather = new JLabel();
		
		JButton buttonWeather = new JButton("Weather Stations");
		JButton buttonFavourites = new JButton("Favourites");
		JButton buttonRefresh = new JButton("Refresh");
		buttonWeather.addActionListener(controller);
		buttonFavourites.addActionListener(controller);
		buttonRefresh.addActionListener(controller);
		
		bottomPanel.add(buttonWeather);
		bottomPanel.add(buttonFavourites);
		bottomPanel.add(buttonRefresh);
		
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("Welcome to the Weather App");
		topPanel.add(label);
		
		weather.setIcon(new ImageIcon("images/weather.png"));
		topPanel.add(weather);
		
		this.add(panel);
		this.setTitle("Weather App");
		this.setSize(400,400);
		this.setLocationRelativeTo(null); //centre the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(400,400));
		
	}

	
}
