package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.json.JSONObject;

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
	JPanel panel = new JPanel();
	
	JPanel filterPanel = new JPanel();
	JComboBox<State> stateFilter = new JComboBox<State>();
	JTextField filterTextField = new JTextField();
	
	List<StationEntryView> entryList = new ArrayList<StationEntryView>();
	
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
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		TitledBorder border = BorderFactory.createTitledBorder("Weather Station Filter");
		border.setTitleJustification(TitledBorder.CENTER);
		filterPanel.setBorder(border);
		filterPanel.add(stateFilter);
		filterPanel.add(filterTextField);
		this.add(filterPanel);
		
		panel.setBorder(new EmptyBorder(15,15,15,15));
		//panel.add(Box.createRigidArea(new Dimension(7.5)));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(scrollPane);
	}
	
	@Override
	public void Refresh() 
	{
		List<WeatherStation> favorites = system.getWeatherStations();
		
		entryList.clear();
		
		for(WeatherStation station : favorites)
		{
			StationEntryView stationEntry = new StationEntryView(station, system);
			panel.add(stationEntry);
			entryList.add(stationEntry);
		}
		panel.validate();
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
