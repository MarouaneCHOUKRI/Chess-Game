package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ChessController;
import model.Piece;

public class ChangePiece extends JOptionPane {

	private JPanel panel = new JPanel();

	public ChangePiece(Piece piece, ChessController chessController) {
		// TODO Auto-generated constructors stub
		panel.setLayout(new GridLayout(1, 4));

		JButton bishopImg = new JButton();
		bishopImg.setIcon(new ImageIcon(
				new ImageIcon("images/bishop_white.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		bishopImg.setBackground(new Color(0xeeeed2));
		
		JButton knightImg = new JButton();
		knightImg.setIcon(new ImageIcon(
				new ImageIcon("images/knight_white.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		knightImg.setBackground(new Color(0xeeeed2));
		
		JButton queenImg = new JButton();
		queenImg.setIcon(new ImageIcon(
				new ImageIcon("images/queen_white.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		queenImg.setBackground(new Color(0xeeeed2));
		
		JButton rookImg = new JButton();
		rookImg.setIcon(new ImageIcon(
				new ImageIcon("images/rook_white.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		rookImg.setBackground(new Color(0xeeeed2));

		panel.add(bishopImg);
		panel.add(knightImg);
		panel.add(queenImg);
		panel.add(rookImg);
		
		panel.setPreferredSize(new Dimension(400, 100));
		
		String[] pieces = {"Fou", "Cavalier", "Dame", "Tour"};

		int result = JOptionPane.showOptionDialog(null, panel, "Choisir une piece", 0, 0,
				null, pieces , pieces[0]);
		
		switch (result) {
		case 0:
			chessController.changePiece(piece, "Bishop");
			break;
		case 1:
			chessController.changePiece(piece, "Knight");
			break;
		case 2:
			chessController.changePiece(piece, "Queen");
			break;
		case 3:
			chessController.changePiece(piece, "Rook");
			break;
		}
	}
}
