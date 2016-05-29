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
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

        // Setup forecast pane
        forecast = new JPanel();
        JPanel forecastPanel = new JPanel(new BorderLayout());
        forecastPanel.add(new JLabel("Forecast Data"), BorderLayout.NORTH);
        forecastPanel.add(forecast, BorderLayout.CENTER);

        // Setup historical pane
        historical = new JPanel();
        JPanel historicalPanel = new JPanel(new BorderLayout());
        historicalPanel.add(new JLabel("Historical Data"), BorderLayout.NORTH);
        historicalPanel.add(historical, BorderLayout.CENTER);

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