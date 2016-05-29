package tests;

import model.CompassDirection;
import model.WeatherDataPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WeatherDataPointTest
{
    WeatherDataPoint dataPoint;
    Date date = new Date();
    @Before
    public void setUp() throws Exception {
        dataPoint = new WeatherDataPoint(date, 15f,15f, 1f, 2, 2f, CompassDirection.CALM, 2, 2, 4, 4, 1000f, 1000f, 22);
    }

    @Test
    public void testConstructor() {
        assertEquals(dataPoint.getDate(), date);
        assertEquals(dataPoint.getTemp(), 15f, 0.1f);
        assertEquals(dataPoint.getAppTemp(), 15f, 0.1f);
        assertEquals(dataPoint.getDewPoint(), 1f, 0.1f);
        assertEquals(dataPoint.getRelHum(), 2);
        assertEquals(dataPoint.getDeltaT(), 2f, 0.1f);
        assertEquals(dataPoint.getWindDir(), CompassDirection.CALM);
        assertEquals(dataPoint.getWindSpeedKmh(), 2);
        assertEquals(dataPoint.getGustSpeedKmh(), 2);
        assertEquals(dataPoint.getWindSpeedKts(), 4);
        assertEquals(dataPoint.getGustSpeedKts(), 4);
        assertEquals(dataPoint.getPressQNH(), 1000f, 0.1f);
        assertEquals(dataPoint.getPressMSL(), 1000f, 0.1f);
        assertEquals(dataPoint.getRainSinceNineAM(), 22, 0.1);
    }
}
