package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.JSONObject;

import controller.StationsFilterController;
import interfaces.IJsonSerializable;
import interfaces.IWeatherSystemCallback;
import interfaces.WeatherSystem;
import model.State;
import model.WeatherStation;


public class WeatherStationsView extends JPanel implements IWeatherSystemCallback, IJsonSerializable
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
		StationsFilterController controller = new StationsFilterController(system, comboBox, stationSearch, panel);
		stationSearch = new JTextField();
		stationSearch.addActionListener(controller);
		comboBox = new JComboBox<State>(State.values());
		comboBox.addActionListener(controller);
		
		panel.add(comboBox);
		panel.add(stationSearch);
		this.add(scrollPane);
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
