package view;

import java.awt.Dimension;
import java.util.Date;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import interfaces.IWeatherSystemCallback;
import model.WeatherStationSnapshotEntry;

/**
 * A window containing the graph for a weather stations data.
 * @author Liam
 */
public class WeatherStationDataGraph extends JFrame implements IWeatherSystemCallback
{
	private static final long serialVersionUID = -8115274594459474258L;
	
	private Map<Date, WeatherStationSnapshotEntry> entries;
	
	private JFreeChart graph;
	DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
	
	/**
	 * Constructor for a weatherStation graph with name and data
	 * @param name The name of the station
	 * @param data The data for the graph to display
	 */
	public WeatherStationDataGraph(String name, Map<Date, WeatherStationSnapshotEntry> data)
	{
		super(name);
		this.entries = data;
		
		graph = ChartFactory.createLineChart(name + " Historical Temperatures",
				"Date", "Temperature", populateData(), PlotOrientation.VERTICAL, true, true , false);
		
		ChartPanel panel = new ChartPanel(graph);
		panel.setPreferredSize(new Dimension(600,600));
		
		this.setContentPane(panel);
	}
	
	/**
	 * Populates a dataset with values from entries array.
	 * @return A dataset for display inside of a chart. NOTE, this
	 * dataset is just a convenience reference to the graphs
	 * internal dataset.
	 */
	private DefaultCategoryDataset populateData()
	{
		dataSet.clear();
		if(entries != null)
		{
			for(WeatherStationSnapshotEntry entry : entries.values())
			{
				dataSet.addValue(entry.getTemp(), "Temperature", entry.getDate().toString());
			}
		}
		return dataSet;
	}

	@Override
	public void Refresh() 
	{
		populateData();
		graph.fireChartChanged();
	}
}
