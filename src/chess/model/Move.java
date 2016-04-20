package chess.model;

import chess.model.pieces.*;

/**
 * Holds a single move made by a piece
 *
 * TODO need to add support for casteling and pawn promotions
 */
public class Move {

    private ChessPiece piece;
    private Position startPosition;
    private Position endPosition;

    private boolean isCaptureMove;
    private ChessPiece capturedPiece;
    private Position capturePosition;

    public Move(ChessPiece piece, Position start, Position end) {
        setPiece(piece);
        setStartPosition(start);
        setEndPosition(end);
    }

    public ChessPiece getPiece() {
        return piece;
    }

    private void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public PieceColor getPieceColor() {
        return piece.getPieceColor();
    }

    public Position getStartPosition() {
        return startPosition;
    }

    private void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    private void setEndPosition(Position endPosition) {
        this.endPosition = endPosition;
    }

    public void setAsCaptureMove(ChessPiece capturedPiece, Position capturePosition) {
        this.isCaptureMove = true;
        setCapturedPiece(capturedPiece);
        setCapturePosition(capturePosition);
    }

    public void setAsNonCaptureMove() {
        this.isCaptureMove = false;
        setCapturedPiece(null);
        setCapturePosition(null);
    }

    public boolean isCaptureMove() {
        return isCaptureMove;
    }

    public ChessPiece getCapturedPiece() {
        return capturedPiece;
    }

    private void setCapturedPiece(ChessPiece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public Position getCapturePosition() {
        return capturePosition;
    }

    private void setCapturePosition(Position capturePosition) {
        this.capturePosition = capturePosition;
    }

    public String getAlgebraicNotation() {
        StringBuilder notation = new StringBuilder(8);

        notation.append(piece.getNotationSymbol());         //add piece type
        if (isCaptureMove())                                //add capture notation
            notation.append("x");
        notation.append((char)endPosition.getCol() + 97);   //add column
        notation.append(8 - endPosition.getRow());           //add row

        return notation.toString();
    }

    public String getDetailedDescription() {
        StringBuilder notation = new StringBuilder(100);

        notation.append(piece.getPieceColor() + " " + piece.getClass().getSimpleName());
        notation.append(" moved from " + ((char)startPosition.getCol() + 97) + (8 - startPosition.getRow()));
        notation.append( " to " + ((char)endPosition.getCol() + 97) + (8 - endPosition.getRow()));
        if (isCaptureMove()) {
            notation.append(" capturing " + capturedPiece.getPieceColor() + " " + capturedPiece.getClass().getSimpleName());
            if(endPosition.equals(capturePosition)) {
                notation.append(" with an En Passant");
            }
        }

        return notation.toString();
    }
}