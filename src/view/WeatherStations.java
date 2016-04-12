package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class WeatherStations extends JFrame
{
	JTable table;
	JPanel panel = new JPanel();
	JFrame frame = new JFrame();
	
	public WeatherStations()
	{
		
		String[] columns = {"Name", "Age", "Gender"};
		
		String[][] data = {{"John", "18", "Male"},
				{"Daisy", "19", "Female"},
				{"Dave", "23", "Male"},
				{"Jake", "30", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				{"Dave", "23", "Male"},
				};
		
		table = new JTable(data, columns)
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
			
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
		};
		
		table.setPreferredScrollableViewportSize(new Dimension(450, 63));
		table.setFillsViewportHeight(true);
		
//		JScrollPane scrollPane = new JScrollPane(table);
//		add(scrollPane);
		
	
		
		
		
		
		
		
//		panel1.setLayout(new GridLayout(6,2));
//		setBounds(5,5,100,100);
//		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		JScrollPane scrollPane = new JScrollPane();
//		
//		
//		for(int i= 0; i<=9; i++)
//		{
//			panel1.add(new JLabel("" + i)); //numbers each cell
//		}
		
		this.add(new JScrollPane(table));
		this.add(table);
		this.setTitle("Weather Stations | Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
		
	}
	
	
	
}
