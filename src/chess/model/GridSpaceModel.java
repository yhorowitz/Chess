package chess.model;

import chess.model.pieces.ChessPiece;


public class GridSpaceModel {

    private Vector position;
    private ChessPiece piece;

    GridSpaceModel(Vector position) {
        this.position = position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return false;
    }
}
