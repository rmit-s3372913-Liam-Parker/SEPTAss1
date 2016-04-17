package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import model.WeatherStationDailyEntry;

public class WeatherStationDataGraph extends JFrame
{
	private static final long serialVersionUID = -8115274594459474258L;
	
	List<WeatherStationDailyEntry> entries;
	
	public WeatherStationDataGraph()
	{
		this.setMaximumSize(new Dimension(600,600));
	}
	
	public WeatherStationDataGraph(String name, List<WeatherStationDailyEntry> data)
	{
		this();
		
		this.entries = data;
	}
	
	
}
