package Model;

import java.util.ArrayList;

public class Queen extends Piece{
	
	public Queen(char pieceSymbol, String pieceColor){
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
		
//		This checks to the left of the queen
		keepRunning = true;
		for(int j = boardIndex-HORIZONTAL_MOVE; j%MOD_VALUE < boardModValue && j >= BOARD_MIN && keepRunning; j -= HORIZONTAL_MOVE){
			addPossibleMoves(j, squares);	
		}
		
//		This checks to the right of the queen
		keepRunning = true;
		for(int j = boardIndex+HORIZONTAL_MOVE; j%MOD_VALUE > boardModValue && j <= BOARD_MAX && keepRunning; j += HORIZONTAL_MOVE){
			addPossibleMoves(j, squares);			
		}
		
//		This checks above the queen
		keepRunning = true;
		for(int j = boardIndex-VERTICAL_MOVE; j >= BOARD_MIN && keepRunning; j -= VERTICAL_MOVE){		
			addPossibleMoves(j, squares);			
		}
		
//		This checks below the queen
		keepRunning = true;
		for(int j = boardIndex+VERTICAL_MOVE; j <= BOARD_MAX && keepRunning; j += VERTICAL_MOVE){
			addPossibleMoves(j, squares);			
		}		
		
//		This will check up and left movement for -7
		keepRunning = true;
		for(int j = boardIndex-DIAGONAL_MOVE1; j%MOD_VALUE > boardModValue && j >= BOARD_MIN && keepRunning; j -= DIAGONAL_MOVE1){
			addPossibleMoves(j, squares);	
		}
		
//		This will check down and right movement for +7
		keepRunning = true;
		for(int j = boardIndex+DIAGONAL_MOVE1; j%MOD_VALUE < boardModValue && j <= BOARD_MAX && keepRunning; j += DIAGONAL_MOVE1){
			addPossibleMoves(j, squares);			
		}
		
//		This will check up and left movement for -9
		keepRunning = true;
		for(int j = boardIndex-DIAGONAL_MOVE2; j%MOD_VALUE < boardModValue && j >= BOARD_MIN && keepRunning; j -= DIAGONAL_MOVE2){
			addPossibleMoves(j, squares);		
		}
		
//		This will check down and right movement for +9
		keepRunning = true;
		for(int j = boardIndex+DIAGONAL_MOVE2; j%MOD_VALUE > boardModValue && j <= BOARD_MAX && keepRunning; j += DIAGONAL_MOVE2){
			addPossibleMoves(j, squares);		
		}
		
		return possibleMoves;
	}
}
