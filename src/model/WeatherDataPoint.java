package model;

import java.util.Date;

/**
 * Represents a single day of weather information.
 */
public class WeatherDataPoint {

	// Temperatures and humidity
	private Date date;
	private float temp;
	private float appTemp;
	private float dewPoint;
	private int relHum;
	private float deltaT;

	// Wind & gust
	private CompassDirection windDir;
	private int windSpeedKmh;

	private int gustSpeedKmh;
	private int windSpeedKts;
	private int gustSpeedKts;

	// Pressure
	private float pressQNH;
	private float pressMSL;

	// Precipitation
	private float rainSinceNineAM;

	public WeatherDataPoint(Date date, float temp, float appTemp, float dewPoint, int relHum, float deltaT,
			CompassDirection windDir, int windSpeedKm, int gustSpeedKm, int windSpeedKts, int gustSpeedKts,
			float pressQNH, float pressMSL, float rainSinceNineAM) {
		this.date = date;
		this.temp = temp;
		this.appTemp = appTemp;
		this.dewPoint = dewPoint;
		this.relHum = relHum;
		this.deltaT = deltaT;

		this.windDir = windDir;
		this.windSpeedKmh = windSpeedKm;
		this.gustSpeedKmh = gustSpeedKm;
		this.windSpeedKts = windSpeedKts;
		this.gustSpeedKts = gustSpeedKts;

		this.pressQNH = pressQNH;
		this.pressMSL = pressMSL;

		this.rainSinceNineAM = rainSinceNineAM;
	}

	/**
	 * @return The date and time of this snapshot
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return The temperature at the time of this snapshot.
	 */
	public float getTemp() {
		return temp;
	}
	
	public float getAppTemp() {
		return appTemp;
	}

	public float getDewPoint() {
		return dewPoint;
	}

	public int getRelHum() {
		return relHum;
	}

	public float getDeltaT() {
		return deltaT;
	}

	public CompassDirection getWindDir() {
		return windDir;
	}

	public int getWindSpeedKmh() {
		return windSpeedKmh;
	}

	public int getGustSpeedKmh() {
		return gustSpeedKmh;
	}

	public int getWindSpeedKts() {
		return windSpeedKts;
	}

	public int getGustSpeedKts() {
		return gustSpeedKts;
	}

	public float getPressQNH() {
		return pressQNH;
	}

	public float getPressMSL() {
		return pressMSL;
	}

	public float getRainSinceNineAM() {
		return rainSinceNineAM;
	}
}
