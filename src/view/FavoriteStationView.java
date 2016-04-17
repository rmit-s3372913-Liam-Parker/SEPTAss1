package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import model.WeatherStation;

/**
 * This class represents a single weather station entry into
 * the favorites view. It contains aa table of the stations current available data
 * alngside a graph and other related information.
 * @author Liam
 */
public class FavoriteStationView extends JPanel 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	private WeatherStationDataTable dataTable;
	private WeatherStationDataGraph graph;
	
	private WeatherStation station;
	
	private JPanel leftPanel;
	private JButton graphButton;
	
	public FavoriteStationView(WeatherStation station)
	{	
		this.station = station;
		
		InitializeStationView(station);
		AttachActionListeners();
	}
	
	private void InitializeStationView(WeatherStation station)
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(station.getName()));

		graphButton = new JButton("Graph");
		
		dataTable = new WeatherStationDataTable();
		
		graph = new WeatherStationDataGraph("Test", this.station.getSnapshots());
		graph.pack();
		RefineryUtilities.centerFrameOnScreen(graph);
		
		this.add(graphButton, BorderLayout.WEST);
		
		this.add(dataTable, BorderLayout.EAST);
	}
	
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
}
