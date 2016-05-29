package view;

import controller.GraphToggleController;
import interfaces.IWeatherSystemCallback;
import model.WeatherDataPoint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;

import javax.swing.*;
import java.awt.*;
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

	private JToggleButton tempToggle = new JToggleButton("Temperature", true);
	private JToggleButton appTempToggle = new JToggleButton("Apparent Temperature", true);
	private JToggleButton dewPointToggle = new JToggleButton("Dew Point", true);
	private JToggleButton wSpdKtsToggle = new JToggleButton("Wind Speed Kts", true);
	private JToggleButton wSpdKmhToggle = new JToggleButton("Wind Speed Kmh", true);
	private JToggleButton rainToggle = new JToggleButton("Rain MM", true);

	private JFreeChart historicalGraph;

    JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);

    private SlidingCategoryDataset historicalDataset;

	/**
	 * Constructor for a weatherStation historicalGraph with name and data
	 * 
	 * @param name
	 *            The name of the station
	 * @param historical
	 *            The data for the historicalGraph to display
	 */
	public WeatherStationDataGraph(String name, Map<Date, WeatherDataPoint> historical) {
		super(name);
		this.historicalData = historical;

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
        //TODO: Move to controller
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(historicalData.size() <= 1) return;
                scrollBar.setMaximum((historicalData.size() - 1));
                historicalDataset.setFirstCategoryIndex(e.getValue());
            }
        });

        // We add the toggle and scroll panes together to form the control panel
        JPanel controlPane = new JPanel(new BorderLayout());
        controlPane.add(scrollBar, BorderLayout.NORTH);
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

	private void initializeListeners() {
		GraphToggleController controller = new GraphToggleController(historicalGraph);

		tempToggle.addActionListener(controller);
		appTempToggle.addActionListener(controller);
		dewPointToggle.addActionListener(controller);
		wSpdKtsToggle.addActionListener(controller);
		wSpdKmhToggle.addActionListener(controller);
		rainToggle.addActionListener(controller);
	}

	@Override
	public void Refresh() {
		populateHistoricalData();
		historicalGraph.fireChartChanged();
	}
}
