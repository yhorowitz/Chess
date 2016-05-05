package chess.model;

import chess.model.pieces.ChessPiece;


public class BoardSpace {

    private Position position;
    private ChessPiece piece;

    public BoardSpace(Position position) {
        this.position = position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Checks if this space has a piece on it
     * @return
     */
    public boolean isOccupied() {
        return this.piece != null;
    }

    public boolean isEmpty() {
        return !isOccupied();
    }

}
