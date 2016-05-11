package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Bishop extends ChessPiece {

    public Bishop(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Bishop(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        List<Position> legalMoves = new ArrayList<>();
    	int curCol;
    	
    	//add legal moves up and right
    	curCol = 1;
    	for (int i = this.getPosition().getRow() - 1; i > 0 && curCol < 8; i--) {
    		Position posToLookAt = new Position(i, this.getPosition().getCol() + curCol);
    		BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);
    		
    		if (spaceToLookAt == null){
    			break;
    		}
    		if (spaceToLookAt.isEmpty()) {
    			legalMoves.add(posToLookAt);
    		}
    		else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
    			legalMoves.add(posToLookAt);
    			break;
    		}
    		else {
    			break;
    		}
    		
    		curCol++;
    	}
    	
    	//add legal moves up and left
    	curCol = 1;
    	for (int i = this.getPosition().getRow() - 1; i > 0 && curCol < 8; i--) {
    		Position posToLookAt = new Position(i, this.getPosition().getCol() - curCol);
    		BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);
    		
    		if (spaceToLookAt == null){
    			break;
    		}
    		if (spaceToLookAt.isEmpty()) {
    			legalMoves.add(posToLookAt);
    		}
    		else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
    			legalMoves.add(posToLookAt);
    			break;
    		}
    		else {
    			break;
    		}
    		
    		curCol++;
    	}
    	
    	//add legal moves down and right
    	curCol = 1;
    	for (int i = this.getPosition().getRow() + 1; i < 8 && curCol < 8; i++) {
    		Position posToLookAt = new Position(i, this.getPosition().getCol() + curCol);
    		BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);
    		
    		if (spaceToLookAt == null){
    			break;
    		}
    		if (spaceToLookAt.isEmpty()) {
    			legalMoves.add(posToLookAt);
    		}
    		else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
    			legalMoves.add(posToLookAt);
    			break;
    		}
    		else {
    			break;
    		}
    		
    		curCol++;
    	}
    	
    	//add legal moves down and left
    	curCol = 1;
    	for (int i = this.getPosition().getRow() + 1; i < 8 && curCol < 8; i++) {
    		Position posToLookAt = new Position(i, this.getPosition().getCol() - curCol);
    		BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);
    		
    		if (spaceToLookAt == null){
    			break;
    		}
    		if (spaceToLookAt.isEmpty()) {
    			legalMoves.add(posToLookAt);
    		}
    		else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
    			legalMoves.add(posToLookAt);
    			break;
    		}
    		else {
    			break;
    		}
    		
    		curCol++;
    	}    	
    	
    	if (removeMovesThatCauseCheck)
    		removeMovesThatCauseCheck(game, legalMoves);
    	
    	return legalMoves;
    }

    @Override
    public String getNotationLetter() {
        return "B";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2657 + add) + "";
    }
}
