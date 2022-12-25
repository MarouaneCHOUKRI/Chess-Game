package model;

import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}

	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		Piece possiblePiece;
		possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		if(possiblePiece != null) {
		}
		else possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
		
		if (possiblePiece != null) {
			
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		
			if (this.isBlanche() && !possiblePiece.isBlanche() && y_dest == getY() - 1 && x_dest == getX())
				return false;
			if (this.isBlanche() && !possiblePiece.isBlanche() && y_dest == getY() - 1
					&& (x_dest == getX() - 1 || x_dest == getX() + 1))
				return true;

			if (!this.isBlanche() && possiblePiece.isBlanche() && y_dest == getY() + 1 && x_dest == getX())
				return false;
			if (!this.isBlanche() && possiblePiece.isBlanche() && y_dest == getY() + 1
					&& (x_dest == getX() - 1 || x_dest == getX() + 1))
				return true;

		}

		if (this.isBlanche()) {
			if (x_dest != getX() || y_dest > getY() || y_dest < getY() - 1)
				return false;
		} else {
			if (x_dest != getX() || y_dest < getY() || y_dest > getY() + 1)
				return false;
		}

		return true;
	}
	
	@Override
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
	     ArrayList arraylist = new ArrayList();
	     Piece possiblePiece;
	     possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
	     possiblePiece = noir.getPieceByXY(x_dest, y_dest);
	     
	     
	     if(blanche.getPieceByXY(getX(), getY()-1) == null && noir.getPieceByXY(getX(), getY()-1) == null) {
	    	 arraylist.add(getX());   
			 arraylist.add(getY()-1); 
	     }
	     
	     if (noir.getPieceByXY(getX()+1, getY()-1) != null) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()-1); 
	     }
	     
	     if (noir.getPieceByXY(getX()-1, getY()-1) != null) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()-1); 
	     }
	
		 return arraylist; 
	}
	
}
