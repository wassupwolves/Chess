package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Board;
import Model.Piece;
import Model.Square;

public class UserInteraction {
	
	private TurnHandler handle;
	private CheckMove checkMove;
	private ArrayList<String> moves = new ArrayList<String>();
	private boolean canMove = true;
	private boolean checkMate = false;
	
	public UserInteraction(TurnHandler handle, CheckMove checkMove){
		this.handle = handle;
		this.checkMove = checkMove;
	}
	
	public boolean getCheckMate(){
		return checkMate;
	}
	
	public void promptStartSpace(Board board){
		Scanner scan = new Scanner(System.in);
		moves.clear();
		if(!checkMate){
			System.out.print("Which square would you like to move a piece from: ");
			String startSpace = scan.nextLine();
			Square[] squares = checkMove.getSquares();
			Piece avoidCheckingCheck = new Piece('-', "NoColor");
			for(int i = 0; i < squares.length; i++){
				if(squares[i].getFileRank().equals(startSpace)){
					checkMove.checkPossibleMoves(squares[i].getPiece());
					moves = checkMove.getFinalPossibleSquares();
					avoidCheckingCheck = squares[i].getPiece();
				}
			}
			printPossibleMoves(avoidCheckingCheck);
			if(canMove){
				String endSpace = promptEndSpace();
				if(!board.attemptMove(startSpace, endSpace)){
					System.out.println("ERROR: Couldn't move piece from " + startSpace + " to " + endSpace);
//					movement = "ERROR: Couldn't move piece from " + startSpace + " to " + finalSpace;
				}
				else{
					if(board.getPieceTaken()){
						System.out.println("Piece from " + startSpace + " took piece at " + endSpace);
//						movement += "Piece from " + startSpace + " took piece at " + endSpace;
					}
					else{
						System.out.println("Piece from " + startSpace + " moved to " + endSpace);
//						movement += "Piece from " + startSpace + " moved to " + endSpace;
					}
				}
			}		
		}	
		board.drawBoard();
	}
	
	private String promptEndSpace(){
		Scanner scan = new Scanner(System.in);			
		System.out.print("Where would you like to move: ");
		return scan.nextLine();
	}
	
	private void printPossibleMoves(Piece piece){
		canMove = true;	
		if(moves.size() == 0){
			System.out.println("No moves are available for that piece!");
			canMove = false;
			if(piece.getColor().equals(handle.getColor())){
				if(checkMove.getCheckMate()){
					System.out.println(handle.getColor() + " King is in CHECKMATE!");
					checkMate = true;
				}
			}			
		}
		for(int i = 0; i < moves.size(); i++){
			System.out.print(moves.get(i) + " ");
		}
		System.out.println();
	}

}
