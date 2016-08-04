package Model;

public class Board {

	private final int boardSize = 64;
	private Square[] squares = new Square[boardSize];
	private boolean pieceTaken = false;
//	private Piece [] pieces = new Piece[32];
	
	public Board(){		
		populateSquares();
//		populatePieces();
	}
	
//	public void populatePieces(){
//		for(int i = 0; i < pieces.length; i++){
//			pieces[i] = new Piece('-', "NoColor");
//		}
//	}
	
	public boolean attemptMove(String startSpace, String endSpace){
		boolean successfulMove = false;
		for(int i = 0; i < squares.length && !successfulMove; i++){
			if(squares[i].getFileRank().equals(startSpace)){
				if(squares[i].getPiece().checkMove(startSpace, endSpace, squares)){
//					Move a Piece
//					Swap Turns
					squares[i] = movePiece(squares[i], endSpace);
					successfulMove = true;					
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
				squares[i].getPiece().clearPossibleMoves();
				startSquare.setPiece(emptyPiece);
				startSquare.setOccupied(false);
				
			}
		}
		return startSquare;
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
	
	public boolean getPieceTaken(){
		return pieceTaken;
	}
}
