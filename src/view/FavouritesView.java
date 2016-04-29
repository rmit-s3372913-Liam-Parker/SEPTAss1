package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.JSONObject;

import interfaces.IJsonSerializable;
import interfaces.IWeatherSystemCallback;
import interfaces.WeatherSystem;
import model.WeatherStation;


public class FavouritesView extends JPanel implements IJsonSerializable, IWeatherSystemCallback
{
	private static final long serialVersionUID = 1L;
	
	WeatherSystem system;
	
	JScrollPane scrollPane;
	JPanel panel = new JPanel(new GridLayout(0,1, 5, 5));
	
	public FavouritesView(WeatherSystem system)
	{
		this.system = system;
		system.registerRefreshableCallback(this);
		initializeWindow();
		Refresh();
	}
	
	/**
	 * Initialises the window/frame.
	 */
	private void initializeWindow()
	{
		scrollPane = new JScrollPane(panel);
		this.add(scrollPane);
	}

	@Override
	public JSONObject SaveToJsonObject() 
	{
		JSONObject object = new JSONObject();
		
		object.put("windowPosX", this.getX());
		object.put("windowPosY", this.getY());
		
		object.put("windowWidth", this.getWidth());
		object.put("windowHeight", this.getHeight());
		
		//TODO any other values to save?
		
		return object;
	}

	@Override
	public void LoadFromJsonObject(JSONObject obj) 
	{
		this.setBounds(obj.getInt("windowPosX"), obj.getInt("windowPosY"),
				obj.getInt("windowWidth"), obj.getInt("windowHeight"));
	}

	@Override
	public void Refresh()
	{
		panel.removeAll();
		List<WeatherStation> favorites = system.getFavoriteStations();
		
		for(WeatherStation station : favorites)
		{
			panel.add(new FavoriteStationView(system, station));
		}
		
		if(panel.getComponentCount() == 0)
			panel.add(new JLabel("Add some favorites in the station view to see something here!"));
	}
}
