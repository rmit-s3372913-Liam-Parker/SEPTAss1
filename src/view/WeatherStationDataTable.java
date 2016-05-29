package view;

import interfaces.IWeatherSystemCallback;
import model.ForecastDataPoint;
import model.WeatherDataPoint;
import model.WeatherStation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;

public class WeatherStationDataTable extends JPanel implements IWeatherSystemCallback {
    private static final long serialVersionUID = 5280729831640510542L;

    private WeatherStation station;

    private JList historical;
    private JList forecast;

    public WeatherStationDataTable(WeatherStation station)
    {
        super();
        this.station = station;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        // Setup forecast pane
        forecast = new JList();
        JPanel forecastPanel = new JPanel(new BorderLayout());
        JLabel forecastTitle = new JLabel("Forecast Data:");
        forecastTitle.setVerticalTextPosition(JLabel.CENTER);
        forecastTitle.setHorizontalTextPosition(JLabel.LEFT);
        forecastPanel.add(forecastTitle, BorderLayout.NORTH);
        forecastPanel.add(new JScrollPane(forecast), BorderLayout.CENTER);

        // Setup historical pane
        historical = new JList();
        JPanel historicalPanel = new JPanel(new BorderLayout());
        JLabel historicalTitle = new JLabel("Historical Data:");
        historicalTitle.setVerticalTextPosition(JLabel.CENTER);
        historicalTitle.setHorizontalTextPosition(JLabel.LEFT);
        historicalPanel.add(historicalTitle, BorderLayout.NORTH);
        historicalPanel.add(new JScrollPane(historical), BorderLayout.CENTER);

        // Set content into main panel
        content.add(forecastPanel);

        content.add(historicalPanel);
        content.setBackground(new Color(255, 0, 186,255));
        this.add(content);

        updateAll();
    }

    @Override
    public void Refresh() {
        updateAll();
        this.revalidate();
    }

    /**
     * Convenience method when users desire calling both table update functions.
     */
    private void updateAll()
    {
        updateForecastTable();
        updateHistoricalTable();
    }

    /**
     * Updates the forecast JList with latest data model.
     */
    private void updateForecastTable() {
        Map<Date, ForecastDataPoint> forecastEntries = station.getForecastDataPoints();
        DefaultListModel model = new DefaultListModel<String>();

        for(Map.Entry<Date, ForecastDataPoint> entry : forecastEntries.entrySet()) {
            model.addElement(entry.getValue().toString());
        }
        forecast.setModel(model);
        forecast.repaint();
    }

    /**
     * Updates the historical JList with latest data model.
     */
    private void updateHistoricalTable() {
        Map<Date, WeatherDataPoint> forecastEntries = station.getHistoricalDataPoints();
        DefaultListModel model = new DefaultListModel<String>();

        for(Map.Entry<Date, WeatherDataPoint> entry : forecastEntries.entrySet()) {
            model.addElement(entry.getValue().toString());
        }
        historical.setModel(model);
        historical.repaint();
    }
}