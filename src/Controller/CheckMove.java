package Controller;

import java.util.ArrayList;

import Model.Piece;
import Model.Square;

public class CheckMove {

	private ArrayList<Integer> possibleMovesOnBoard = new ArrayList<Integer>();
	private ArrayList<String> possibleSquares = new ArrayList<String>();
	private ArrayList<Integer> finalPossibleMoves = new ArrayList<Integer>(); 
	private final int BOARD_MIN = 0;
	private final int BOARD_MAX = 63;
	private final int MOD_VALUE = 8;
	private Square[] squares;

	public CheckMove(Square[] squares){
		this.squares = squares;
	}

	public void clearArrays(){
		possibleSquares.clear();
	}

	private boolean checkJumpCheck(int startPosition){		
		int offset = 6;
		boolean inCheck = false;
		inCheck = checkJumpOffset(startPosition, offset);
		if(!inCheck){
			offset = -6;
			inCheck = checkJumpOffset(startPosition, offset);
		}		
		if(!inCheck){
			offset = 10;
			inCheck = checkJumpOffset(startPosition, offset);
		}
		if(!inCheck){
			offset = -10;
			inCheck = checkJumpOffset(startPosition, offset);
		}
		if(!inCheck){
			offset = 15;
			inCheck = checkJumpOffset(startPosition, offset);
		}
		if(!inCheck){
			offset = -15;
			inCheck = checkJumpOffset(startPosition, offset);
		}
		if(!inCheck){
			offset = 17;
			inCheck = checkJumpOffset(startPosition, offset);
		}
		if(!inCheck){
			offset = -17;
			inCheck = checkJumpOffset(startPosition, offset);
		}		
		return inCheck;
	}

	private boolean checkJumpOffset(int startPosition, int offset){
		possibleSquares.clear();
		boolean inCheck = false;
		int checkLocation = startPosition + offset;
		if(checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX){
			if(squares[checkLocation].isOccupied()){
				Piece piece = squares[checkLocation].getPiece();
				if(!piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					piece.setPossibleMoves();
					checkPossibleMoves(piece);
					for(int i = 0; i < possibleSquares.size(); i++){
						if(squares[startPosition].getFileRank().equals(possibleSquares.get(i))){							
							inCheck = true;
						}
					}
				}
			}
		}		
		return inCheck;
	}

	private boolean checkDiagonalLeftCheck(int startPosition){		
		int offset = 7;
		boolean inCheck = checkDiagonalLeftOffset(startPosition, offset);
		if(!inCheck){
			offset = -9;
			inCheck = checkDiagonalLeftOffset(startPosition, offset);
		}

		return inCheck;
	}

	private boolean checkDiagonalLeftOffset(int startPosition, int offset){
		possibleSquares.clear();
		boolean inCheck = false;
		boolean keepRunning = true;
		int checkLocation = startPosition + offset;	
		while(checkLocation % MOD_VALUE < startPosition % MOD_VALUE && keepRunning && checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX){
			if(!squares[checkLocation].isOccupied()){
				checkLocation += offset;
			}
			else{
				Piece piece = squares[checkLocation].getPiece();
				if(piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					keepRunning = false;
				}
				else{
					piece.setPossibleMoves();
					checkPossibleMoves(piece);
					for(int i = 0; i < possibleSquares.size(); i++){
						if(squares[startPosition].getFileRank().equals(possibleSquares.get(i))){							
							inCheck = true;
						}
					}
					keepRunning = false;
				}
			}
		}		
		return inCheck;
	}

	private boolean checkDiagonalRightCheck(int startPosition){		
		int offset = 9;
		boolean inCheck = checkDiagonalRightOffset(startPosition, offset);
		if(!inCheck){
			offset = -7;
			inCheck = checkDiagonalRightOffset(startPosition, offset);
		}

		return inCheck;
	}

	private boolean checkDiagonalRightOffset(int startPosition, int offset){
		possibleSquares.clear();
		boolean inCheck = false;
		boolean keepRunning = true;
		int checkLocation = startPosition + offset;
		while(checkLocation % MOD_VALUE > startPosition % MOD_VALUE && keepRunning && checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX){
			if(!squares[checkLocation].isOccupied()){
				checkLocation += offset;
			}
			else{
				Piece piece = squares[checkLocation].getPiece();
				if(piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					keepRunning = false;
				}
				else{
					piece.setPossibleMoves();
					checkPossibleMoves(piece);
					for(int i = 0; i < possibleSquares.size(); i++){
						if(squares[startPosition].getFileRank().equals(possibleSquares.get(i))){							
							inCheck = true;
						}
					}
					keepRunning = false;
				}
			}
		}		
		return inCheck;
	}

