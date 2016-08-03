package Model;

import java.util.ArrayList;

public class Pawn extends Piece{
	
	public Pawn(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public ArrayList<String> getPossibleMoves(String startSpace){
		ArrayList<String> possibleSquares = new ArrayList<String>();
		return possibleSquares;
	}
	
	//Still need to account for checking space < 0 and space > 64 and Enpassant
	//Later add promotion
	public boolean checkMove(String startingSpot, String endingSpot, Square[] squares){
		boolean canMove = false;
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getFileRank().equals(startingSpot)){
				for(int j = 0; j < squares.length; j++){
					if(squares[j].getFileRank().equals(endingSpot)){
						int compareSpots = j - i;
						if(!squares[j].isOccupied()){
							if(squares[i].getPiece().getColor().equals("White")){							
								if(compareSpots == 8){
									canMove = true;
									System.out.println("Moved");
								}
							}
							else{
								if(compareSpots == -8){
									canMove = true;
									System.out.println("Moved");
								}
							}					
						}
					}
				}
			}
		}
		return canMove;
	}
	
	public Square[] move(String startingSpot, String endingSpot, Square[] squares){
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getFileRank().equals(startingSpot)){
				for(int j = 0; j < squares.length; j++){
					if(squares[j].getFileRank().equals(endingSpot)){
						Piece tempPiece = squares[j].getPiece();
						squares[j].setPiece(squares[i].getPiece());
						squares[i].setPiece(tempPiece);
					}
				}					
			}
		}
		return squares;
	}

}
