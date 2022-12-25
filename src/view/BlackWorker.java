package view;

import java.awt.Color;
import java.awt.Image;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import controller.ChessController;
import model.ListPieces;
import model.Piece;

public class BlackWorker extends SwingWorker<Integer, Void> {

	private ChessController chessController;
	private TimerThread timerBlanche;

	public BlackWorker(ChessController chessController, TimerThread timerBlanche) {
		// TODO Auto-generated constructor stub
		this.chessController = chessController;
		this.timerBlanche = timerBlanche;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		int rand = new Random().ints(3, 6).findFirst().getAsInt();
		Thread.sleep(rand*1000);
		System.out.println("COMPUTER TURN !");
		chessController.executeOpponent();
		return null;
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		timerBlanche.execute();
	}
}
