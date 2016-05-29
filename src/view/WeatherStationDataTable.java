package view;

import interfaces.IWeatherSystemCallback;
import model.WeatherDataPoint;
import model.WeatherStation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;

public class WeatherStationDataTable extends JPanel implements IWeatherSystemCallback {
    private static final long serialVersionUID = 5280729831640510542L;

    private WeatherStation station;

    private JPanel historical;
    private JPanel forecast;

    public WeatherStationDataTable(WeatherStation station)
    {
        super();
        this.station = station;

        JPanel content = new JPanel();

        // Setup forecast pane
        forecast = new JPanel();
        JPanel forecastPanel = new JPanel(new BorderLayout());
        JLabel forecastTitle = new JLabel("Forecast Data:");
        forecastTitle.setVerticalTextPosition(JLabel.CENTER);
        forecastTitle.setHorizontalTextPosition(JLabel.LEFT);
        forecastPanel.add(forecastTitle, BorderLayout.NORTH);
        forecastPanel.add(forecast, BorderLayout.CENTER);

        forecast.setBackground(new Color(110,255, 102,255));

        // Setup historical pane
        historical = new JPanel();
        JPanel historicalPanel = new JPanel(new BorderLayout());
        JLabel historicalTitle = new JLabel("Historical Data:");
        historicalTitle.setVerticalTextPosition(JLabel.CENTER);
        historicalTitle.setHorizontalTextPosition(JLabel.LEFT);
        historicalPanel.add(historicalTitle, BorderLayout.NORTH);
        historicalPanel.add(historical, BorderLayout.CENTER);

        forecast.setBackground(new Color(255, 98, 216,255));

        // Set content into main panel
        content.add(forecastPanel);
        content.add(historicalPanel);

        this.add(content);

        updateAll();
    }

    @Override
    public void Refresh() {
        updateAll();
        this.revalidate();
    }

    private void updateAll()
    {
        updateForecastTable();
        updateHistoricalTable();
    }

    private void updateForecastTable() {
        Map<Date, WeatherDataPoint> forecastEntries = station.getForecastDataPoints();

        forecast.removeAll();
        for(Map.Entry<Date, WeatherDataPoint> entry : forecastEntries.entrySet()) {
            forecast.add(new JLabel(entry.getValue().toString()));
        }
        forecast.repaint();
    }

    private void updateHistoricalTable() {
        Map<Date, WeatherDataPoint> forecastEntries = station.getForecastDataPoints();

        historical.removeAll();
        for(Map.Entry<Date, WeatherDataPoint> entry : forecastEntries.entrySet()) {
            historical.add(new JLabel(entry.getValue().toString()));
        }
        historical.repaint();
    }
}