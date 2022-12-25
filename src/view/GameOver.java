package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ChessController;
import workers.InitBlackWorker;
import workers.InitWhiteWorker;

public class GameOver extends JOptionPane {
	
	private JLabel message = new JLabel();
	private JPanel panel = new JPanel();
	
	public GameOver(ChessController chessController, TimerPanel time) {
		// TODO Auto-generated constructor stub
		message.setText("GAME OVER");
		message.setFont(new Font("Evil Empire", Font.BOLD, 30));
		panel.add(message);
		setPreferredSize(new Dimension(500, 400));
		
		int result = JOptionPane.showOptionDialog(null, panel, "Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new Object[] {"Rejouer", "Quitter"}, null);
		
		if(result == JOptionPane.YES_OPTION) {
			new ChessLaunch();
			chessController.close();
		} else {
			System.exit(0);
		}
	}
}
