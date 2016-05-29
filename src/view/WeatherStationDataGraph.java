package view;

import interfaces.IWeatherSystemCallback;
import model.WeatherDataPoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Date;
import java.util.Map;

/**
 * A window containing the historicalGraph for a weather stations data.
 * 
 * @author Liam
 */
public class WeatherStationDataGraph extends JFrame implements IWeatherSystemCallback {
	private static final long serialVersionUID = -8115274594459474258L;

	private Map<Date, WeatherDataPoint> historicalData;
    private Map<Date, WeatherDataPoint> forecastData;

	private JToggleButton tempToggle = new JToggleButton("Temperature", true);
	private JToggleButton appTempToggle = new JToggleButton("Apparent Temperature", true);
	private JToggleButton dewPointToggle = new JToggleButton("Dew Point", true);
	private JToggleButton wSpdKtsToggle = new JToggleButton("Wind Speed Kts", true);
	private JToggleButton wSpdKmhToggle = new JToggleButton("Wind Speed Kmh", true);
	private JToggleButton rainToggle = new JToggleButton("Rain MM", true);

	private JFreeChart historicalGraph;
	private JFreeChart forecastGraph;

    JScrollBar historicalScroll = new JScrollBar(JScrollBar.HORIZONTAL);
	JScrollBar forecastScroll = new JScrollBar(JScrollBar.HORIZONTAL);

    private SlidingCategoryDataset historicalDataset;
	private SlidingCategoryDataset forecastDataset;

	/**
	 * Constructor for a weatherStation historicalGraph with name and data
	 * 
	 * @param name
	 *            The name of the station
	 * @param historical
	 *            The data for the historicalGraph to display
	 */
	public WeatherStationDataGraph(String name, Map<Date, WeatherDataPoint> historical, Map<Date, WeatherDataPoint> forecast) {
		super(name);
		this.historicalData = historical;
        this.forecastData = forecast;

        // We create a new JFreeChart line chart and populate its data
        // with the current available weather info from model.
		historicalGraph = ChartFactory.createLineChart(name + " - Weather Information", "Date", "Measurement", populateHistoricalData(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel graphPane = new ChartPanel(historicalGraph);
		historicalGraph.getCategoryPlot().setRangePannable(true);
		graphPane.setPreferredSize(new Dimension(1024, 768));

        // We add the toggles for series here
		JPanel togglePane = new JPanel();
		togglePane.add(tempToggle);
		togglePane.add(appTempToggle);
		togglePane.add(dewPointToggle);
		togglePane.add(wSpdKtsToggle);
		togglePane.add(wSpdKmhToggle);
		togglePane.add(rainToggle);

        // We need a scrollbar to translate the horizontal axis and view all available data.
        historicalScroll.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(historicalData.size() <= 1) return;
                historicalScroll.setMaximum((historicalData.size() - 1) + (forecastData.size() - 1));
                historicalDataset.setFirstCategoryIndex(e.getValue());
            }
        });

        // We add the toggle and scroll panes together to form the control panel
        JPanel controlPane = new JPanel(new BorderLayout());
        controlPane.add(historicalScroll, BorderLayout.NORTH);
        controlPane.add(togglePane, BorderLayout.SOUTH);

        // We place the control panel below the chart and set it into the JFrame
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(graphPane, BorderLayout.CENTER);
		contentPanel.add(controlPane, BorderLayout.SOUTH);

		this.setContentPane(contentPanel);

		initializeListeners();
	}

	/**
	 * Populates a dataset with values from historicalData array.
	 *
	 * @return A dataset for display inside of a chart. NOTE, this dataset is
	 *         just a convenience reference to the graphs internal dataset.
	 */
	private SlidingCategoryDataset populateHistoricalData() {
		DefaultCategoryDataset internal = new DefaultCategoryDataset();
        historicalDataset = new SlidingCategoryDataset(internal, 0, 5);
        // Loop through daily historicalData adding available weather data to series.
		if (historicalData != null) {
			for (WeatherDataPoint entry : historicalData.values()) {
				String dateStr = entry.getDate().toString();
				internal.addValue(entry.getTemp(), "Temperature", dateStr);
				internal.addValue(entry.getAppTemp(), "Apparent Temperature", dateStr);
				internal.addValue(entry.getDewPoint(), "Dew Point", dateStr);
				internal.addValue(entry.getWindSpeedKts(), "Wind Speed Kts", dateStr);
				internal.addValue(entry.getWindSpeedKmh(), "Wind Speed Kmh", dateStr);
				internal.addValue(entry.getRainSinceNineAM(), "Rain MM", dateStr);
			}
		}

		return historicalDataset;
	}

	/**
	 * Populates a dataset with values from forecastData array.
	 *
	 * @return A dataset for display inside of a chart. NOTE, this dataset is
	 *         just a convenience reference to the graphs internal dataset.
	 */
	private SlidingCategoryDataset populateForecastData() {
		DefaultCategoryDataset internal = new DefaultCategoryDataset();
		forecastDataset = new SlidingCategoryDataset(internal, 0, 5);

		if (forecastData != null) {
			for (WeatherDataPoint entry : forecastData.values()) {
				String dateStr = entry.getDate().toString();
				internal.addValue(entry.getTemp(), "Forecast - Temperature", dateStr);
				internal.addValue(entry.getAppTemp(), "Forecast - Apparent Temperature", dateStr);
				internal.addValue(entry.getDewPoint(), "Forecast - Dew Point", dateStr);
				internal.addValue(entry.getWindSpeedKts(), "Forecast - Wind Speed Kts", dateStr);
				internal.addValue(entry.getWindSpeedKmh(), "Forecast - Wind Speed Kmh", dateStr);
				internal.addValue(entry.getRainSinceNineAM(), "Forecast - Rain MM", dateStr);
			}
		}

		return historicalDataset;
	}

	private void initializeListeners() {
		tempToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(0, !renderer.isSeriesVisible(0));
				renderer.setSeriesVisibleInLegend(0, true, false);
			}
		});

		appTempToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(1, !renderer.isSeriesVisible(1));
				renderer.setSeriesVisibleInLegend(1, true, false);
			}
		});

		dewPointToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(2, !renderer.isSeriesVisible(2));
				renderer.setSeriesVisibleInLegend(2, true, false);
			}
		});

		wSpdKtsToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(3, !renderer.isSeriesVisible(3));
				renderer.setSeriesVisibleInLegend(3, true, false);
			}
		});

		wSpdKmhToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(4, !renderer.isSeriesVisible(4));
				renderer.setSeriesVisibleInLegend(4, true, false);
			}
		});

		rainToggle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryPlot plot = historicalGraph.getCategoryPlot();

				CategoryItemRenderer renderer = plot.getRenderer();
				renderer.setSeriesVisible(5, !renderer.isSeriesVisible(5));
				renderer.setSeriesVisibleInLegend(5, true, false);
			}
		});
	}

	@Override
	public void Refresh() {
		populateHistoricalData();
		historicalGraph.fireChartChanged();
	}
}
