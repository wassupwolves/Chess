package Model;

import java.util.ArrayList;

public class Piece {

	protected char characterPiece;
	protected String color;
	protected boolean hasMoved = false;
	protected ArrayList<Integer> possibleMoves;
	protected int currentBoardLocation;
	protected boolean keepRunning = true;
	protected final int VERTICAL_MOVE = 8;
	protected final int HORIZONTAL_MOVE = 1;
	protected final int DIAGONAL_MOVE1 = 7;
	protected final int DIAGONAL_MOVE2 = 9;
	protected boolean canJump = false;
	protected boolean hasSpecialAttack = false;
//	Black are lower case, White are upper case
	public boolean getCanJump(){
		return canJump;
	}
	
	public boolean hasSpecialAttack(){
		return hasSpecialAttack;
	}
	
	public int getCurrentBoardLocation(){
		return currentBoardLocation;
	}
	
	public void setCurrentBoardLocation(int location){
		currentBoardLocation = location;
	}
	
	public void setHasMoved(boolean hasMoved){
		this.hasMoved = hasMoved;
	}
	
	public void setPossibleMoves(){
		possibleMoves.add(-1000);
	}
	
	public ArrayList<Integer> getPossibleMoves(){
		return possibleMoves;
	}
	
	public Piece(char pieceSymbol, String pieceColor){
		characterPiece = pieceSymbol;
		color = pieceColor;
		possibleMoves = new ArrayList<Integer>();
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
