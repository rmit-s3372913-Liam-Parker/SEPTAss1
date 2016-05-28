package view;

import interfaces.IWeatherSystemCallback;
import model.WeatherDataPoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

/**
 * A window containing the graph for a weather stations data.
 * 
 * @author Liam
 */
public class WeatherStationDataGraph extends JFrame implements IWeatherSystemCallback {
	private static final long serialVersionUID = -8115274594459474258L;

	private Map<Date, WeatherDataPoint> entries;

	private JToggleButton tempToggle = new JToggleButton("Temperature", true);
	private JToggleButton appTempToggle = new JToggleButton("Apparent Temperature", true);
	private JToggleButton dewPointToggle = new JToggleButton("Dew Point", true);
	private JToggleButton wSpdKtsToggle = new JToggleButton("Wind Speed Kts", true);
	private JToggleButton wSpdKmhToggle = new JToggleButton("Wind Speed Kmh", true);
	private JToggleButton rainToggle = new JToggleButton("Rain MM", true);

	private JFreeChart graph;
	DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

	/**
	 * Constructor for a weatherStation graph with name and data
	 * 
	 * @param name
	 *            The name of the station
	 * @param data
	 *            The data for the graph to display
	 */
	public WeatherStationDataGraph(String name, Map<Date, WeatherDataPoint> data) {
		super(name);
		this.entries = data;

		graph = ChartFactory.createLineChart(name + " - Weather Information", "Date", "Measurement", populateData(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel graphPane = new ChartPanel(graph);
		graph.getCategoryPlot().setRangePannable(true);
		graphPane.setPreferredSize(new Dimension(1024, 768));

		JPanel togglePane = new JPanel();
		togglePane.add(tempToggle);
		togglePane.add(appTempToggle);
		togglePane.add(dewPointToggle);
		togglePane.add(wSpdKtsToggle);
		togglePane.add(wSpdKmhToggle);
		togglePane.add(rainToggle);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(graphPane, BorderLayout.CENTER);
		contentPanel.add(togglePane, BorderLayout.SOUTH);

		this.setContentPane(contentPanel);

		initializeListeners();
	}

	/**
	 * Populates a dataset with values from entries array.
	 * 
	 * @return A dataset for display inside of a chart. NOTE, this dataset is
	 *         just a convenience reference to the graphs internal dataset.
	 */
	private DefaultCategoryDataset populateData() {
		dataSet.clear();
		if (entries != null) {
			for (WeatherDataPoint entry : entries.values()) {
				dataSet.addValue(entry.getTemp(), "Temperature", entry.getDate().toString());
				dataSet.addValue(entry.getAppTemp(), "Apparent Temperature", entry.getDate().toString());
				dataSet.addValue(entry.getDewPoint(), "Dew Point", entry.getDate().toString());
				dataSet.addValue(entry.getWindSpeedKts(), "Wind Speed Kts", entry.getDate().toString());
				dataSet.addValue(entry.getWindSpeedKmh(), "Wind Speed Kmh", entry.getDate().toString());
				dataSet.addValue(entry.getRainSinceNineAM(), "Rain MM", entry.getDate().toString());
			}
		}
		return dataSet;
	}

	private void initializeListeners()
	{
		tempToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(0, !renderer.isSeriesVisible(0));
				renderer.setSeriesVisibleInLegend(0, true, false);
			}
		});

		appTempToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(1, !renderer.isSeriesVisible(1));
				renderer.setSeriesVisibleInLegend(1, true, false);
			}
		});

		dewPointToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(2, !renderer.isSeriesVisible(2));
				renderer.setSeriesVisibleInLegend(2, true, false);
			}
		});

		wSpdKtsToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(3, !renderer.isSeriesVisible(3));
				renderer.setSeriesVisibleInLegend(3, true, false);
			}
		});

		wSpdKmhToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(4, !renderer.isSeriesVisible(4));
				renderer.setSeriesVisibleInLegend(4, true, false);
			}
		});

		rainToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = graph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(5, !renderer.isSeriesVisible(5));
				renderer.setSeriesVisibleInLegend(5, true, false);
			}
		});
	}

	@Override
	public void Refresh() {
		populateData();
		graph.fireChartChanged();
	}
}
