import java.awt.*;

public class ProgramEntry 
{
	public static void main(String[] args) 
	{
		MainView frame = new MainView();
		frame.ShowBorderLayout();
		
		Color c = new Color(255,255,255);
		frame.getContentPane().setBackground(c);
	}
}
