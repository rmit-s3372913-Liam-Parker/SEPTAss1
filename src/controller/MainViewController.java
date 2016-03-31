package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainViewController implements ActionListener 
{
	JFrame stations, favorites;
	
	public MainViewController(JFrame stations, JFrame favorites)
	{
		this.stations = stations;
		this.favorites = favorites;
	}
	
	// TODO: Implement these as anonymous inner classes instead.
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "Weather Stations":
			stations.setVisible(!stations.isVisible());
			break;
		case "Favourites":
			favorites.setVisible(!favorites.isVisible());
			break;
		}
	}
}
