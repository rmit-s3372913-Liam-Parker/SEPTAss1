package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.WeatherStation;
import model.WeatherSystem;

/**
 * Stores GUI related functionality of an individual
 * station entry on the WeatherStations JFrame.
 * @author Liam
 *
 */
public class WeatherStationInfoView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	JPanel leftPanel;
	JButton favoriteButton;
	
	WeatherStation station;
	WeatherSystem system;
	
	public WeatherStationInfoView(WeatherStation station,
			WeatherSystem system)
	{	
		this.station = station;
		this.system = system;
		InitializeStationView(station.getName());
		AttachActionListeners();
	}
	
	private void InitializeStationView(String station)
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(station));
		
		favoriteButton = new JButton("Favorite");
		
		
		this.add(favoriteButton, BorderLayout.WEST);
	}
	
	private void AttachActionListeners()
	{
		favoriteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				WeatherStation temp = system.getFavoriteStation(station.getName());
				if(temp == null)
				{
					system.addFavoriteStation(station.getName());
					favoriteButton.setText("Unfavorite");
				}
				else
				{
					system.removeFavoriteStation(station.getName());
					favoriteButton.setText("Favorite");
				}
			}
		});
	}
}
