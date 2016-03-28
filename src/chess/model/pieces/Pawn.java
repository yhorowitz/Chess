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

    public boolean hasMoved() {
        return moved;
    }

    public void moveTo(Vector vector) {
        super.moveTo(vector);
        this.moved = true;
    }

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
