package Model;

import java.util.ArrayList;

public class Piece {

	protected char characterPiece;
	protected String color;
	protected boolean hasMoved;
//	Black are lower case, White are upper case
	
	public Piece(char pieceSymbol, String pieceColor){
		characterPiece = pieceSymbol;
		color = pieceColor;
		hasMoved = false;
	}
	
	public boolean checkMove(String startingSpot, String endingSpot, Square[] squares){
		boolean canMove = false;
		return canMove;
	}
	
	public Square[] move(String startingSpot, String endingSpot, Square[] squares){
		return squares;
	}
	
	public ArrayList<String> getPossibleMoves(String startSpace, Square[] squares){
		ArrayList<String> possibleMoves = new ArrayList<String>();		
		return possibleMoves;
	}

	public char getCharacterPiece() {
		return characterPiece;
	}

	public void setCharacterPiece(char characterPiece) {
		this.characterPiece = characterPiece;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
