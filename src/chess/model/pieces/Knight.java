package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Knight extends ChessPiece  {

    public Knight(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Knight(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        List<Position> legalMoves = new ArrayList<>();
    	
    	Position p1 = new Position(0,0);Position p2 = new Position(0,0);Position p3 = new Position(0,0);
    	Position p4 = new Position(0,0);Position p5 = new Position(0,0);Position p6 = new Position(0,0);
    	Position p7 = new Position(0,0);Position p8 = new Position(0,0);
    	
    	if (this.getPosition().getCol()-1>=0 && this.getPosition().getRow()-2>=0)
			p1 = new Position(this.getPosition().getRow()-2, this.getPosition().getCol()-1);
		if (this.getPosition().getCol()+1<8 && this.getPosition().getRow()-2>=0)
			p2 = new Position(this.getPosition().getRow()-2, this.getPosition().getCol()+1);
		if (this.getPosition().getCol()-1>=0 && this.getPosition().getRow()+2<8)
			p3 = new Position(this.getPosition().getRow()+2, this.getPosition().getCol()-1);
		if (this.getPosition().getCol()+1<8 && this.getPosition().getRow()+2<8)
		    p4 = new Position(this.getPosition().getRow()+2, this.getPosition().getCol()+1);
		if (this.getPosition().getCol()+2<8 && this.getPosition().getRow()-1>=0)
		    p5 = new Position(this.getPosition().getRow()-1, this.getPosition().getCol()+2);
		if (this.getPosition().getCol()+2<8 && this.getPosition().getRow()+1<8)
		    p6 = new Position(this.getPosition().getRow()+1, this.getPosition().getCol()+2);
		if (this.getPosition().getCol()-2>=0 && this.getPosition().getRow()-1>=0)
			p7 = new Position(this.getPosition().getRow()-1, this.getPosition().getCol()-2);
		if (this.getPosition().getCol()-2>=0 && this.getPosition().getRow()+1<8)
			p8 = new Position(this.getPosition().getRow()+1, this.getPosition().getCol()-2); 	
		
		Position[] potentials = { p1, p2, p3, p4, p5, p6, p7, p8 };			
		
		for (Position positionToLookAt : potentials){
			BoardSpace spaceToLookAt = game.getBoardSpace(positionToLookAt);
			if (spaceToLookAt == null){
    			continue;
    		}
			if (spaceToLookAt.isEmpty()) {
				legalMoves.add(positionToLookAt);
			}
			else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
				legalMoves.add(positionToLookAt);
			}
			else {
    			continue;
    		}
		}
		
		if (removeMovesThatCauseCheck)
    		removeMovesThatCauseCheck(game, legalMoves);
    	
        return legalMoves;
    }

    @Override
    public String getNotationLetter() {
        return "N";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2658 + add) + "";
    }
}
