package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import model.WeatherStationEntry;

public class WeatherStationDataGraph extends JFrame
{
	private static final long serialVersionUID = -8115274594459474258L;
	
	List<WeatherStationEntry> entries;
	
	public WeatherStationDataGraph()
	{
		this.setMaximumSize(new Dimension(600,600));
	}
	
	public WeatherStationDataGraph(String name, List<WeatherStationEntry> data)
	{
		this();
		
		this.entries = data;
	}
	
	
}
