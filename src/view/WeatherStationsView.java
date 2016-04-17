package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONObject;

import model.WeatherStation;
import model.WeatherSystem;


public class WeatherStationsView extends JFrame implements IRefreshable, IJsonSerializable
{
	private static final long serialVersionUID = 1L;
	
	static String[] columns = {"", "Station"};
	
	WeatherSystem system;
	
	JTable table;
	JScrollPane scrollPane;
	JPanel panel = new JPanel();
	
	
	JButton buttonFavourite = new JButton("Favourite");
	
	public WeatherStationsView(WeatherSystem system)
	{
		this.system = system;
		initializeWindow();
	}
	
	/**
	 * Initialises the window/frame.
	 */
	private void initializeWindow()
	{
		buildTable();
		//scrollPane = new JScrollPane(table);
		//panel.add(scrollPane);
		
		this.add(panel);
		this.getContentPane().add(panel, BorderLayout.WEST);
		this.setTitle("Weather Stations | Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
	}
	
	/**
	 * Represents the view of the weather stations through a table.
	 */
	private void buildTable()
	{	
		
	}
	
	@Override
	public void Refresh() 
	{
		// TODO Regrab data
		
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
