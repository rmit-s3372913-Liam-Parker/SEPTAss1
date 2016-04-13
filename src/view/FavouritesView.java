package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;


public class FavouritesView extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	JTable table;
	JScrollPane scrollPane;
	JPanel panel = new JPanel(new GridLayout());
	
	
	public FavouritesView()
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
	
			private static final long serialVersionUID = 1L;

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
					cell.setBackground(Color.MAGENTA);
				}
				
				return cell;
			}
			
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
		};
		
		table.setPreferredScrollableViewportSize(new Dimension(400, 400));
		table.setFillsViewportHeight(true);

		
	
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		this.add(new JScrollPane(panel));
		this.setTitle("Weather Stations | Weather App");
		this.setSize(400,400); //needs to be changed
		this.setLocationRelativeTo(null); //centre the frame - needs to be changed
		this.setMinimumSize(new Dimension(400,400));
	}

}
