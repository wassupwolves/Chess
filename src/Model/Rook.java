package Model;

import java.util.ArrayList;

public class Rook extends Piece{
	
	public Rook(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public ArrayList<String> getPossibleMoves(String startSpace, Square[] squares){
		int boardIndex = -1;		
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getFileRank().equals(startSpace)){
				boardIndex = i;
			}
		}
		if(boardIndex == -1){
			System.out.println("Bad board location in getPossible moves for the piece in " + startSpace);
			System.exit(1);
		}
		
		int boardModValue = boardIndex%MOD_VALUE;
		
//		This checks to the left of the rook
		keepRunning = true;
		for(int j = boardIndex-HORIZONTAL_MOVE; j%MOD_VALUE < boardModValue && j >= BOARD_MIN && keepRunning; j -= HORIZONTAL_MOVE){
			addPossibleMoves(j, squares);	
		}
		
//		This checks to the right of the rook
		keepRunning = true;
		for(int j = boardIndex+HORIZONTAL_MOVE; j%MOD_VALUE > boardModValue && j <= BOARD_MAX && keepRunning; j += HORIZONTAL_MOVE){
			addPossibleMoves(j, squares);			
		}
		
//		This checks above the rook
		keepRunning = true;
		for(int j = boardIndex-VERTICAL_MOVE; j >= BOARD_MIN && keepRunning; j -= VERTICAL_MOVE){		
			addPossibleMoves(j, squares);			
		}
		
//		This checks below the rook
		keepRunning = true;
		for(int j = boardIndex+VERTICAL_MOVE; j <= BOARD_MAX && keepRunning; j += VERTICAL_MOVE){
			addPossibleMoves(j, squares);			
		}	
		
		return possibleMoves;
	}
}