	private boolean checkVerticalCheck(int startPosition){		
		int offset = 8;
		boolean inCheck = checkVerticalOffset(startPosition, offset);
		if(!inCheck){
			offset = -8;
			inCheck = checkVerticalOffset(startPosition, offset);
		}

		return inCheck;
	}

	private boolean checkVerticalOffset(int startPosition, int offset){
		possibleSquares.clear();
		boolean inCheck = false;
		boolean keepRunning = true;
		int checkLocation = startPosition + offset;
		while(checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX && keepRunning){
			if(!squares[checkLocation].isOccupied()){
				checkLocation += offset;
			}
			else{
				Piece piece = squares[checkLocation].getPiece();
				if(piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					keepRunning = false;
				}
				else{
					piece.setPossibleMoves();
					checkPossibleMoves(piece);
					for(int i = 0; i < possibleSquares.size(); i++){
						if(squares[startPosition].getFileRank().equals(possibleSquares.get(i))){							
							inCheck = true;
						}
					}
					keepRunning = false;
				}
			}
		}
		return inCheck;
	}

	private boolean checkHorizontalCheck(int startPosition){		
		int offset = 1;
		boolean inCheck = checkHorizontalOffset(startPosition, offset);
		if(!inCheck){
			offset = -1;
			inCheck = checkHorizontalOffset(startPosition, offset);
		}

		return inCheck;
	}

	private boolean checkHorizontalOffset(int startPosition, int offset){
		possibleSquares.clear();
		boolean inCheck = false;
		boolean keepRunning = true;
		double sameRowCheck = Math.floor(startPosition / MOD_VALUE);
		int checkLocation = startPosition + offset;
		while(Math.floor(checkLocation / MOD_VALUE) == sameRowCheck && keepRunning && checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX){
			if(!squares[checkLocation].isOccupied()){
				checkLocation += offset;
			}
			else{
				Piece piece = squares[checkLocation].getPiece();
				if(piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					keepRunning = false;
				}
				else{
					piece.setPossibleMoves();
					checkPossibleMoves(piece);
					for(int i = 0; i < possibleSquares.size(); i++){
						if(squares[startPosition].getFileRank().equals(possibleSquares.get(i))){							
							inCheck = true;
						}
					}
					keepRunning = false;
				}
			}
		}		
		return inCheck;
	}

	public boolean isCheck(char character, String color){
		Piece piece = findPieceChar(character, color);
		int piecePosition = piece.getCurrentBoardLocation();		
		return checkForCheck(piecePosition);
	}

	private boolean checkForCheck(int piecePosition){
		boolean check = false;
		if(checkHorizontalCheck(piecePosition)){
			check = true;
		}
		else if(checkVerticalCheck(piecePosition)){
			check = true;
		}
		else if(checkDiagonalRightCheck(piecePosition)){
			check = true;
		}
		else if(checkDiagonalLeftCheck(piecePosition)){
			check = true;
		}
		else if(checkJumpCheck(piecePosition)){
			check = true;
		}
		possibleSquares.clear();
		return check;
	}

