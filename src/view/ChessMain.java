package view;

import javax.swing.SwingUtilities;

public class ChessMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub				
				new ChessLaunch();
			}
		});
	}
}
