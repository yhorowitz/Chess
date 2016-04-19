package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.List;

/**
 * Abstract model of a chess piece
 */
public abstract class ChessPiece {

    private PieceColor pieceColor;
    private Position position;

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    public void setPieceColor(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Changes the position of this piece
     * @param position
     */
    public void moveTo(Position position) {
        setPosition(position);
    }

    /**
     * Checks if the current move is a legal move for this piece
     * @param position
     * @return
     */
    public boolean isLegalMove(Position position) {
        return false;
    }

    /**
     * Gets all the legal moves for a piece
     * @param game
     * @return
     */
    public abstract List<Position> getLegalMoves(ChessGame game);

    /**
     * Handles the capturing of the piece
     */
    public void capture() {
        this.setPosition(null);
    }

    /**
     * Checks if a piece has been captured
     */
    public boolean isCaptured() {
        return this.getPosition() != null;
    }

}
