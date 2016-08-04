package Model;

import java.util.ArrayList;

public class Piece {

	protected char characterPiece;
	protected String color;
	protected boolean hasMoved = false;
	protected ArrayList<String> possibleMoves = new ArrayList<String>();
	protected boolean keepRunning = true;
	protected final int MOD_VALUE = 8;
	protected final int BOARD_MIN = 0;
	protected final int BOARD_MAX = 63;
	protected final int VERTICAL_MOVE = 8;
	protected final int HORIZONTAL_MOVE = 1;
	protected final int DIAGONAL_MOVE1 = 7;
	protected final int DIAGONAL_MOVE2 = 9;
//	Black are lower case, White are upper case
	
	public void clearPossibleMoves(){
		possibleMoves.clear();
	}
	
	public void setHasMoved(boolean hasMoved){
		this.hasMoved = hasMoved;
	}
	
	protected void addPossibleMoves(int index, Square[] squares){
		if(!squares[index].isOccupied()){
			possibleMoves.add(squares[index].getFileRank());				
		}
		else{
			if(squares[index].getPiece().getColor().equals(this.color)){
				keepRunning = false;
			}
			else{
				possibleMoves.add(squares[index].getFileRank());
				keepRunning = false;
			}
		}
	}
	
	public Piece(char pieceSymbol, String pieceColor){
		characterPiece = pieceSymbol;
		color = pieceColor;
	}
	
	public boolean checkMove(String startSpace, String endingSpace, Square[] squares){
		boolean canMove = false;
		getPossibleMoves(startSpace, squares);
			for(int j = 0; j < possibleMoves.size() && !canMove; j++){
				if(possibleMoves.get(j).equals(endingSpace)){
					canMove = true;
				}
			}
//		possibleMoves.clear();
		return canMove;
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
