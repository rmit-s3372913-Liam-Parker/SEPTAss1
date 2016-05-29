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

    @Before
    public void setUp() throws Exception {
        forecastIO = new ForecastIOFactory("37.8","144.9");
        openWeather = new OpenWeatherMapFactory("37.8","144.9");
    }

    @Test
    public void checkFactoryImplementations() {
        // Assert the concrete implementations of factory correctly override default
        // behaviour which returns null.
        assertNotNull(forecastIO.GetWeatherForecast());
        assertNotNull(openWeather.GetWeatherForecast());
    }
}
