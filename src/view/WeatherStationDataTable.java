package view;

import interfaces.IWeatherSystemCallback;
import model.WeatherDataPoint;
import model.WeatherStation;

import javax.swing.*;
import java.util.Date;
import java.util.Map;

public class WeatherStationDataTable extends JPanel implements IWeatherSystemCallback {
    private static final long serialVersionUID = 5280729831640510542L;

    private WeatherStation station;

    static String[] forecastCols = {"Date",
            "Temp",
            "App Temp",
            "Dew Point",
            "Rel Hum",
            "Wind Dir",
            "Wind Spd Kts",
            "Pressure QNH",
            "Rain MM"};

    private JTable forecastTable = new JTable(new Object[0][0], forecastCols);
    private JTable historicalTable = new JTable(new Object[0][0], forecastCols);

    public WeatherStationDataTable(WeatherStation station) {
        super();
        this.station = station;

        this.add(forecastTable);
        this.add(historicalTable);
        this.validate();
    }

    @Override
    public void Refresh() {
        this.removeAll();
        updateForecastTable();
        updateHistoricalTable();
        this.add(forecastTable);
        this.add(historicalTable);
        this.revalidate();
    }

    private void updateForecastTable() {
        Map<Date, WeatherDataPoint> data = station.getForecastDataPoints();
        Object[][] forecastDataRows = new Object[forecastCols.length][data.size()];

        int i = 0;
        for (Map.Entry<Date, WeatherDataPoint> entry : data.entrySet()) {
            forecastDataRows[0][i] = entry.getKey().toString();
            forecastDataRows[1][i] = entry.getValue().getTemp();
            forecastDataRows[2][i] = entry.getValue().getAppTemp();
            forecastDataRows[3][i] = entry.getValue().getDewPoint();
            forecastDataRows[4][i] = entry.getValue().getRelHum();
            forecastDataRows[5][i] = entry.getValue().getWindDir();
            forecastDataRows[6][i] = entry.getValue().getWindSpeedKts();
            forecastDataRows[7][i] = entry.getValue().getPressQNH();
            forecastDataRows[8][i] = entry.getValue().getRainSinceNineAM();

            i++;
        }

        forecastTable = new JTable(forecastDataRows, forecastCols);
    }

    private void updateHistoricalTable() {
        Map<Date, WeatherDataPoint> data = station.getHistoricalDataPoints();
        Object[][] historicalDataRows = new Object[forecastCols.length][data.size()];

        int i = 0;
        for (Map.Entry<Date, WeatherDataPoint> entry : data.entrySet()) {
            historicalDataRows[0][i] = entry.getKey().toString();
            historicalDataRows[1][i] = entry.getValue().getTemp();
            historicalDataRows[2][i] = entry.getValue().getAppTemp();
            historicalDataRows[3][i] = entry.getValue().getDewPoint();
            historicalDataRows[4][i] = entry.getValue().getRelHum();
            historicalDataRows[5][i] = entry.getValue().getWindDir();
            historicalDataRows[6][i] = entry.getValue().getWindSpeedKts();
            historicalDataRows[7][i] = entry.getValue().getPressQNH();
            historicalDataRows[8][i] = entry.getValue().getRainSinceNineAM();

            i++;
        }

        forecastTable = new JTable(historicalDataRows, forecastCols);
    }
}