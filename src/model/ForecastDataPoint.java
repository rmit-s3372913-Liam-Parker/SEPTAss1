package model;

import java.util.Date;

public class ForecastDataPoint
{
		//Summary
		private Date date;
		private String summary;

		// Temperatures and humidity
		private float temp;
		//private float appTemp; //removing because only forecast.io has this, weathermap does not
		private int relHum;

		// Wind & gust
		private float windDir;
		private float windSpeedKmh;

		// Pressure
		private float pressure;

		// Precipitation

		public ForecastDataPoint(Date date, String summary, float temp, int relHum, float windDir, 
				float windSpeedKmh, float pressure)
		{
				this.date = date;
				this.summary = summary;
				this.temp = temp;
				this.relHum = relHum;
				this.windDir = windDir;
				this.windSpeedKmh = windSpeedKmh;
				this.pressure = pressure;
		}

		public Date getDate()
		{
			return date;
		}
		
		public String getSummary()
		{
			return summary;
		}
		
		public float temp()
		{
			return temp;
		}
		
		public int relHum()
		{
			return relHum;
		}
		
		public float windDir()
		{
			return windDir;
		}
		
		public float windSpeedKmh()
		{
			return windSpeedKmh;
		}
		
		public float pressure()
		{
			return pressure;
		}

	
}
