package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import model.WeatherStationDailyEntry;

public class WeatherStationDataGraph extends ApplicationFrame
{
	private static final long serialVersionUID = -8115274594459474258L;
	
	List<WeatherStationDailyEntry> entries;
	
	public WeatherStationDataGraph(String name, List<WeatherStationDailyEntry> data)
	{
		super(name);
		this.entries = data;
		
		JFreeChart graph = ChartFactory.createLineChart(name + " Historical Temperatures",
				"Date", "Temperature", populateData(), PlotOrientation.VERTICAL, true, true , false);
		
		ChartPanel panel = new ChartPanel(graph);
		panel.setPreferredSize(new Dimension(600,600));
		this.setContentPane(panel);
	}
	
	/**
	 * Populates a dataset with values from entries array.
	 * @return A dataset for display inside of a chart.
	 */
	private DefaultCategoryDataset populateData()
	{
		DefaultCategoryDataset set = new DefaultCategoryDataset();
		
		set.addValue(10, "minTemp", "2000");
		set.addValue(11, "minTemp", "2001");
		set.addValue(12, "minTemp", "2002");
		set.addValue(13, "minTemp", "2003");
		set.addValue(14, "minTemp", "2004");
		
		set.addValue(15, "maxTemp", "2000");
		set.addValue(20, "maxTemp", "2001");
		set.addValue(25, "maxTemp", "2002");
		set.addValue(30, "maxTemp", "2003");
		set.addValue(35, "maxTemp", "2004");
		return set;
	}
	
	
}
