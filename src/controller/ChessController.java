package controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.Bishop;
import model.King;
import model.Knight;
import model.ListPieces;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;
import view.ChangePiece;
import view.GameOver;
import view.KingDied;

public class ChessController implements ActionListener {

	private final static int LIGNES = 7;
	private final static int COLONNES = 8;

	private JFrame frame;
	private ListPieces piecesBlanche;
	private ListPieces piecesNoir;
	private JButton carres[][];
	private Piece activePiece = null;
	private boolean myTurn = true;
	private boolean computerTurn = false;

	public ChessController(JFrame frame, ListPieces piecesBlanche, ListPieces piecesNoir) {
		// TODO Auto-generated constructor stub
		this.piecesBlanche = piecesBlanche;
		this.piecesNoir = piecesNoir;
		this.frame = frame;
	}

	public void setButtons(JButton[][] carres) {
		this.carres = carres;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (computerTurn)
			return;
		JButton click = (JButton) e.getSource();
		int clickedLigne = click.getY() / click.getSize().height;
		int clickedCol = click.getX() / click.getSize().width;

		if (activePiece == null && !clickOnWhite(clickedCol, clickedLigne)) {
			return;
		}

		if (!myTurn) {
			boolean possiblePiece = activePiece.isPossible(clickedCol, clickedLigne, piecesBlanche, piecesNoir);
			int position = piecesBlanche.indexOfPiece(activePiece);
			if (possiblePiece) {
				if (clickedLigne == 0 && activePiece instanceof Pawn) {
					new ChangePiece(activePiece, this);
					System.out.println(piecesBlanche.getPiece(position).getClass());
				}
				Piece p = piecesNoir.getPieceByXY(clickedCol, clickedLigne);
				if (p != null) {
					piecesNoir.removePiece(p);
				}
				piecesBlanche.getPiece(position).setXY(clickedCol, clickedLigne, activePiece);
				System.out.println("SET IT");
			} else {
				if (clickedLigne == 0 && activePiece instanceof Pawn) {
					new ChangePiece(activePiece, this);
					while (piecesBlanche.getPiece(position) instanceof Pawn)
						;
					System.out.println(piecesBlanche.getPiece(position).getClass());
				}
				piecesBlanche.getPiece(position).setXY(activePiece.getX(), activePiece.getY(), activePiece);
			}

			myTurn = true;
			activePiece = null;
		} else {
			ArrayList<Integer> Tab;
			click.setBackground(new Color(0xf6f669));
			activePiece = piecesBlanche.getPieceByXY(clickedCol, clickedLigne);
			Tab = activePiece.colorCase(clickedCol, clickedLigne, piecesBlanche, piecesNoir);
			int j = 1;
			for (int i = 0; i < Tab.size(); i = i + 2) {
				try {
					carres[Tab.get(j)][Tab.get(i)].setBackground(new Color(0xf6f669));
				} catch (Exception e1) {

				}
				j = j + 2;
			}
			myTurn = false;
		}
	}

	public void executeOpponent() {
		Random rand = new Random();
		int randX = -1;
		int randY = -1;
		int randX_dest = -1;
		int randY_dest = -1;

		boolean legalMove = false;

		while (!legalMove) {
			randX = rand.nextInt(8);
			randY = rand.nextInt(7);

			if (!clickOnNoir(randX, randY)) {
				continue;
			}

			Piece active = piecesNoir.getPieceByXY(randX, randY);
			int position = piecesNoir.indexOfPiece(active);

			randX_dest = rand.nextInt(8);
			randY_dest = rand.nextInt(7);

			if (!active.isPossible(randX_dest, randY_dest, piecesBlanche, piecesNoir)) {
				continue;
			}

			legalMove = true;
			Piece p = piecesBlanche.getPieceByXY(randX_dest, randY_dest);
			if (p != null) {
				piecesBlanche.removePiece(p);
			}
			piecesNoir.getPiece(position).setXY(randX_dest, randY_dest, active);
		}
	}

