package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import controller.ChessEvent;
import controller.ChessEventListener;
import controller.ChessEventNotifieur;

public class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean blanche;
	private String icon;
	private transient ChessEventNotifieur notifieur = new ChessEventNotifieur();
	
	public Piece(int x, int y, boolean blanche, String icon) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.blanche = blanche;
		this.icon = icon;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Piece getXY() {
		return this;
	}
	
	public void setXY(int x_dest, int y_dest, Piece p) {
		String oldX = "" + p.getX();
		String oldY = "" + p.getY();
		
		Vector<Object> piece = new Vector<Object>(3);
		piece.add(oldX); piece.add(oldY);
		
		this.x = x_dest;
		this.y = y_dest;
		
		piece.add(getXY());
		if(this.isBlanche()) {
			notifieur.diffuserBlancheChessEvent(new ChessEvent(this, piece));
		} else {
			notifieur.diffuserNoirChessEvent(new ChessEvent(this, piece));
		}
		
	}

	public boolean isBlanche() {
		return blanche;
	}

	public void setBlanche(boolean blanche) {
		this.blanche = blanche;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public void addChessEventListener(ChessEventListener listener) {
		if(this.isBlanche()) 
			notifieur.addChessBlancheEventListener(listener);
		else
			notifieur.addChessNoirEventListener(listener);
	}
	
	public boolean isPossible(int x_dest, int y_dest, ListPieces piecesBlanche, ListPieces piecesNoir) {
		return false;
	}
	
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		return numbers;
	}


	public boolean isBlocking(int x, int y, ListPieces listBlanche, ListPieces listNoir) {
		if(listBlanche.getPieceByXY(x, y) != null) return true;
		if(listNoir.getPieceByXY(x, y) != null) return true;
		return false;
	}

	@Override
	public String toString() {
		return "Piece [x=" + x + ", y=" + y + ", blanche=" + blanche + "]";
	}
	
}
