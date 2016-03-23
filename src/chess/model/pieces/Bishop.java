package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;


public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        this.setColor(color);
    }

    public Bishop(Color color, Vector position) {
        this.setColor(color);
        this.setCurrentPosition(position);
    }

    @Override
    List<Vector> getLegalMoves(ChessGame game) {
        return null;
    }
}
