package view;

import java.awt.Color;
import java.awt.Image;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import controller.ChessController;
import model.ListPieces;
import model.Piece;

public class WhiteWorker extends SwingWorker<Integer, Void> {

	private int oldX;
	private int oldY;
	private Piece piece;
	private JButton[][] carres;
	private ListPieces piecesBlanche;
	private BlackWorker blackWorker;

	public WhiteWorker(int oldX, int oldY, Piece piece, JButton[][] carres, ListPieces piecesBlanche,
			BlackWorker blackWorker) {
		// TODO Auto-generated constructor stub
		this.oldX = oldX;
		this.oldY = oldY;
		this.piece = piece;
		this.carres = carres;
		this.piecesBlanche = piecesBlanche;
		this.blackWorker = blackWorker;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		Image icon;
		int index = piecesBlanche.indexOfPiece(piece);
		icon = new ImageIcon("images/" + piecesBlanche.getPiece(index).getIcon()).getImage().getScaledInstance(60, 60,
				Image.SCALE_SMOOTH);

		ImageIcon deplaceIcon = new ImageIcon(icon);
		carres[oldY][oldX].setIcon(null);
		carres[piece.getY()][piece.getX()].setIcon(deplaceIcon);
		return null;
	}

	@Override
	protected void done() {
		// TODO Auto-generated method stub
		blackWorker.execute();
	}
}
