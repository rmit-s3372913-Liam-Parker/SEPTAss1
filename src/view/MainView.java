package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import controller.MainViewController;
import interfaces.IJsonSerializable;
import interfaces.WeatherSystem;

public class MainView extends JFrame implements IJsonSerializable
{
	private static final long serialVersionUID = 1L;
	
	private static final String PROGRAM_TITLE = "Weather Application";
	
	//Window sizing constants.
	private static final int MIN_WIDTH = 500;
	private static final int DEFAULT_WIDTH = 900;
	
	private static final int MIN_HEIGHT = 400;
	private static final int DEFAULT_HEIGHT = 750;
	
	//Model Interface
	WeatherSystem system;
	
	//Sub Views
	WeatherStationsView leftPanel;
	FavouritesView rightPanel;
	
	JPanel mainPanel = new JPanel(new GridLayout(0,2));
	
	Font font = new Font("Calibri", Font.PLAIN, 15);
	
	public MainView(WeatherSystem system)
	{	
		this.system = system;
		leftPanel = new WeatherStationsView(system);
		rightPanel = new FavouritesView(system);
		InitializeWindow();
		AttachActionListeners();
	}
	
	/**
	 * Initialises the view of the window/frame.
	 */
	private void InitializeWindow()
	{
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		//There is a bug where you set the minSize and maxSize 
		//It will minSize the frame but not maxSize
		//http://bugs.java.com/bugdatabase/view_bug.do;?bug_id=6200438
		//so I set the resizable to false
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.setTitle(PROGRAM_TITLE);
		this.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		this.setMinimumSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		this.setLocationRelativeTo(null); //centre the frame on screen
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		
		loadProgramState();
		this.setVisible(true);
	}
	
	/**
	 * This function would ideally be removed
	 * we need to transition this window listener to controller.
	 * However we're reliant on functionality in this class. 
	 * TODO:
	 */
	private void AttachActionListeners()
	{
		//MainViewController controller = new MainViewController(bottomPanel, system,
		//		weatherStationView, favoritesView);
		
		//buttonWeather.addActionListener(controller);
		//buttonFavourites.addActionListener(controller);
		//buttonRefresh.addActionListener(controller);
		
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
	
	/**
	 * Will load frm json state file if available, grabbing
	 * the last known window sizes and positions to reinitialize on this
	 * execution of the program.
	 */
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
		leftPanel.LoadFromJsonObject(stateArray.getJSONObject(1));
	}
	
	private void saveProgramState()
	{
		JSONArray windowArray = new JSONArray();
		windowArray.put(SaveToJsonObject());
    	windowArray.put(leftPanel.SaveToJsonObject());
    	
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
