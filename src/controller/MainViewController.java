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
	
	// Lazy with a switch, should probably extract each
	// button to its own listener at some point.
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		switch(arg0.getActionCommand())
		{
		case "Weather Stations":
			stations.setVisible(true);
			break;
		case "Favourites":
			favorites.setVisible(true);
			break;
		}
	}
}
