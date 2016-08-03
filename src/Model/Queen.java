package Model;

import java.util.ArrayList;

public class Queen extends Piece{
	
	public Queen(char pieceSymbol, String pieceColor){
		super(pieceSymbol, pieceColor);
	}
	
	public boolean move(String startingSpot, String endingSpot){
		boolean canMove = false;
		return canMove;
	}
	
	public ArrayList<String> getPossibleMoves(String startSpace, Square[] squares){
		ArrayList<String> possibleMoves = new ArrayList<String>();
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
//		This checks to the left of the rook
		for(int j = boardIndex-1; j%8 < boardIndex%8 && j >= 0; j--){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}		
		}
//		This checks to the right of the rook
		for(int j = boardIndex+1; j%8 > boardIndex%8 && j <= 63; j++){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}			
		}
//		This checks above the rook
		for(int j = boardIndex-8; j >= 0 && j >= 0; j -= 8){		
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}			
		}
//		This checks below the rook
		for(int j = boardIndex+8; j <= 63 && j <= 63; j += 8){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}			
		}		
//		This will check up and left movement for -7
		for(int j = boardIndex-7; j%8 > boardIndex%8 && j >= 0; j -= 7){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}		
		}
//		This will check down and right movement for +7
		for(int j = boardIndex+7; j%8 < boardIndex%8 && j <= 63; j += 7){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}			
		}
//		This will check up and left movement for -9
		for(int j = boardIndex-9; j%8 < boardIndex%8 && j >= 0; j -= 9){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}		
		}
//		This will check down and right movement for +9
		for(int j = boardIndex+9; j%8 > boardIndex%8 && j <= 63; j += 9){
			if(!squares[j].isOccupied()){
				possibleMoves.add(squares[j].getFileRank());				
			}
			else{
				if(squares[j].getPiece().getColor().equals(this.color)){
					break;
				}
				else{
					possibleMoves.add(squares[j].getFileRank());
					break;
				}
			}		
		}	
		return possibleMoves;
	}

}
