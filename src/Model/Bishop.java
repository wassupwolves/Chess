package Model;

import java.util.ArrayList;

public class Bishop extends Piece{
	
	public Bishop(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public void setPossibleMoves(){
		possibleMoves.clear();
		for(int i = 1; i < 8; i++){
			possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE1 * i);
			possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE1 * i);
			possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE2 * i);
			possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE2 * i);
		}	
	}

}
