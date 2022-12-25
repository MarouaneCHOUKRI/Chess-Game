package model;

import java.io.Serializable;
import java.util.Vector;

public class ListPieces implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Piece> pieces = new Vector<Piece>();
	private int size = 0;
	
	public int size() {
		return this.size;
	}
	public void addPiece(Piece piece) {
		pieces.add(piece);
		size = pieces.size();
	}
	
	public void removePiece(Piece piece) {
		pieces.removeElement(piece);
		size = pieces.size();
	}
	
	public void removeAllPiece() {
		pieces.removeAll(pieces);
	}
	
	public Vector<Piece> getPieces() {
		return this.pieces;
	}
	
	public Piece getPiece(int index) {
		return this.pieces.get(index);
	}
	
	public Piece getPieceByXY(int x, int y) {
		return pieces.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst().orElse(null);
	}
	
	public int indexOfPiece(Piece piece) {
		return pieces.indexOf(piece);
	}
	
	public boolean findKing() {
		for(Piece p : pieces) {
			if(p instanceof King) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		for(Piece p : pieces) {
			System.out.println(p);
		}
		return "Listes";
	}
	
}
