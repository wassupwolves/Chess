package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Queen;
import Model.Square;

public class Main {
	
	public static void main(String[] args) {
		FileIO fileIO = new FileIO(args[0]);
		fileIO.readFile();
		Iterator<String> iterator = fileIO.getActions().iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
//		fileIO.getBoard().drawBoard();		
//		fileIO.getBoard().movePiece("b7", "b6");
//		fileIO.getBoard().movePiece("c7", "c5");
//		fileIO.getBoard().movePiece("d7", "d6");
//		fileIO.getBoard().movePiece("b2", "b3");
		fileIO.getBoard().drawBoard();
		Square[] squares = fileIO.getBoard().getSquares();
		Queen testRook = (Queen) squares[7].getPiece();
		ArrayList<String> places = testRook.getPossibleMoves(squares[7].getFileRank(), squares);
		for(int i = 0; i < places.size(); i++){
			System.out.println(i + places.get(i));
		}
	}	
}