package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class FavoriteButton extends AbstractCellEditor implements TableCellEditor, ActionListener 
{
	private static final long serialVersionUID = -4737975039746081012L;

	JButton button;
	
	public FavoriteButton()
	{
		button = new JButton("Favorite");
		
		button.addActionListener(this);
		button.setBorderPainted(false);
	}
	
	@Override
	public Object getCellEditorValue() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println("sfasdf");
		fireEditingStopped();
	}

	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
