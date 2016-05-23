package chess.model.pieces;

import chess.model.BoardSpace;
import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Queen extends ChessPiece  {


    public Queen(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Queen(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {

        List<Position> legalMoves = new ArrayList<>();
        int curCol;
    	
    	//add legal moves up and right
    	curCol = 1;
    	for (int i = this.getPosition().getRow() - 1; i >= 0 && curCol < 8; i--) {
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
    	for (int i = this.getPosition().getRow() - 1; i >= 0 && curCol < 8; i--) {
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

        //add legal moves to the right
        for (int i = this.getPosition().getCol() + 1; i < 8; i++) {
            Position posToLookAt = new Position(this.getPosition().getRow(), i);
            BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);

            if (spaceToLookAt == null) {
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
        }

        //add legal moves to the left
        for (int i = this.getPosition().getCol() - 1; i >= 0; i--) {
            Position posToLookAt = new Position(this.getPosition().getRow(), i);
            BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);

            if (spaceToLookAt == null) {
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
        }

        //add legal moves going up
        for (int i = this.getPosition().getRow() - 1; i >= 0; i--) {
            Position posToLookAt = new Position(i, this.getPosition().getCol());
            BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);

            if (spaceToLookAt == null) {
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
        }

        //add legal moves going down
        for (int i = this.getPosition().getRow() + 1; i < 8; i++) {
            Position posToLookAt = new Position(i, this.getPosition().getCol());
            BoardSpace spaceToLookAt = game.getBoardSpace(posToLookAt);
            if (spaceToLookAt == null) {
                break;
            }
            else if (spaceToLookAt.isEmpty()) {
                legalMoves.add(posToLookAt);
            }
            else if (spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor()) {
                legalMoves.add(posToLookAt);
                break;
            }
            else {
                break;
            }
        }

        if (removeMovesThatCauseCheck)
            removeMovesThatCauseCheck(game, legalMoves);

        return legalMoves;
    }

    @Override
    public String getNotationLetter() {
        return "Q";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2655 + add) + "";
    }
}
