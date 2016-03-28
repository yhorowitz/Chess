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
    private Vector position;

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    /**
     * Changes the position of this piece
     * @param vector
     */
    public void moveTo(Vector vector) {
        setPosition(vector);
    }

    /**
     * Checks if the current move is a legal move for this piece
     * @param vector
     * @return
     */
    public boolean isLegalMove(Vector vector) {
        return false;
    }

    /**
     * Gets all the legal moves for a piece
     * @param game
     * @return
     */
    public abstract List<Vector> getLegalMoves(ChessGame game);

}
