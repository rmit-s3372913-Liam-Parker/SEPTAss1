package view;

import java.awt.Dimension;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import model.WeatherStationDailyEntry;
import model.WeatherStationSnapshotEntry;

public class WeatherStationDataGraph extends JFrame implements IWeatherSystemCallback
{
	private static final long serialVersionUID = -8115274594459474258L;
	
	private Map<Date, WeatherStationSnapshotEntry> entries;
	
	private JFreeChart graph;
	DefaultCategoryDataset dataSet = new DefaultCategoryDataset();;
	
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
		dataSet.clear();
	}
	
	/**
	 * Populates a dataset with values from entries array.
	 * @return A dataset for display inside of a chart.
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
