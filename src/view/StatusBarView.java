package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import interfaces.WeatherSystem;

public class StatusBarView extends JPanel {
	private static final long serialVersionUID = -7934671678344118450L;

	static JProgressBar progressBar = new JProgressBar();;

	private static final String IDLE = "Idle";
	private static final String ACTIVE = "Fetching Weather Data";

	public StatusBarView(WeatherSystem system) {
		this.setLayout(new BorderLayout());

		// Initialize progress bar
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setString(IDLE);
		progressBar.setIndeterminate(false);
		this.add(progressBar, BorderLayout.WEST);
	}

	public static void SetBusy(boolean busy) {
		progressBar.setIndeterminate(busy);

		if (busy)
			progressBar.setString(ACTIVE);
		else
			progressBar.setString(IDLE);
	}
}
