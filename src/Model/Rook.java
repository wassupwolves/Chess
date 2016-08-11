package Model;

import java.util.ArrayList;

public class Rook extends Piece{
	
	public Rook(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public void setPossibleMoves(){
		possibleMoves.clear();
		for(int i = 1; i < 8; i++){
			possibleMoves.add(currentBoardLocation + VERTICAL_MOVE * i);
			possibleMoves.add(currentBoardLocation - VERTICAL_MOVE * i);
			possibleMoves.add(currentBoardLocation + HORIZONTAL_MOVE * i);
			possibleMoves.add(currentBoardLocation - HORIZONTAL_MOVE * i);
		}	
	}
}
