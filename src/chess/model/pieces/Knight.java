package chess.model.pieces;

import chess.model.BoardSpace;
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

		List<Position> possibleMoves = new ArrayList<>();

		possibleMoves.add(new Position(this.getPosition().getRow()-2, this.getPosition().getCol()-1));
		possibleMoves.add(new Position(this.getPosition().getRow()-2, this.getPosition().getCol()+1));
		possibleMoves.add(new Position(this.getPosition().getRow()+2, this.getPosition().getCol()-1));
		possibleMoves.add(new Position(this.getPosition().getRow()+2, this.getPosition().getCol()+1));
		possibleMoves.add(new Position(this.getPosition().getRow()-1, this.getPosition().getCol()-2));
		possibleMoves.add(new Position(this.getPosition().getRow()-1, this.getPosition().getCol()+2));
		possibleMoves.add(new Position(this.getPosition().getRow()+1, this.getPosition().getCol()-2));
		possibleMoves.add(new Position(this.getPosition().getRow()+1, this.getPosition().getCol()+2));

		for (Position move : possibleMoves) {
			if (move != null && move.getCol() >= 0 && move.getCol() < 8 && move.getRow() >=0 && move.getRow() < 8) {
				BoardSpace spaceToLookAt = game.getBoardSpace(move);

				if (spaceToLookAt.isEmpty() || spaceToLookAt.getPiece().getPieceColor() != this.getPieceColor())
					legalMoves.add(move);
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
