package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import interfaces.IWeatherSystemCallback;
import interfaces.WeatherSystem;
import model.WeatherStation;

/**
 * This class represents a single weather station entry into
 * the favorites view. It contains aa table of the stations current available data
 * alngside a graph and other related information.
 * @author Liam
 */
public class FavoriteStationView extends JPanel implements IWeatherSystemCallback
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	private WeatherStationDataTable dataTable;
	private WeatherStationDataGraph graph;
	
	private WeatherStation station;
	
	private JButton graphButton;
	
	/**
	 * Create a view of a specified station's data.
	 * @param station The station to compile data for.
	 */
	public FavoriteStationView(WeatherSystem system, WeatherStation station)
	{	
		this.station = station;
		
		InitializeStationView(system, station);
		AttachActionListeners();
	}
	
	/**
	 * Initializes the station data and registers data visualization objects with system.
	 * @param system The WeatherSystem to register with.
	 * @param station
	 */
	private void InitializeStationView(WeatherSystem system, WeatherStation station)
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(station.getName()));

		graphButton = new JButton("Graph");
		dataTable = new WeatherStationDataTable(station);
		
		graph = new WeatherStationDataGraph("Test", this.station.getSnapshots());
		graph.pack();
		RefineryUtilities.centerFrameOnScreen(graph);
		
		this.add(graphButton, BorderLayout.WEST);
		this.add(dataTable, BorderLayout.EAST);
		
		//system.registerRefreshableCallback(this);
	}
	
	/**
	 * Attaches action listeners to interactive elements.
	 * TODO: Extract to controller
	 */
	private void AttachActionListeners()
	{
		graphButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				graph.setVisible(!graph.isVisible());
			}
		});
	}

	@Override
	public void Refresh() 
	{
		dataTable.Refresh();
		graph.Refresh();
	}
}