	public void changePiece(Piece p, String newType) {
		switch (newType) {
			case "Bishop":
				activePiece = new Bishop(p.getX(), p.getY(), true, "bishop_white.png");
				break;
			case "Knight":
				activePiece = new Knight(p.getX(), p.getY(), true, "knight_white.png");
				break;
			case "Queen":
				activePiece = new Queen(p.getX(), p.getY(), true, "queen_white.png");
				break;
			case "Rook":
				activePiece = new Rook(p.getX(), p.getY(), true, "rook_white.png");
				break;
		}
		System.out.println("CHANGED IT");
	}

	public void initBlanche() {
		System.out.println("Blanche Size : " + piecesBlanche.size());
		if (piecesBlanche.size() == 0) {
			piecesBlanche.addPiece(new Rook(0, LIGNES - 1, true, "rook_white.png"));
			piecesBlanche.addPiece(new Knight(1, LIGNES - 1, true, "knight_white.png"));
			piecesBlanche.addPiece(new Bishop(2, LIGNES - 1, true, "bishop_white.png"));
			piecesBlanche.addPiece(new King(3, LIGNES - 1, true, "king_white.png"));
			piecesBlanche.addPiece(new Queen(4, LIGNES - 1, true, "queen_white.png"));
			piecesBlanche.addPiece(new Bishop(5, LIGNES - 1, true, "bishop_white.png"));
			piecesBlanche.addPiece(new Knight(6, LIGNES - 1, true, "knight_white.png"));
			piecesBlanche.addPiece(new Rook(7, LIGNES - 1, true, "rook_white.png"));

			for (int i = 0; i < COLONNES; i++) {
				piecesBlanche.addPiece(new Pawn(i, LIGNES - 2, true, "pawn_white.png"));
			}
		}

		for (int i = 0; i < piecesBlanche.size(); i++) {
			int col = piecesBlanche.getPiece(i).getX();
			int row = piecesBlanche.getPiece(i).getY();
			Image icon = new ImageIcon("images/" + piecesBlanche.getPiece(i).getIcon()).getImage().getScaledInstance(60,
					60, Image.SCALE_SMOOTH);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
		}
	}

	public void initNoir() {
		if (piecesNoir.size() == 0) {
			piecesNoir.addPiece(new Rook(0, 0, false, "rook_black.png"));
			piecesNoir.addPiece(new Knight(1, 0, false, "knight_black.png"));
			piecesNoir.addPiece(new Bishop(2, 0, false, "bishop_black.png"));
			piecesNoir.addPiece(new King(3, 0, false, "king_black.png"));
			piecesNoir.addPiece(new Queen(4, 0, false, "queen_black.png"));
			piecesNoir.addPiece(new Bishop(5, 0, false, "bishop_black.png"));
			piecesNoir.addPiece(new Knight(6, 0, false, "knight_black.png"));
			piecesNoir.addPiece(new Rook(7, 0, false, "rook_black.png"));

			for (int i = 0; i < COLONNES; i++) {
				piecesNoir.addPiece(new Pawn(i, 1, false, "pawn_black.png"));
			}
		}

		for (int i = 0; i < piecesNoir.size(); i++) {
			int col = piecesNoir.getPiece(i).getX();
			int row = piecesNoir.getPiece(i).getY();
			Image icon = new ImageIcon("images/" + piecesNoir.getPiece(i).getIcon()).getImage().getScaledInstance(60,
					60, Image.SCALE_SMOOTH);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
		}
	}

	public void close() {
		frame.dispose();
	}

	private boolean clickOnWhite(int x, int y) {
		if (piecesBlanche.getPieceByXY(x, y) != null)
			return true;
		return false;
	}

	private boolean clickOnNoir(int x, int y) {
		if (piecesNoir.getPieceByXY(x, y) != null)
			return true;
		return false;
	}

	public boolean isComputerTurn() {
		return computerTurn;
	}

	public void setComputerTurn(boolean computerTurn) {
		this.computerTurn = computerTurn;
	}

}
