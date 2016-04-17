package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.WeatherStation;

public class WeatherStationInfoView extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -615795282210380188L;
	
	WeatherStationDataTable dataTable;
	WeatherStationDataGraph graph;
	
	JPanel leftPanel;
	JButton favoriteButton;
	
	public WeatherStationInfoView(WeatherStation station)
	{	
		InitializeStationView(station.getName());
		AttachActionListeners();
	}
	
	private void InitializeStationView(String station)
	{
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(station));

		favoriteButton = new JButton("Graph");
		this.add(favoriteButton, BorderLayout.WEST);
	}
	
	private void AttachActionListeners()
	{
		favoriteButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				graph.setVisible(!graph.isVisible());
			}
		});
	}
}
