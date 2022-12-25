package model;

import java.util.ArrayList;
import java.util.Vector;

public class Bishop extends Piece {

	public Bishop(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		int cpt = 0;
		
		Piece possiblePiece;
		possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		if(possiblePiece != null) {
		}
		else possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		
		for(int i = 1; i < 7; i++) {
			if( (x_dest != getX()+i || y_dest != getY()-i) && (x_dest != getX()-i || y_dest != getY()-i) && (x_dest != getX()+i || y_dest != getY()+i) &&  (x_dest != getX()-i || y_dest != getY()+i ) ) {
				cpt++;
			}
			
		}
				
		if(cpt == 6) {
			return false;
		}else {
			for(int i = 1; i < 7; i++) {

				if(x_dest == getX()+i && ( y_dest == getY()-i || y_dest == getY()+i )) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()+j, getY()-j, blanche, noir)) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()-i && y_dest == getY()-i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()-j, getY()-j, blanche, noir) ) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()+i && y_dest == getY()+i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()+j, getY()+j, blanche, noir) ) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()-i && y_dest == getY()+i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()-j, getY()+j, blanche, noir) ) {
							return false;
						}
					}
				
				}
			}
		}
		
		return true;
	}
	
	@Override
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
	     ArrayList arraylist = new ArrayList();
	     Piece possiblePiece;
	     possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
	     
	    for(int i=1;i<8;i++) {
	    	 if(possiblePiece.isPossible(getX()+i, getY()-i, blanche, noir)) {
	    		 arraylist.add(getX()+i);   
				 arraylist.add(getY()-i); 
		     }
	    	 if(possiblePiece.isPossible(getX()+i, getY()+i, blanche, noir)) {
	    		 arraylist.add(getX()+i);   
				 arraylist.add(getY()+i); 
		     }
	    	 if(possiblePiece.isPossible(getX()-i, getY()-i, blanche, noir)) {
	    		 arraylist.add(getX()-i);   
				 arraylist.add(getY()-i); 
		     }
	    	 if(possiblePiece.isPossible(getX()-i, getY()+i, blanche, noir)) {
	    		 arraylist.add(getX()-i);   
				 arraylist.add(getY()+i); 
		     }
	    	 
	    }
	    
		 return arraylist; 
	}

}
