package Model;

import java.util.ArrayList;

public class King extends Piece{
	
	public King(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public void setPossibleMoves(){
		possibleMoves.clear();
		possibleMoves.add(currentBoardLocation + VERTICAL_MOVE);
		possibleMoves.add(currentBoardLocation - VERTICAL_MOVE);
		possibleMoves.add(currentBoardLocation + HORIZONTAL_MOVE);
		possibleMoves.add(currentBoardLocation - HORIZONTAL_MOVE);
		possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE1);
		possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE1);
		possibleMoves.add(currentBoardLocation + DIAGONAL_MOVE2);
		possibleMoves.add(currentBoardLocation - DIAGONAL_MOVE2);
	}
	


}
