package model;

import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		int x, y, x1, x2, x3, x4, y1, y2, y3, y4;

		//sdckjusigcgdsvgdf_vsei@ghqdg. ocm
		Piece possiblePiece;
		possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		if(possiblePiece != null) {
		}
		else possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		
		
		x = x_dest; y = y_dest;
		x1 = getX() + 1; x2 = getX() + 2 ; x3 = getX() - 1; x4 = getX() - 2;
		y1 = getY() + 1 ; y2 = getY() + 2 ; y3 = getY() - 1; y4 = getY() - 2;
		
		if((x != x1 || y != y4) && (x != x2 || y != y3 ) && (x != x2 || y != y1) && (x != x1 || y != y2) && 
				(x != x3 || y != y4) && (x != x4 || y != y3 ) && (x != x4 || y != y1) && (x != x3 || y != y2)) {
				return false;
		}
		
		return true;
	}
	
	@Override
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
	     ArrayList arraylist = new ArrayList();
	     Piece possiblePiece;
	     possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
	     
	     
	     if(possiblePiece.isPossible(getX()+1, getY()-2, blanche, noir)) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()-2); 
	     }
	     
	     if(possiblePiece.isPossible(getX()+2, getY()-1, blanche, noir)) {
	    	 arraylist.add(getX()+2);   
			 arraylist.add(getY()-1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()+2, getY()+1, blanche, noir)) {
	    	 arraylist.add(getX()+2);   
			 arraylist.add(getY()+1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()+1, getY()+2, blanche, noir)) {
	    	 arraylist.add(getX()+1);   
			 arraylist.add(getY()+2); 
	     }
	     //
	     if(possiblePiece.isPossible(getX()-1, getY()+2, blanche, noir)) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()+2); 
	     }
	     
	     if(possiblePiece.isPossible(getX()-2, getY()+1, blanche, noir)) {
	    	 arraylist.add(getX()-2);   
			 arraylist.add(getY()+1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()-2, getY()-1, blanche, noir)) {
	    	 arraylist.add(getX()-2);   
			 arraylist.add(getY()-1); 
	     }
	     
	     if(possiblePiece.isPossible(getX()-1, getY()-2, blanche, noir)) {
	    	 arraylist.add(getX()-1);   
			 arraylist.add(getY()-2); 
	     }
		 
	     
		 return arraylist; 
	}
	

}
