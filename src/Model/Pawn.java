package Model;

import java.util.ArrayList;

public class Pawn extends Piece{
	
	public Pawn(char pieceSymbol, String pieceColor){
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
		
//		Checks color first
		if(this.color.equals("White")){
			modifiedIndex = boardIndex - VERTICAL_MOVE;
			if(modifiedIndex >= BOARD_MIN){
//					Checks one in front of it
				if(!squares[modifiedIndex].isOccupied()){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
//					Checks two in front of it
					if(!this.hasMoved){
						modifiedIndex = boardIndex - (VERTICAL_MOVE * 2);
						if(!squares[modifiedIndex].isOccupied()){
							possibleMoves.add(squares[modifiedIndex].getFileRank());
						}
					}
				}
				modifiedIndex = boardIndex - DIAGONAL_MOVE1;
				if(squares[modifiedIndex].isOccupied() && squares[modifiedIndex].getPiece().getColor().equals("Black") && modifiedIndex%MOD_VALUE > boardModValue){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
				}
				modifiedIndex = boardIndex - DIAGONAL_MOVE2;
				if(squares[modifiedIndex].isOccupied() && squares[modifiedIndex].getPiece().getColor().equals("Black") && modifiedIndex%MOD_VALUE < boardModValue){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
				}
			}		
		}
		else{
			modifiedIndex = boardIndex + VERTICAL_MOVE;
			if(modifiedIndex <= BOARD_MAX){
//				Checks one in front of it
				if(!squares[modifiedIndex].isOccupied()){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
//					Checks two in front of it
					if(!this.hasMoved){
						modifiedIndex = boardIndex + (VERTICAL_MOVE * 2);
						if(!squares[modifiedIndex].isOccupied()){
							possibleMoves.add(squares[modifiedIndex].getFileRank());
						}
					}
				}
				modifiedIndex = boardIndex + DIAGONAL_MOVE1;
				if(squares[modifiedIndex].isOccupied() && squares[modifiedIndex].getPiece().getColor().equals("White") && modifiedIndex%MOD_VALUE < boardModValue){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
				}
				modifiedIndex = boardIndex + DIAGONAL_MOVE2;
				if(squares[modifiedIndex].isOccupied() && squares[modifiedIndex].getPiece().getColor().equals("White") && modifiedIndex%MOD_VALUE > boardModValue){
					possibleMoves.add(squares[modifiedIndex].getFileRank());
				}
			}

		}
		
		return possibleMoves;
	}
}
