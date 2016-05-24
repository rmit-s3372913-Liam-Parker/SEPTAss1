package view;

import javax.swing.table.AbstractTableModel;

public class WeatherStationTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7872297024820398372L;

	private static String[] colNames = { "Date/Time", "Temperature (Celsius)", "Apparent Temperature (Celsius)",
			"Dew Point (Celsius)", "Relative Humidity (%)", "deltaT (Celsius)", "Wind Direction", "Wind Speed (kmh)",
			"Gust Speed (kmh)", "Wind Speed (kts)", "Gust Speed (kts)", "QNH Pressure(hPa)", "MSL Pressure (hPa)",
			"Rain since 9am (mm)" };

	private Object[][] data;

	public WeatherStationTableModel() {

	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}