	private Piece findPieceChar(char character, String color){
		char pieceChar = character;
		Piece piece = new Piece('-', "NoColor");
		if(color.equals("Black")){
			pieceChar = Character.toLowerCase(pieceChar);
		}
		else{
			pieceChar = Character.toUpperCase(pieceChar);
		}
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getPiece().getCharacterPiece() == pieceChar){
				piece = squares[i].getPiece();
			}
		}
		return piece;
	}

	private void checkOffBoard(Piece piece){
		ArrayList<Integer> possibleMoves = piece.getPossibleMoves();
		//		int boardModValue = piece.getCurrentBoardLocation() % MOD_VALUE;
		for(int i = 0; i < possibleMoves.size(); i++){
			if(possibleMoves.get(i) >= BOARD_MIN && possibleMoves.get(i) <= BOARD_MAX){
				possibleMovesOnBoard.add(possibleMoves.get(i));
			}
		}
	}

	private boolean hasPathToSquare(int startPosition, int endPosition){
		boolean result = false;		
		if(checkStraight(startPosition, endPosition)){
			result = true;
		}
		else if(checkDiagonal(startPosition, endPosition) && !result){
			result = true;
		}	
		return result;
	}

	private boolean checkPath(int startPosition, int endPosition, int offset){
		boolean pathClear = true;
		int endPositionHolder = endPosition + offset;
		while(endPositionHolder != startPosition && pathClear && endPositionHolder >= BOARD_MIN && endPositionHolder <= BOARD_MAX){
			if(squares[endPositionHolder].isOccupied()){
				pathClear = false;
			}
			endPositionHolder += offset;
		}
		return pathClear;
	}

	//	Beef up this in order to fit in check, run the isCheck() method
	private void removeImpossibleMoves(Piece piece){
		int currentPosition = piece.getCurrentBoardLocation();
		for(int i = 0; i < possibleMovesOnBoard.size(); i++){
			if(!piece.getColor().equals(squares[possibleMovesOnBoard.get(i)].getPiece().getColor())){
				if(piece.getCanJump()){
					checkJumpMoves(currentPosition, possibleMovesOnBoard.get(i));
				}
				else if(hasPathToSquare(currentPosition, possibleMovesOnBoard.get(i))){
					if(piece.hasSpecialAttack()){
						checkSpecialAttack(currentPosition, possibleMovesOnBoard.get(i));
					}
					else{
//						finalPossibleMoves.add(i);
						possibleSquares.add(squares[possibleMovesOnBoard.get(i)].getFileRank());	
					}								
				}
			}
		}		
	}
	
	private void removeCheckViolations(Piece piece){
		Square[] tempSquares = squares;
		for(int i = 0; i < finalPossibleMoves.size(); i++){
			Square startSquare = tempSquares[piece.getCurrentBoardLocation()];
			Square endSquare = tempSquares[finalPossibleMoves.get(i)];
			Piece emptyPiece = new Piece('-', "NoColor");
			endSquare.setPiece(startSquare.getPiece());
			startSquare.setPiece(emptyPiece);	
			startSquare.setOccupied(true);
			if(!isCheck('k', piece.getColor())){
				possibleSquares.add(squares[finalPossibleMoves.get(i)].getFileRank());
			}
		}
	}
	
	private void checkJumpMoves(int startPosition, int endPosition){
		if(endPosition - 6 == startPosition || endPosition - 15 == startPosition || endPosition + 10 == startPosition || endPosition + 17 == startPosition){
			if(endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				possibleSquares.add(squares[endPosition].getFileRank());
//				finalPossibleMoves.add(endPosition);
			}
		}
		else if(endPosition + 6 == startPosition || endPosition + 15 == startPosition || endPosition - 10 == startPosition || endPosition - 17 == startPosition){
			if(endPosition % MOD_VALUE > startPosition % MOD_VALUE){
				possibleSquares.add(squares[endPosition].getFileRank());
//				finalPossibleMoves.add(endPosition);
			}
		}
		
	}

	private void checkSpecialAttack(int startPosition, int endPosition){
		float diagonal1 = 9;
		float diagonal2 = 7;
		if(!squares[endPosition].isOccupied() && startPosition % MOD_VALUE == endPosition % MOD_VALUE){
			possibleSquares.add(squares[endPosition].getFileRank());
//			finalPossibleMoves.add(endPosition);
		}
		else if(squares[endPosition].isOccupied() && ((endPosition - startPosition) / diagonal1) % 1 == 0){
			if(!squares[startPosition].getPiece().getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleSquares.add(squares[endPosition].getFileRank());
//				finalPossibleMoves.add(endPosition);
			}
		}
		else if(squares[endPosition].isOccupied() && ((endPosition - startPosition) / diagonal2) % 1 == 0){
			if(!squares[startPosition].getPiece().getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleSquares.add(squares[endPosition].getFileRank());
//				finalPossibleMoves.add(endPosition);
			}
		}
	}

	private boolean checkDiagonal(int startPosition, int endPosition){
		boolean validMove = false;
		int offset;
		float diagonal1 = 9;
		float diagonal2 = 7;
		if(((endPosition - startPosition) / diagonal1) % 1 == 0){
			//			+9
			if(endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				offset = 9;
			}
			//			-9
			else{
				offset = -9;
			}
			validMove = checkPath(startPosition, endPosition, offset);
		}
		else if(((endPosition - startPosition) / diagonal2) % 1 == 0){
			//			+7
			if(endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				offset = -7;
			}
			//			-7
			else{
				offset = 7;
			}
			validMove = checkPath(startPosition, endPosition, offset);
		}		
		return validMove;
	}

	private boolean checkStraight(int startPosition, int endPosition){
		boolean validMove = false;
		int offset;
		if(endPosition % MOD_VALUE == startPosition % MOD_VALUE){
			//			+9
			if(endPosition > startPosition){
				offset = -8;
			}
			//			-9
			else{
				offset = 8;
			}
			validMove = checkPath(startPosition, endPosition, offset);
		}
		//		This trims off the decimals allowing us to compare their row values
		else if(Math.floor(endPosition / MOD_VALUE) == Math.floor(startPosition / MOD_VALUE)){
			//			+1
			if(endPosition < startPosition){
				offset = 1;
			}
			//			-1
			else{
				offset = -1;
			}
			validMove = checkPath(startPosition, endPosition, offset);
		}
		return validMove;
	}

	public void checkPossibleMoves(Piece piece){
		possibleMovesOnBoard.clear();
		checkOffBoard(piece);
		removeImpossibleMoves(piece);
//		removeCheckViolations(piece);
	}

	public ArrayList<String> getPossibleSquares(){
		return possibleSquares;
	}

}
