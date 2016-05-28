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
            historicalDataRows[i][0] = entry.getKey().toString();
            historicalDataRows[i][1] = entry.getValue().getTemp();
            historicalDataRows[i][2] = entry.getValue().getAppTemp();
            historicalDataRows[i][3] = entry.getValue().getDewPoint();
            historicalDataRows[i][4] = entry.getValue().getRelHum();
            historicalDataRows[i][5] = entry.getValue().getWindDir();
            historicalDataRows[i][6] = entry.getValue().getWindSpeedKts();
            historicalDataRows[i][7] = entry.getValue().getPressQNH();
            historicalDataRows[i][8] = entry.getValue().getRainSinceNineAM();

            i++;
        }

        forecastTable = new JTable(historicalDataRows, forecastCols);
    }
}