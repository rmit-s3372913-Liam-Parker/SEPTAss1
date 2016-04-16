package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import model.*;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MainView extends JFrame implements IJsonSerializable
{
	private static final long serialVersionUID = 1L;
	
	WeatherSystem system;
	WeatherStations weatherStationView; // WeatherStation View
	FavouritesView favoritesView;
	
	JPanel panel = new JPanel(new BorderLayout());
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();
	JLabel weather = new JLabel();

	JButton buttonWeather = new JButton("Weather Stations");
	JButton buttonFavourites = new JButton("Favourites");
	JButton buttonRefresh = new JButton("Refresh");
	
	public MainView(WeatherSystem system)
	{	
		this.system = system;
		weatherStationView = new WeatherStations(system);
		favoritesView = new FavouritesView(system);
		InitializeWindow();
		AttachActionListeners();
	}
	
	/**
	 * Initialises the view of the window/frame.
	 */
	private void InitializeWindow()
	{
		bottomPanel.add(buttonWeather);
		bottomPanel.add(buttonFavourites);
		bottomPanel.add(buttonRefresh);
		
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("Welcome to the Weather App", SwingConstants.CENTER);
		topPanel.add(label);

		weather.setIcon(new ImageIcon("images/weather.png"));
		topPanel.add(weather);
		
		//There is a bug where you set the minSize and maxSize 
		//It will minSize the frame but not maxSize
		//http://bugs.java.com/bugdatabase/view_bug.do;?bug_id=6200438
		//so I set the resizable to false
		this.add(panel);
		this.setTitle("Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setMinimumSize(new Dimension(400,400));
		//this.setMaximumSize(new Dimension(450,500));
		this.setResizable(false);
		
		//Load the stub of sub windows
		loadProgramState();
		
		this.setVisible(true);
	}
	
	private void AttachActionListeners()
	{
		// Button event listeners
				buttonWeather.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						weatherStationView.setVisible(!weatherStationView.isVisible());
					}
				});
				
				buttonFavourites.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						favoritesView.setVisible(!favoritesView.isVisible());
					}
				});
				
				buttonRefresh.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						system.refreshWeatherData();
					}
				});
				
				this.addWindowListener( new WindowAdapter() 
				{
				    @Override
				    public void windowClosing(WindowEvent e) 
				    {
				    	saveProgramState();
				    	System.exit(0);
				    }
				});
	}
	
	private void loadProgramState()
	{
		String windowStatesJson = "";
		
		try 
		{
            FileReader reader = new FileReader("WindowStates.json");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            
            while((line = bufferedReader.readLine()) != null) 
            {
            	windowStatesJson += line;
            }   
            
            bufferedReader.close();
            
		} 
		catch (IOException e) { return; }
		
		JSONTokener tokener = new JSONTokener(windowStatesJson);
		JSONArray stateArray = new JSONArray(tokener);
		
		//TODO for now we assume indexes until we can
		// find a way to save objects with window name.
		LoadFromJsonObject(stateArray.getJSONObject(0));
		weatherStationView.LoadFromJsonObject(stateArray.getJSONObject(1));
		
	}
	
	private void saveProgramState()
	{
		JSONArray windowArray = new JSONArray();
		windowArray.put(SaveToJsonObject());
    	windowArray.put(weatherStationView.SaveToJsonObject());
    	
    	try 
    	{
			PrintWriter writer = new PrintWriter("WindowStates.json");
			writer.print(windowArray.toString());
			writer.close();
		} 
    	catch (FileNotFoundException e1) { }
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
