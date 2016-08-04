package Model;

import java.util.ArrayList;

public class Knight extends Piece{
	
	private final int MOVE_6 = 6;
	private final int MOVE_10 = 10;
	private final int MOVE_15 = 15;
	private final int MOVE_17 = 17;
	
	public Knight(char pieceSymbol, String pieceColor){
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
		
		int modifiedIndex = boardIndex;
		int boardModValue = boardIndex%MOD_VALUE;
		
//		This is UP-RIGHT UP
		modifiedIndex = boardIndex - MOVE_15;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);	
		}
//		This is UP-RIGHT RIGHT
		modifiedIndex = boardIndex - MOVE_6;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}
//		This is UP-LEFT UP
		modifiedIndex = boardIndex - MOVE_17;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}
//		This is UP-LEFT LEFT
		modifiedIndex = boardIndex - MOVE_10;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}		
//		This is DOWN-RIGHT DOWN
		modifiedIndex = boardIndex + MOVE_17;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}
//		This is DOWN-RIGHT RIGHT
		modifiedIndex = boardIndex + MOVE_10;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}	
//		This is DOWN-LEFT DOWN
		modifiedIndex = boardIndex + MOVE_15;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}
//		This is DOWN-LEFT LEFT
		modifiedIndex = boardIndex + MOVE_6;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}		
		return possibleMoves;
	}

}
