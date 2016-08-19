package Model;

import java.util.ArrayList;

import Controller.CheckMove;
import Controller.TurnHandler;

public class Board {

	private final int boardSize = 64;
	private Square[] squares = new Square[boardSize];
	private Square[] tempSquares = new Square[boardSize];
	private boolean pieceTaken = false;
	private CheckMove checkMove;
	private TurnHandler turnHandle = new TurnHandler();
//	private Piece [] pieces = new Piece[32];
	
	public Board(Square[] tempSquares){
		populateTempSquares(tempSquares);
	}
	
	public Board(){		
		populateSquares();
	}
	
	public boolean attemptMove(String startSpace, String endSpace){
		boolean successfulMove = false;
		ArrayList<String> moves;
		for(int i = 0; i < squares.length && !successfulMove; i++){
			if(squares[i].getFileRank().equals(startSpace)){
				if(turnHandle.getColor().equals(squares[i].getPiece().getColor())){
					checkMove = new CheckMove(squares, turnHandle);
					checkMove.checkPossibleMoves(squares[i].getPiece());
					moves = checkMove.getPossibleSquares();
					for(int j = 0; j < moves.size(); j++){
						if(moves.get(j).equals(endSpace)){
//							Move a Piece
//							Swap Turns
							squares[i] = movePiece(squares[i], endSpace);
							turnHandle.swapColor();
							if(checkMove.isCheck('k', turnHandle.getColor())){
								System.out.println(turnHandle.getColor() + " King is in check!");
							}
							else{
								System.out.println(turnHandle.getColor() + " King is NOT in check!");
							}
							successfulMove = true;	
						}
					}	
				}			
			}
		}	
		return successfulMove;
	}
	
	private Square movePiece(Square startSquare, String endSpace){
		for(int i = 0; i < squares.length; i++){
			if(squares[i].getFileRank().equals(endSpace)){
				pieceTaken = squares[i].isOccupied();
				Piece emptyPiece = new Piece('-', "NoColor");
				squares[i].setPiece(startSquare.getPiece());
				squares[i].setOccupied(true);
				squares[i].getPiece().setHasMoved(true);
				squares[i].getPiece().setCurrentBoardLocation(i);
				squares[i].getPiece().setPossibleMoves();
//				checkMove.clearArrays();
				startSquare.setPiece(emptyPiece);
				startSquare.setOccupied(false);				
			}
		}
		return startSquare;
	}
	
	public void populateTempSquares(Square[] tempSquares){
		for(int i = 0; i < tempSquares.length; i++){
			this.tempSquares[i] = tempSquares[i];
		}
	}
	
	public void populateSquares(){			
		char startRank = '8';
		int arrayIndex = 0;
		Piece initialPiece = new Piece('-', "NoColor");
		for(int i = 0; i < boardSize/8; i++){
			char startFile = 'a';	
			for(int j = 0; j < boardSize/8; j++){
				Square square = new Square("" + startFile + startRank, initialPiece);
				squares[arrayIndex] = square;
				startFile++;				
				arrayIndex++;
			}
			startRank--;
		}
	}
	
	public void drawBoard(){
		int arrayIndex = 0;
		for(int i = 0; i < boardSize/8; i++){		
			for(int j = 0; j < boardSize/8; j++){
//				Change this to pull out the pieces value
				System.out.print(squares[arrayIndex].getPiece().getCharacterPiece() + "\t");
//				System.out.print(squares[arrayIndex].getFileRank() + "\t");	
				arrayIndex++;
			}			
			System.out.println("\n");
		}
	}
	
	public Square[] getSquares(){
		return squares;
	}
	
	public Square[] getTempSquares(){
		return tempSquares;
	}
	
	public void setSquares(Square[] squares){
		this.squares = squares;
	}
	
	public boolean getPieceTaken(){
		return pieceTaken;
	}
}
