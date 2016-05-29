package view;

import controller.MenuBarController;
import interfaces.IJsonSerializable;
import interfaces.WeatherSystem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainView extends JFrame implements IJsonSerializable {
	private static final long serialVersionUID = 1L;

	public static Logger logger = Logger.getLogger("Main View");
	
	private static final String PROGRAM_TITLE = "Weather Application";

	// Window sizing constants.
	private static final int MIN_WIDTH = 500;
	private static final int DEFAULT_WIDTH = 900;

	private static final int MIN_HEIGHT = 400;
	private static final int DEFAULT_HEIGHT = 750;

	private static final String WINDOW_STATES_JSON = "WindowStates.json";

	// Model Interface
	WeatherSystem system;

	// Sub Views
	WeatherStationsView leftPanel;
	FavouritesView rightPanel;
	StatusBarView statusBar;

	// Menu Bar
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("Forecast Source");
	
	JPanel mainPanel = new JPanel(new GridLayout(0, 2));
	JPanel container = new JPanel(new BorderLayout());
	Font font = new Font("Calibri", Font.PLAIN, 15);

	public MainView(WeatherSystem system) {
		this.system = system;
		leftPanel = new WeatherStationsView(system);
		rightPanel = new FavouritesView(system);
		statusBar = new StatusBarView(system);

		InitializeWindow();
		AttachActionListeners();
	}

	/**
	 * Initialises the view of the window/frame.
	 */
	private void InitializeWindow() {
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);

		container.add(mainPanel, BorderLayout.CENTER);
		container.add(statusBar, BorderLayout.SOUTH);
		
		// Menu bar setup
		MenuBarController menuBarController = new MenuBarController();
		JMenuItem item = new JMenuItem("Forecast.io");
		item.addActionListener(menuBarController);
		JMenuItem item2 = new JMenuItem("Open Weather Map");
		item2.addActionListener(menuBarController);
		
		menu.add(item);
		menu.add(item2);
		
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		// Main window setup
		// There is a bug where you set the minSize and maxSize
		// It will minSize the frame but not maxSize
		// http://bugs.java.com/bugdatabase/view_bug.do;?bug_id=6200438
		// so I set the resizable to false
		this.getContentPane().add(container);
		this.setTitle(PROGRAM_TITLE);
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setLocationRelativeTo(null); // centre the frame on screen
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(true);
		this.pack();

		loadProgramState();
		this.setVisible(true);
	}

	/**
	 * This function would ideally be removed we need to transition this window
	 * listener to controller. However we're reliant on functionality in this
	 * class. TODO:
	 */
	private void AttachActionListeners() {
		logger.entering("MainView","AttachActionListeners");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logger.entering("MainView", "windowClosing");
				saveProgramState();
				logger.log(Level.INFO, "The system has been saved and exited");
				System.exit(0);
			}
		});
	}

	/**
	 * Will load from json state file if available, grabbing the last known
	 * window sizes and positions to reinitialize on this execution of the
	 * program.
	 */
	private void loadProgramState() {
		logger.entering("MainView","loadProgramState");
		String windowStatesJson = "";

		try {
			FileReader reader = new FileReader(WINDOW_STATES_JSON);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				windowStatesJson += line;
			}

			bufferedReader.close();

		} catch (IOException e) {
			logger.log(Level.WARNING, "Cannot read the window states " + WINDOW_STATES_JSON
					+ ". The program will not execute correctly, replace Window States JSON file and try again!");
			return;
		}

		JSONTokener tokener = new JSONTokener(windowStatesJson);
		JSONArray stateArray = new JSONArray(tokener);

		// TODO for now we assume indexes until we can
		// find a way to save objects with window name.
		LoadFromJsonObject(stateArray.getJSONObject(0));
		leftPanel.LoadFromJsonObject(stateArray.getJSONObject(1));
	}

	private void saveProgramState() {
		
		logger.entering("MainView","saveProgramState");
		JSONArray windowArray = new JSONArray();
		windowArray.put(SaveToJsonObject());
		windowArray.put(leftPanel.SaveToJsonObject());

		try {
			PrintWriter writer = new PrintWriter("WindowStates.json");
			writer.print(windowArray.toString());
			writer.close();
		} catch (FileNotFoundException e1) {
			logger.log(Level.WARNING, "Cannot read the file WindowStates.json. The program "
					+ "will not execute correctly, replace the file and try again!");
		}
	}

	@Override
	public JSONObject SaveToJsonObject() {
		JSONObject object = new JSONObject();

		object.put("windowPosX", this.getX());
		object.put("windowPosY", this.getY());

		object.put("windowWidth", this.getWidth());
		object.put("windowHeight", this.getHeight());

		// TODO any other values to save?

		return object;
	}

	@Override
	public void LoadFromJsonObject(JSONObject obj) {
		this.setBounds(obj.getInt("windowPosX"), obj.getInt("windowPosY"), obj.getInt("windowWidth"),
				obj.getInt("windowHeight"));
	}
}
