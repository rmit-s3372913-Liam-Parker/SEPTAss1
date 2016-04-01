import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import javax.swing.*;

public class MainView extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	JPanel panel = new JPanel(new BorderLayout());
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JLabel weather = new JLabel();

	JButton buttonWeather = new JButton("Weather Stations");
	JButton buttonFavourites = new JButton("Favourites");
	JButton buttonRefresh = new JButton("Refresh");
	
	

	public MainView(WeatherSystem system)
	{

		WeatherStations station = new WeatherStations();
		
		// Button event listeners
		buttonWeather.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				station.setVisible(!station.isVisible());
			}
		});
		
		buttonFavourites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
				//favorites.setVisible(!favorites.isVisible());
			}
		});
		
		buttonRefresh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				system.refreshWeatherData();
			}
		});
		
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
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(400,400));
		
	}

	
}
