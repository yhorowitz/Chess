package chess.model;

import chess.model.pieces.ChessPiece;


public class BoardSpace {

    private Vector position;
    private ChessPiece piece;
    private boolean highlighted;
    private boolean selected;

    public BoardSpace(Vector position) {
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

    public void select(boolean select) {
        this.selected = select;
        if (select) {
            this.highlight(false);
        }
    }

    public boolean isSelected() {
        return this.selected;
    }
}
