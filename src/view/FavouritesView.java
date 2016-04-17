package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import org.json.JSONObject;

import model.WeatherStation;
import model.WeatherSystem;


public class FavouritesView extends JFrame implements IJsonSerializable, IRefreshable
{
	private static final long serialVersionUID = 1L;
	
	WeatherSystem system;
	
	JScrollPane scrollPane;
	JPanel panel = new JPanel(new GridLayout(0,1, 5, 5));
	
	public FavouritesView(WeatherSystem system)
	{
		this.system = system;
		initializeWindow();
		Refresh();
	}
	
	/**
	 * Initialises the window/frame.
	 */
	private void initializeWindow()
	{
		scrollPane = new JScrollPane(panel);
		this.getContentPane().add(scrollPane);

		this.setTitle("Favorite Stations");
		this.setSize(800,400);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(1100,400));
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
		List<WeatherStation> favorites = system.getFavoriteStations();
		
		for(WeatherStation station : favorites)
		{
			panel.add(new FavoriteWeatherStationView(station));
		}
	}
}
