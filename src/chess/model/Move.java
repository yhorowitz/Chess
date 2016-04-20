package chess.model;

import chess.model.pieces.*;

/**
 * Holds a single move made by a piece
 *
 * TODO need to add support for castling and pawn promotions
 */
public class Move {

    private ChessPiece piece;
    private Position startPosition;
    private Position endPosition;

    private boolean isCaptureMove;
    private ChessPiece capturedPiece;
    private Position capturePosition;

    private boolean isCheck = false;
    private boolean isCheckMate = false;

    public Move(ChessPiece piece, Position start, Position end) {
        setPiece(piece);
        setStartPosition(start);
        setEndPosition(end);
    }

    public void setAsCheck() {
        this.isCheck = true;
    }

    public void setAsCheckMate() {
        this.isCheckMate = true;
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
        StringBuilder notation = new StringBuilder();

        notation.append(piece.getNotationSymbol());         //add piece type
        if (isCaptureMove())                                //add capture notation
            notation.append("x");
        notation.append((char)(endPosition.getCol() + 97));   //add column
        notation.append(8 - endPosition.getRow() + "");       //add row

        if (isCheckMate)
            notation.append("++");
        else if (isCheck)
            notation.append("+");

        return notation.toString();
    }

    public String getDetailedDescription() {
        StringBuilder notation = new StringBuilder(100);

        String movingPieceColor = piece.getPieceColor().toString().equals("White") ? "White" : "Black";

        notation.append(movingPieceColor + " " + piece.getClass().getSimpleName());
        notation.append(" moved from " + ((char)(endPosition.getCol() + 97)) + (8 - startPosition.getRow()));
        notation.append( " to " + ((char)(endPosition.getCol() + 97)) + (8 - endPosition.getRow()));
        if (isCaptureMove()) {
            String capturedPieceColor = capturedPiece.getPieceColor().toString().equals("White") ? "White" : "Black";

            notation.append(" capturing " + capturedPieceColor + " " + capturedPiece.getClass().getSimpleName());
            if(!endPosition.equals(capturePosition)) {
                notation.append(" with an En Passant");
            }
        }

        if (isCheckMate) {
            String checkMatedColor = movingPieceColor.equals("White") ? "Black" : "White";
            notation.append(" checkmating the " + checkMatedColor + " King");
        }
        else if (isCheck) {
            String checkedColor = movingPieceColor.equals("White") ? "Black" : "White";
            notation.append(" putting the " + checkedColor + " King in check");

        }

        return notation.toString();
    }
}
