package tests;

import model.ForecastFactory;
import model.ForecastIOFactory;
import model.OpenWeatherMapFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ForecastFactoryTest
{
    ForecastFactory forecastIO;
    ForecastFactory openWeather;

    public final String lat = "37.8";
    public final String lon = "144.9";

    @Before
    public void setUp() throws Exception {
        forecastIO = new ForecastIOFactory(lat,lon);
        openWeather = new OpenWeatherMapFactory(lat,lon);
    }

    @Test
    public void checkFactoryImplementations() {
        // Assert the concrete implementations of factory correctly override default
        // behaviour which returns null.
        assertNotNull("May be false positive, check connection to internet.", forecastIO);
        assertNotNull("May be false positive, check connection to internet.", openWeather);

        assertNotNull(forecastIO.GetWeatherForecast());
        assertNotNull(openWeather.GetWeatherForecast());
    }
}
