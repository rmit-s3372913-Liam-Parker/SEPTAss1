package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.json.JSONObject;

import model.WeatherStation;
import model.WeatherSystem;


public class FavouritesView extends JFrame implements IJsonSerializable, IRefreshable
{
private static final long serialVersionUID = 1L;
	
	static String[] columns = {"", "Station"};
	
	WeatherSystem system;
	
	JTable table;
	JScrollPane scrollPane;
	JPanel panel = new JPanel(new GridLayout());
	
	public FavouritesView(WeatherSystem system)
	{
		this.system = system;
		initializeWindow();
	}
	
	/**
	 * Initialises the window/frame.
	 */
	private void initializeWindow()
	{
		buildTable();
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		this.add(panel);
		this.setTitle("Weather Stations | Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
	}
	
	/**
	 * Represents the view of the weather stations through a table.
	 */
	private void buildTable()
	{	
		String[] columns = {"", "Station", "Favourite"};
		
		ArrayList<WeatherStation> stations = (ArrayList<WeatherStation>) system.getFavoriteStations();
		int stationCount = stations.size();
		String[][] data = new String[stationCount][3];
		
		// Set station data column
		for(int i = 0; i < stationCount; ++i)
		{
			data[i][1] = stations.get(i).getName();
		}
		
		table = new JTable(data, columns)
		{
			private static final long serialVersionUID = 1L;
			
			/**
			 * Disables editing the table.
			 * @return False to stop users from editing data.
			 * @param data Collect the number of cells from the array.
			 * @param columns Collects the number of cells from the array.
			 */
		
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
			
			
			/**
			 * Represents the colour of the table.
			 * @param r Object rendered for cells in JTable.
			 * @param data The row of the cell to render.
			 * @param columns The column of the cell to render.
			 * @return Cells of the table and applies the colours.
			 * 
			 */
					
			public Component prepareRenderer(TableCellRenderer r, int data, int columns)
			{
				Component cell = super.prepareRenderer(r, data, columns);
				
				if(data % 2 == 0)
				{
					cell.setBackground(Color.WHITE);
				}
				else
				{
					cell.setBackground(Color.LIGHT_GRAY);
				}
				
				if(isCellSelected(data, columns))
				{
					cell.setBackground(Color.ORANGE);
				}
				
				return cell;
			}
			
			/**
			 * Indicates the width of the viewport that determines the 
			 * width of the table.
			 * @return 
			 */
			
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
		};
		
		// Set favorite button column
		TableColumn buttonColumn = table.getColumnModel().getColumn(0);
		buttonColumn.setCellEditor(new FavoriteButton());
		
		table.setFillsViewportHeight(true);
	}
	
	@Override
	public void Refresh() 
	{
		// TODO Regrab data
		
	}

	@Override
	public JSONObject SaveToJsonObject() 
	{
		JSONObject object = new JSONObject();
		
		object.put("windowPosX", this.getX());
		object.put("windowPosY", this.getY());
		
		object.put("windowWidth", this.getWidth());
		object.put("windowHeight", this.getHeight());
		
		//TODO any other values to save?
		
		return object;
	}

	@Override
	public void LoadFromJsonObject(JSONObject obj) 
	{
		this.setBounds(obj.getInt("windowPosX"), obj.getInt("windowPosY"),
				obj.getInt("windowWidth"), obj.getInt("windowHeight"));
	}
}
