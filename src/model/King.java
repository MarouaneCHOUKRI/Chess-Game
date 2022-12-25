package model;

import java.util.ArrayList;

public class King extends Piece {

	public King(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}

	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		
		Piece possiblePiece;
		possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		if(possiblePiece != null) {
		}
		else possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		
		if( x_dest > getX()+1 || x_dest < getX()-1) return false;
		
		if(y_dest > getY()+1 || y_dest < getY()-1 ) return false;
		
		return true;
	}
	
	@Override
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
	     ArrayList<Integer> arraylist = new ArrayList<Integer>();
	     Piece possiblePiece;
	     possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
	     
	     if(possiblePiece.isPossible(getX()+1, getY()-1, blanche, noir)) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()-1);
	     }
		 
	     if(possiblePiece.isPossible(getX()+1, getY(), blanche, noir)) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()); 
	     }
	     
	     if(possiblePiece.isPossible(getX()+1, getY()+1, blanche, noir)) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()+1); 
	     }
	     
	     if(possiblePiece.isPossible(getX(), getY()-1, blanche, noir)) {
	    	 arraylist.add(getX());   
			 arraylist.add(getY()-1); 
	     }
	     
	     if(possiblePiece.isPossible(getX(), getY()+1, blanche, noir)) {
	    	 arraylist.add(getX());   
			 arraylist.add(getY()+1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()-1, getY()-1, blanche, noir)) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()-1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()-1, getY(), blanche, noir)) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()); 
	     }
	     if(possiblePiece.isPossible(getX()-1, getY()+1, blanche, noir)) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()+1); 
	     }
		 return arraylist; 
	}
	

}
