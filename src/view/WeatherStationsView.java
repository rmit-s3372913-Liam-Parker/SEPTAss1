package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import org.json.JSONObject;

import controller.StationsFilterController;
import model.State;
import model.WeatherStation;
import model.WeatherSystem;


public class WeatherStationsView extends JFrame implements IWeatherSystemCallback, IJsonSerializable
{
	private static final long serialVersionUID = 1L;
	
	WeatherSystem system;
	
	JScrollPane scrollPane;
	JPanel panel = new JPanel(new GridLayout(0,1, 5, 5));
	JTextField stationSearch;
	JComboBox<State> comboBox;
	
	JButton buttonFavourite = new JButton("Favourite");
	
	public WeatherStationsView(WeatherSystem system)
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
		
		//Set up search functionality
		StationsFilterController controller = new StationsFilterController(comboBox, stationSearch, panel);
		stationSearch = new JTextField();
		stationSearch.addActionListener(controller);
		comboBox = new JComboBox<State>(State.values());
		comboBox.addActionListener(controller);
		
		panel.add(comboBox);
		panel.add(stationSearch);
		this.getContentPane().add(scrollPane);
		
		this.setTitle("Weather Stations");
		this.setSize(400,400);
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
	}
	
	@Override
	public void Refresh() 
	{
		List<WeatherStation> favorites = system.getWeatherStations();
		
		for(WeatherStation station : favorites)
		{
			panel.add(new StationEntryView(station, system));
		}
		panel.repaint();
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
}
