package Controller;

import java.util.ArrayList;

import Model.Board;
import Model.Piece;
import Model.Square;

public class CheckMove {

	private ArrayList<Integer> possibleMovesOnBoard = new ArrayList<Integer>();
	private ArrayList<Integer> possibleMovesOnBoardSecondary = new ArrayList<Integer>();
	private ArrayList<String> possibleSquares = new ArrayList<String>();
	private ArrayList<String> finalPossibleSquares = new ArrayList<String>();
	private ArrayList<Integer> possibleCheckMoves = new ArrayList<Integer>();
	private ArrayList<Integer> possibleCheckMovesSecondary = new ArrayList<Integer>();
	private ArrayList<Boolean> possibleCheckMate = new ArrayList<Boolean>();
	private boolean checkMate = false;
	//	private ArrayList<Integer> finalPossibleMoves = new ArrayList<Integer>(); 
	private int kingPosition;
	private final int BOARD_MIN = 0;
	private final int BOARD_MAX = 63;
	private final int MOD_VALUE = 8;
	private Square[] squares;
	private Square[] tempSquares;
	private int checkOffset;
	private TurnHandler handler;
	
	public Square[] getSquares(){
		return squares;
	}

	public CheckMove(Square[] squares, TurnHandler handler){
		this.squares = squares;
		this.handler = handler;
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
//		possibleSquares.clear();
		boolean inCheck = false;
		int checkLocation = startPosition + offset;
		if(checkLocation >= BOARD_MIN && checkLocation <= BOARD_MAX){
			if(squares[checkLocation].isOccupied()){
				Piece piece = squares[checkLocation].getPiece();
				if(!piece.getColor().equals(squares[startPosition].getPiece().getColor())){
					piece.setPossibleMoves();
//					checkPossibleMoves(piece);
					checkPossibleMovesSecondary(piece);
					for(int i = 0; i < possibleCheckMovesSecondary.size(); i++){
						if(startPosition == (possibleCheckMovesSecondary.get(i))){							
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
//		possibleSquares.clear();
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
//					checkPossibleMoves(piece);
					checkPossibleMovesSecondary(piece);
					for(int i = 0; i < possibleCheckMovesSecondary.size(); i++){
						if(startPosition == (possibleCheckMovesSecondary.get(i))){							
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
//		possibleSquares.clear();
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
//					checkPossibleMoves(piece);
					checkPossibleMovesSecondary(piece);
					for(int i = 0; i < possibleCheckMovesSecondary.size(); i++){
						if(startPosition == (possibleCheckMovesSecondary.get(i))){							
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
//		possibleSquares.clear();
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
//					checkPossibleMoves(piece);
					checkPossibleMovesSecondary(piece);
					for(int i = 0; i < possibleCheckMovesSecondary.size(); i++){
						if(startPosition == (possibleCheckMovesSecondary.get(i))){							
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
//					checkPossibleMoves(piece);
					checkPossibleMovesSecondary(piece);
					for(int i = 0; i < possibleCheckMovesSecondary.size(); i++){
						if(startPosition == (possibleCheckMovesSecondary.get(i))){							
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
		kingPosition = piecePosition;
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
		for(int i = 0; i < possibleMoves.size(); i++){
			if(possibleMoves.get(i) >= BOARD_MIN && possibleMoves.get(i) <= BOARD_MAX){
				possibleMovesOnBoard.add(possibleMoves.get(i));
			}
		}
	}
	
	private void checkOffBoardSecondary(Piece piece){
		ArrayList<Integer> possibleMoves = piece.getPossibleMoves();
		for(int i = 0; i < possibleMoves.size(); i++){
			if(possibleMoves.get(i) >= BOARD_MIN && possibleMoves.get(i) <= BOARD_MAX){
				possibleMovesOnBoardSecondary.add(possibleMoves.get(i));
			}
		}
	}

	private boolean hasPathToSquare(int startPosition, int endPosition, Piece piece){
		boolean result = false;		
		if(piece.getMoveStraight() && checkStraight(startPosition, endPosition)){
			result = true;
		}
		else if(piece.getMoveDiagonal() && checkDiagonal(startPosition, endPosition) && !result){
			result = true;
		}	
		return result;
	}

	private boolean checkPath(int startPosition, int endPosition, int offset){
		boolean pathClear = true;
		if(offset == 9  && endPosition % MOD_VALUE > startPosition % MOD_VALUE){
			pathClear = false;
		}
		else if(offset == -9  && endPosition % MOD_VALUE < startPosition % MOD_VALUE){
			pathClear = false;
		}
		else if(offset == -7  && endPosition % MOD_VALUE > startPosition % MOD_VALUE){
			pathClear = false;
		}
		else if(offset == 7  && endPosition % MOD_VALUE < startPosition % MOD_VALUE){
			pathClear = false;
		}
		else if(offset == -1 && Math.floor(endPosition / MOD_VALUE) != Math.floor(startPosition / MOD_VALUE)){
			pathClear = false;
		}
		else if(offset == 1 && Math.floor(endPosition / MOD_VALUE) != Math.floor(startPosition / MOD_VALUE)){
			pathClear = false;
		}
		else if(squares[startPosition].getPiece().getCharacterPiece() == 'k' || squares[startPosition].getPiece().getCharacterPiece() == 'K'){
			if(startPosition % MOD_VALUE == 0 && endPosition % MOD_VALUE == 7){
				pathClear = false;
			}
			else if(startPosition % MOD_VALUE == 7 && endPosition % MOD_VALUE == 0){
				pathClear = false;
			}
		}
		int endPositionHolder = endPosition + offset;
		while(endPositionHolder != startPosition && pathClear){
			if(endPositionHolder >= BOARD_MIN && endPositionHolder <= BOARD_MAX){
				if(!squares[endPositionHolder].isOccupied()){
					endPositionHolder += offset;
				}
				else{
					pathClear = false;
				}
			}
			else{
				pathClear = false;
			}			
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
				else if(piece.hasSpecialAttack()){
					checkSpecialAttack(currentPosition, possibleMovesOnBoard.get(i), piece);
				}
				else if(hasPathToSquare(currentPosition, possibleMovesOnBoard.get(i), piece)){
					possibleCheckMoves.add(possibleMovesOnBoard.get(i));									
				}
			}
		}		
	}
	
//	I needed this secondary methods because when checking the opponents pieces I didnt need to run a checkCheck on those
	private void removeImpossibleMovesSecondary(Piece piece){
		int currentPosition = piece.getCurrentBoardLocation();
		for(int i = 0; i < possibleMovesOnBoardSecondary.size(); i++){
			if(!piece.getColor().equals(squares[possibleMovesOnBoardSecondary.get(i)].getPiece().getColor())){
				if(piece.getCanJump()){
					checkJumpMovesSecondary(currentPosition, possibleMovesOnBoardSecondary.get(i));
				}
				else if(piece.hasSpecialAttack()){
					checkSpecialAttackSecondary(currentPosition, possibleMovesOnBoardSecondary.get(i), piece);
				}
				else if(hasPathToSquare(currentPosition, possibleMovesOnBoardSecondary.get(i), piece)){
					//						finalPossibleMoves.add(i);
					possibleCheckMovesSecondary.add(possibleMovesOnBoardSecondary.get(i));									
				}
			}
		}		
	}

	private void removeCheckViolations(Piece piece){
		Board tempBoard = new Board(squares);
		Square[] tempSquares = tempBoard.getTempSquares();
		Piece initialStartPiece = piece;		
		int initialStartLocation = piece.getCurrentBoardLocation();
		Piece emptyPiece = new Piece('-', "NoColor");				
		for(int i = 0; i < possibleCheckMoves.size(); i++){
			Piece initialEndPiece = emptyPiece;
			if(tempSquares[possibleCheckMoves.get(i)].isOccupied()){
				initialEndPiece = tempSquares[possibleCheckMoves.get(i)].getPiece();
			}		
			int initialEndLocation = possibleCheckMoves.get(i);
			tempSquares[initialStartLocation].setOccupied(false);
			tempSquares[initialStartLocation].setPiece(emptyPiece);
			tempSquares[initialEndLocation].setOccupied(true);
			tempSquares[initialEndLocation].setPiece(initialStartPiece);
			initialStartPiece.setCurrentBoardLocation(initialEndLocation);
			if(!isCheck('k', piece.getColor())){
				possibleSquares.add(squares[possibleCheckMoves.get(i)].getFileRank());
			}
			tempSquares[initialStartLocation].setOccupied(true);
			tempSquares[initialStartLocation].setPiece(initialStartPiece);
			initialStartPiece.setCurrentBoardLocation(initialStartLocation);
			if(initialEndPiece.equals(emptyPiece)){
				tempSquares[initialEndLocation].setOccupied(false);
			}			
			tempSquares[initialEndLocation].setPiece(initialEndPiece);	
		}
	}
	
	private void removeCheckViolationsSecondary(Piece piece){
		boolean keepGoing = true;
		Board tempBoard = new Board(squares);
		Square[] tempSquares = tempBoard.getTempSquares();
		Piece initialStartPiece = piece;		
		int initialStartLocation = piece.getCurrentBoardLocation();
		Piece emptyPiece = new Piece('-', "NoColor");				
		for(int i = 0; i < possibleCheckMovesSecondary.size() && keepGoing; i++){
			Piece initialEndPiece = emptyPiece;
			if(tempSquares[possibleCheckMovesSecondary.get(i)].isOccupied()){
				initialEndPiece = tempSquares[possibleCheckMovesSecondary.get(i)].getPiece();
			}		
			int initialEndLocation = possibleCheckMovesSecondary.get(i);
			tempSquares[initialStartLocation].setOccupied(false);
			tempSquares[initialStartLocation].setPiece(emptyPiece);
			tempSquares[initialEndLocation].setOccupied(true);
			tempSquares[initialEndLocation].setPiece(initialStartPiece);		
			if(isCheck('k', piece.getColor())){
				possibleCheckMate.add(true);				
			}
			else{
				possibleCheckMate.add(false);	
				keepGoing = false;
			}
			tempSquares[initialStartLocation].setOccupied(true);
			tempSquares[initialStartLocation].setPiece(initialStartPiece);
			initialStartPiece.setCurrentBoardLocation(initialStartLocation);
			if(initialEndPiece.equals(emptyPiece)){
				tempSquares[initialEndLocation].setOccupied(false);
			}			
			tempSquares[initialEndLocation].setPiece(initialEndPiece);	
		}
	}
	
	private void checkCheckMate(Piece piece){
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getPiece().getColor().equals(piece.getColor())){
//				checkPossibleMovesSecondary(squares[i].getPiece());
//				removeCheckViolationsSecondary(squares[i].getPiece());
				possibleMovesOnBoard.clear();
				possibleCheckMoves.clear();
				possibleSquares.clear();
				finalPossibleSquares.clear();
				checkOffBoard(squares[i].getPiece());
				removeImpossibleMoves(squares[i].getPiece());
				if(handler.getColor().equals(squares[i].getPiece().getColor())){
					removeCheckViolations(squares[i].getPiece());
				}	
				fillFinalPossibleSquareArray();
				if(finalPossibleSquares.size() == 0){			
					possibleCheckMate.add(true);
				}
				else{
					possibleCheckMate.add(false);
				}
			}
		}
	}
	
	private void checkJumpMovesSecondary(int startPosition, int endPosition){
		if(endPosition - 6 == startPosition || endPosition - 15 == startPosition || endPosition + 10 == startPosition || endPosition + 17 == startPosition){
			if(endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				possibleCheckMovesSecondary.add(endPosition);
				//				finalPossibleMoves.add(endPosition);
			}
		}
		else if(endPosition + 6 == startPosition || endPosition + 15 == startPosition || endPosition - 10 == startPosition || endPosition - 17 == startPosition){
			if(endPosition % MOD_VALUE > startPosition % MOD_VALUE){
				possibleCheckMovesSecondary.add(endPosition);
				//				finalPossibleMoves.add(endPosition);
			}
		}		
	}

	private void checkJumpMoves(int startPosition, int endPosition){
		if(endPosition - 6 == startPosition || endPosition - 15 == startPosition || endPosition + 10 == startPosition || endPosition + 17 == startPosition){
			if(endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
				//				finalPossibleMoves.add(endPosition);
			}
		}
		else if(endPosition + 6 == startPosition || endPosition + 15 == startPosition || endPosition - 10 == startPosition || endPosition - 17 == startPosition){
			if(endPosition % MOD_VALUE > startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
				//				finalPossibleMoves.add(endPosition);
			}
		}		
	}
	
	private void checkSpecialAttackSecondary(int startPosition, int endPosition, Piece piece){
		if(piece.getColor().equals("White")){
			if(endPosition + 16 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition + 8 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition + 7 == startPosition && !piece.getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition + 9 == startPosition && !piece.getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleCheckMovesSecondary.add(endPosition);
			}
		}
		else if(piece.getColor().equals("Black")){
			if(endPosition - 16 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition - 8 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition - 7 == startPosition && !piece.getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleCheckMovesSecondary.add(endPosition);
			}
			else if(endPosition - 9 == startPosition && !piece.getColor().equals(squares[endPosition].getPiece().getColor())){
				possibleCheckMovesSecondary.add(endPosition);
			}
		}
	}

	private void checkSpecialAttack(int startPosition, int endPosition, Piece piece){
		if(piece.getColor().equals("White")){
			if(endPosition + 16 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition + 8 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition + 7 == startPosition && squares[endPosition].getPiece().getColor().equals("Black") && endPosition % MOD_VALUE > startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition + 9 == startPosition && squares[endPosition].getPiece().getColor().equals("Black") && endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
			}
		}
		else if(piece.getColor().equals("Black")){
			if(endPosition - 16 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition - 8 == startPosition && !squares[endPosition].isOccupied()){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition - 7 == startPosition && squares[endPosition].getPiece().getColor().equals("White") && endPosition % MOD_VALUE < startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
			}
			else if(endPosition - 9 == startPosition && squares[endPosition].getPiece().getColor().equals("White") && endPosition % MOD_VALUE > startPosition % MOD_VALUE){
				possibleCheckMoves.add(endPosition);
			}
		}
	}

	private boolean checkDiagonal(int startPosition, int endPosition){
		boolean validMove = false;
		int offset;
		float diagonal1 = 9;
		float diagonal2 = 7;
		if(((endPosition - startPosition) / diagonal1) % 1 == 0){
			offset = 9;
			checkOffset = offset;
			validMove = checkPath(startPosition, endPosition, offset);
			if(!validMove){
				offset = -9;
				checkOffset = offset;
				validMove = checkPath(startPosition, endPosition, offset);		
			}				
		}
		else if(((endPosition - startPosition) / diagonal2) % 1 == 0){
			offset = -7;
			checkOffset = offset;
			validMove = checkPath(startPosition, endPosition, offset);
			if(!validMove){
				offset = 7;
				checkOffset = offset;
				validMove = checkPath(startPosition, endPosition, offset);
			}			
		}		
		return validMove;
	}

	private boolean checkStraight(int startPosition, int endPosition){
		boolean validMove = false;
		int offset;
		if(endPosition % MOD_VALUE == startPosition % MOD_VALUE){
			offset = -8;
			checkOffset = offset;
			validMove = checkPath(startPosition, endPosition, offset);
			if(!validMove){
				offset = 8;
				checkOffset = offset;
				validMove = checkPath(startPosition, endPosition, offset);
			}			
		}
		//		This trims off the decimals allowing us to compare their row values
		else if(Math.floor(endPosition / MOD_VALUE) == Math.floor(startPosition / MOD_VALUE)){
			offset = 1;
			checkOffset = offset;
			validMove = checkPath(startPosition, endPosition, offset);
			if(!validMove){
				offset = -1;
				checkOffset = offset;
				validMove = checkPath(startPosition, endPosition, offset);
			}
		}
		return validMove;
	}
	
	public void checkPossibleMovesSecondary(Piece piece){
		possibleMovesOnBoardSecondary.clear();
		possibleCheckMovesSecondary.clear();		
//		possibleSquares.clear();
		checkOffBoardSecondary(piece);
		removeImpossibleMovesSecondary(piece);
	}

	public void checkPossibleMoves(Piece piece){
		possibleMovesOnBoard.clear();
		possibleCheckMoves.clear();
		possibleSquares.clear();
		finalPossibleSquares.clear();
		possibleCheckMate.clear();
		checkOffBoard(piece);
		removeImpossibleMoves(piece);
		if(handler.getColor().equals(piece.getColor())){
			removeCheckViolations(piece);
		}	
		fillFinalPossibleSquareArray();
		if(finalPossibleSquares.size() == 0){			
			checkCheckMate(piece);
		}
		else{
			possibleCheckMate.add(false);
		}
	}
	
//	This is to remove all duplicates in the final fileRank array
	private void fillFinalPossibleSquareArray(){
		for(String fileRank : possibleSquares){
			if(!finalPossibleSquares.contains(fileRank)){
				finalPossibleSquares.add(fileRank);
			}
		}
	}
	
	public boolean getCheckMate(){
		if(!possibleCheckMate.contains(false)){
			checkMate = true;
		}
		return checkMate;
	}

	public ArrayList<String> getFinalPossibleSquares(){
		return finalPossibleSquares;
	}

}