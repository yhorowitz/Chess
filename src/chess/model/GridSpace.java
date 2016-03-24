package chess.model;

import chess.model.pieces.ChessPiece;


public class GridSpace {

    private Vector position;
    private ChessPiece piece;
    private boolean highlighted;

    public GridSpace(Vector position) {
        this.position = position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public Vector getPosition() {
        return this.position;
    }

    public boolean isOccupied() {
        return false;
    }

    public void highlight(boolean highlight) {
        this.highlighted = highlight;
    }

    public boolean isHighlighted() {
        return this.highlighted;
    }
}
