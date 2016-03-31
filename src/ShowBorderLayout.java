import java.awt.*;
import javax.swing.*;

public class ShowBorderLayout extends JFrame 
{
	public void ShowBorderLayout()
	{
		
		JPanel panel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JLabel weather = new JLabel();
		
		JButton buttonLeft = new JButton("Weather Stations");
		JButton buttonRight = new JButton("Favourites");
		
		bottomPanel.add(buttonLeft);
		bottomPanel.add(buttonRight);
		
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("Welcome to the Weather App");
		topPanel.add(label);
		
		weather.setIcon(new ImageIcon("images/weather.png"));
		topPanel.add(weather);
		
		this.add(panel);
		this.setTitle("Weather App");
		this.setSize(400,400);
		this.setLocationRelativeTo(null); //centre the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(400,400));
		
		
	}

	
}
