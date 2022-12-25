package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.ChessController;
import controller.ChessEvent;
import controller.ChessEventListener;
import model.Bishop;
import model.King;
import model.Knight;
import model.ListPieces;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;
import workers.InitBlackWorker;
import workers.InitWhiteWorker;

public class Table extends JPanel implements ChessEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7642237496065653046L;
	private final static int LIGNES = 7;
	private final static int COLONNES = 8;
	private final static int WIDTH = 600;
	private final static int HEIGHT = 600;

	private JButton carres[][] = new JButton[LIGNES][COLONNES];

	private TimerThread timerBlanche;
	private TimerThread timerNoir;
	
	private ListPieces piecesBlanche;
	private ListPieces piecesNoir;

	private ChessController chessController;

	public Table(ChessController chessController, ListPieces piecesBlanche, ListPieces piecesNoir,
			TimerThread timerBlanche, TimerThread timerNoir) {
		this.timerBlanche = timerBlanche;
		this.timerNoir = timerNoir;
		
		this.chessController = chessController;
		this.piecesBlanche = piecesBlanche;
		this.piecesNoir = piecesNoir;
		this.chessController.setButtons(carres);
		
		setLayout(new GridLayout(LIGNES, COLONNES));
		for (int i = 0; i < LIGNES; i++) {
			for (int j = 0; j < COLONNES; j++) {
				carres[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					carres[i][j].setBackground(new Color(0x769656));
				} else {
					carres[i][j].setBackground(new Color(0xeeeed2));
				}
				add(carres[i][j]);
				carres[i][j].addActionListener(chessController);
				carres[i][j].setFocusPainted(false);
			}
		}
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void noirListener(ChessEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof Piece) {
			Vector<Object> donne = (Vector<Object>) evt.getDonnee();
			int oldX = Integer.parseInt((String) donne.get(0));
			int oldY = Integer.parseInt((String) donne.get(1));
			Piece newPiece = (Piece) donne.get(2);

			Image icon;
			int index = piecesNoir.indexOfPiece(newPiece);
			icon = new ImageIcon("images/" + piecesNoir.getPiece(index).getIcon()).getImage().getScaledInstance(60, 60,
					Image.SCALE_SMOOTH);

			ImageIcon deplaceIcon = new ImageIcon(icon);
			carres[oldY][oldX].setIcon(null);
			carres[newPiece.getY()][newPiece.getX()].setIcon(deplaceIcon);
			
			if(!this.piecesBlanche.findKing()) {
				timerNoir.cancel(true);
				new KingDied(new King(0, 0, true, null), chessController);
				return;
			}
			
			if (oldX != newPiece.getX() || oldY != newPiece.getY()) {
				timerNoir.cancel(true);
				chessController.setComputerTurn(false);
				timerBlanche = new TimerThread(timerBlanche.getTimerPanel(), chessController);
				timerBlanche.execute();
			}
		}
	}

	@Override
	public void blancheListener(ChessEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof Piece) {
			Vector<Object> donne = (Vector<Object>) evt.getDonnee();
			int oldX = Integer.parseInt((String) donne.get(0));
			int oldY = Integer.parseInt((String) donne.get(1));
			Piece newPiece = (Piece) donne.get(2);
			
			if(!this.piecesNoir.findKing()) {
				timerBlanche.cancel(true);
				new KingDied(new King(0, 0, false, null), chessController);
				return;
			}

			if (oldX != newPiece.getX() || oldY != newPiece.getY()) {
				BlackWorker blackWorker = new BlackWorker(chessController, timerBlanche);
				WhiteWorker whiteWorker = new WhiteWorker(oldX, oldY, newPiece, carres, piecesBlanche, blackWorker);
				whiteWorker.execute();
				timerBlanche.cancel(true);
				chessController.setComputerTurn(true);
				timerNoir = new TimerThread(timerNoir.getTimerPanel(), chessController);
				timerNoir.execute();
			}
			for (int i = 0; i < LIGNES; i++) {
				for (int j = 0; j < COLONNES; j++) {
					if ((i + j) % 2 != 0) {
						carres[i][j].setBackground(new Color(0x769656));
					} else {
						carres[i][j].setBackground(new Color(0xeeeed2));
					}
				}
			}
			System.out.println("Tableau Blanche : " + piecesBlanche.size());
		}
	}

	public void initTable() {
		initPieces();
	}

	private void initPieces() {
		for (int i = 0; i < piecesBlanche.size(); i++) {
			piecesBlanche.getPiece(i).addChessEventListener(this);
		}

		for (int i = 0; i < piecesNoir.size(); i++) {
			piecesNoir.getPiece(i).addChessEventListener(this);
		}
	}
}
