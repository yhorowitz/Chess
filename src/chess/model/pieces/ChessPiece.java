package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.Iterator;
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
     * @param removeMovesThatCauseCheck
     * @return
     */
    public abstract List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck);

    /**
     * Gets the notation letter for the piece
     * @return
     */
    public abstract String getNotationLetter();

    /**
     * Gets the notation symbol for the piece
     * @return
     */
    public abstract String getNotationSymbol();

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

    /**
     * Removes all moves that cause check to its own king
     *
     * @param game
     * @param currLegalMoves
     */
    protected void removeMovesThatCauseCheck(ChessGame game, List<Position> currLegalMoves) {
        Iterator iter = currLegalMoves.iterator();
        while (iter.hasNext()) {
            Position move = (Position) iter.next();

            if (move == null)
                iter.remove();
            else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), move))
                iter.remove();
        }
    }

}
