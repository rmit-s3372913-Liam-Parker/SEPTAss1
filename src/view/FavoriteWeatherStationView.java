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

import model.WeatherStation;

/**
 * This class represents a single weather station entry into
 * the favorites view. It contains aa table of the stations current available data
 * alngside a graph and other related information.
 * @author Liam
 */
public class FavoriteWeatherStationView extends JPanel 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	WeatherStationDataTable dataTable;
	WeatherStationDataGraph graph;
	
	JPanel leftPanel;
	JButton graphButton;
	
	public FavoriteWeatherStationView(WeatherStation station)
	{	
		InitializeStationView(station.getName());
		AttachActionListeners();
	}
	
	private void InitializeStationView(String station)
	{
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder(station));
		GridBagConstraints constraints = new GridBagConstraints();

		graphButton = new JButton("Graph");
		
		dataTable = new WeatherStationDataTable();
		graph = new WeatherStationDataGraph();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		this.add(graphButton, constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(dataTable, constraints);
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
