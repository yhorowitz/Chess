package chess.model.pieces;

import chess.model.BoardSpace;
import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.ArrayList;
import java.util.List;


public class Pawn extends ChessPiece  {

    private boolean moved = false;

    public Pawn(Color color) {
        this.setColor(color);
    }

    public Pawn(Color color, Vector position) {
        this.setColor(color);
        this.setPosition(position);
    }

    /**
     * Checks if the pawn has moved yet. Needed to know if it can move 1 space or 2
     * @return
     */
    public boolean hasMoved() {
        return moved;
    }

    @Override
    public void moveTo(Vector vector) {
        super.moveTo(vector);
        this.moved = true;
    }

    /**
     * Pawns can make the following moves
     *
     *  - It Can only move forward when not capturing a piece. (Down the board if black and up if white)
     *  - If it is the pawn's first move it can move 1 or 2 spaces if not blocked
     *  - If it is not the pawn's first move it can move 1 space forward if not blocked
     *
     *  - When capturing it must move forward 1 space but can only capture a piece on either side of it (not directly ahead)
     *  - It can capture another pawn that is directly next to it if that pawn just moved 2 spaces forward last turn.
     *    This is known as an En Passant and it moves in the regular manner of capturing and would therefore occupy the space
     *    the opposing pawn would have been in if it had moved only 1 space forward. This can only be done on the turn immediately
     *    following the opposing pawn moving forward 2 spaces
     *
     * @param game
     * @return
     */
    @Override
    public List<Vector> getLegalMoves(ChessGame game) {
        //used to determine which direction the pawn can move based on its color
        int direction = this.getColor() == Color.BLACK ? 1 : -1;
        //how many spaces forward it can move based on its color
        int spacesCanMove = hasMoved() ? 1 : 2;

        int currentRow = this.getPosition().getY();
        int currentColumn = this.getPosition().getX();

        List<Vector> legalMoves = new ArrayList<>();

        //add any spaces 1 ahead (or 2 if first move) that arent blocked
        for (int i = 1; i <= spacesCanMove; i++) {
            int newRow = currentRow + (i * direction);
            Vector spaceBeingChecked = new Vector(currentColumn, newRow);

            if (!game.getBoardSpace(spaceBeingChecked).isOccupied()) {
                legalMoves.add(spaceBeingChecked);
            }
            else
                break;
        }

        //check if any spaces have a piece that can be captured
        if (currentColumn - 1 >= 0) {
            Vector vectorForCapture = new Vector(currentColumn - 1, currentRow + direction);
            BoardSpace captureSpace = game.getBoardSpace(vectorForCapture);
            if(captureSpace.isOccupied() && captureSpace.getPiece().getColor() != game.getCurrentTurn()){
                legalMoves.add(vectorForCapture);
            }
        }
        if (currentColumn + 1 < 8) {
            Vector vectorForCapture = new Vector(currentColumn + 1, currentRow + direction);
            BoardSpace captureSpace = game.getBoardSpace(vectorForCapture);
            if(captureSpace.isOccupied() && captureSpace.getPiece().getColor() != game.getCurrentTurn()){
                legalMoves.add(vectorForCapture);
            }
        }

        return legalMoves;
    }
}
