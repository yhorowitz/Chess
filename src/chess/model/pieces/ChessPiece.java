package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;

/**
 * Abstract model of a chess piece
 */
public abstract class ChessPiece {

    private Color color;
    private Vector currentPosition;

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Vector currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void moveTo(Vector vector) {
        setCurrentPosition(vector);
    }

    public boolean isLegalMove(Vector vector) {
        return false;
    }

    public abstract List<Vector> getLegalMoves(ChessGame game);

}
