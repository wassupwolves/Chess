package Model;

import java.util.ArrayList;

public class Pawn extends Piece{
	
	public Pawn(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
		hasSpecialAttack = true;
	}
	
	public void setPossibleMoves(){
		possibleMoves.clear();
		if(this.color.equals("White")){
			possibleMoves.add(currentBoardLocation - VERTICAL_MOVE);
			if(!hasMoved){
				possibleMoves.add(currentBoardLocation - VERTICAL_MOVE * 2);
			}			
			possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE1);
			possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE2);
		}
		else if(this.color.equals("Black")){
			possibleMoves.add(currentBoardLocation + VERTICAL_MOVE);
			if(!hasMoved){
				possibleMoves.add(currentBoardLocation + VERTICAL_MOVE * 2);
			}			
			possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE1);			
			possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE2);
		}	
	}
}
