package Model;

import java.util.ArrayList;

public class King extends Piece{
	
	public King(char pieceSymbol, String pieceColor){
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
		
//		This checks to the left of the king
		modifiedIndex = boardIndex - HORIZONTAL_MOVE;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);			
		}
		
//		This checks to the right of the king
		modifiedIndex = boardIndex + HORIZONTAL_MOVE;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);	
		}
		
//		This checks above the king
		modifiedIndex = boardIndex - VERTICAL_MOVE;
		if(modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}
		
//		This checks below the king
		modifiedIndex = boardIndex + VERTICAL_MOVE;
		if(modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}	
		
//		This will check up and left movement for -7
		modifiedIndex = boardIndex - DIAGONAL_MOVE1;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}
		
//		This will check down and left movement for +7
		modifiedIndex = boardIndex + DIAGONAL_MOVE1;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}	
		
//		This will check up and left movement for -9
		modifiedIndex = boardIndex - DIAGONAL_MOVE2;
		if(modifiedIndex%MOD_VALUE < boardModValue && modifiedIndex >= BOARD_MIN){
			addPossibleMoves(modifiedIndex, squares);		
		}
		
//		This will check down and right movement for +9
		modifiedIndex = boardIndex + DIAGONAL_MOVE2;
		if(modifiedIndex%MOD_VALUE > boardModValue && modifiedIndex <= BOARD_MAX){
			addPossibleMoves(modifiedIndex, squares);		
		}	
		
		return possibleMoves;
	}

}
