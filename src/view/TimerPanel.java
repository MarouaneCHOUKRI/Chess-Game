package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String timerName = "";
	private JPanel panelTime = new JPanel();
	private JLabel timerLabel = new JLabel();
	private JPanel tourPlayer = new JPanel();
	private final int maxTime = 20;
	private int count = 0;
	private boolean tourVisibility = false;
	
	public TimerPanel(String timerName) {
		// TODO Auto-generated constructor stub
		this.timerName = timerName;
		int timeInSeconds = maxTime % 60;
		int timeInMinutes = maxTime / 60;
		String timeSeconds = "" + timeInSeconds;
		if(timeInSeconds < 10)
			timeSeconds = "0" + timeSeconds;
		timerLabel.setText("0" + timeInMinutes + ":" + timeSeconds);
		timerLabel.setFont(new Font("LCD", Font.BOLD, 45));
		tourPlayer.setBackground(Color.BLACK);
		tourPlayer.setPreferredSize(new Dimension(30, 30));
		tourPlayer.setVisible(false);
		panelTime.add(timerLabel);
		setBorder(BorderFactory.createTitledBorder(timerName));
		setPreferredSize(new Dimension(200, 90));
		add(panelTime, BorderLayout.LINE_START);
		add(tourPlayer, BorderLayout.CENTER);
	}
	
	public void setTimer() {
		this.count = this.count + 1;
		int timeInSeconds = (maxTime - this.count) % 60;
		int timeInMinutes = (maxTime - this.count) / 60;
		String timeSeconds = "" + timeInSeconds;
		if(timeInSeconds < 10)
			timeSeconds = "0" + timeSeconds;
		if((maxTime - count) <= 10 && (maxTime - count) > 0)
			timerLabel.setForeground(Color.ORANGE);
		timerLabel.setText("0" + timeInMinutes + ":" + timeSeconds);
	}
	
	public void timerEnded() {
		timerLabel.setForeground(Color.RED);
	}
	
	public void resetTimer() {
		this.count = 0;
		int timeInSeconds = (maxTime - this.count) % 60;
		int timeInMinutes = (maxTime - this.count) / 60;
		String timeSeconds = "" + timeInSeconds;
		if(timeInSeconds < 10)
			timeSeconds = "0" + timeSeconds;
		timerLabel.setText("0" + timeInMinutes + ":" + timeSeconds);
		timerLabel.setForeground(Color.BLACK);
	}
	
	public void switchTour() {
		if(tourVisibility) {
			tourVisibility = false;
			tourPlayer.setVisible(false);
		} else {
			tourVisibility = true;
			tourPlayer.setVisible(true);
		}
	}
	
	public int getMaxTime() {
		return this.maxTime;
	}
}
