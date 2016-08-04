package Controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Piece;
import Model.Square;

public class Main {
	
	public static void main(String[] args) {
		FileIO fileIO = new FileIO(args[0]);
		fileIO.readFile();
//		Iterator<String> iterator = fileIO.getActions().iterator();
//		while(iterator.hasNext()){
//			fileIO.getBoard().drawBoard();
//			System.out.println(iterator.next());
//		}
//		fileIO.getBoard().drawBoard();
//		Square[] squares = fileIO.getBoard().getSquares();
//		Piece testRook = squares[35].getPiece();
//		ArrayList<String> places = testRook.getPossibleMoves(squares[35].getFileRank(), squares);
//		for(int i = 0; i < places.size(); i++){
//			System.out.println(i + places.get(i));
//		}
	}	
}