package chess.model.pieces;

import chess.model.BoardSpace;
import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Rook extends ChessPiece  {


    public Rook(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Rook(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {

        List<Position> legalMoves = new ArrayList<>();

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
        return "R";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2656 + add) + "";
    }
}
