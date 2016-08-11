package Model;

import java.util.ArrayList;

public class Knight extends Piece{
	
	private final int MOVE_6 = 6;
	private final int MOVE_10 = 10;
	private final int MOVE_15 = 15;
	private final int MOVE_17 = 17;
	
	public Knight(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
		canJump = true;
	}
	
	public void setPossibleMoves(){
		possibleMoves.clear();
		possibleMoves.add(currentBoardLocation + MOVE_6);
		possibleMoves.add(currentBoardLocation - MOVE_6);
		possibleMoves.add(currentBoardLocation + MOVE_10);
		possibleMoves.add(currentBoardLocation - MOVE_10);	
		possibleMoves.add(currentBoardLocation + MOVE_15);
		possibleMoves.add(currentBoardLocation - MOVE_15);	
		possibleMoves.add(currentBoardLocation + MOVE_17);
		possibleMoves.add(currentBoardLocation - MOVE_17);	
	}

}
