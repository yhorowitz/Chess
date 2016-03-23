package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;


public class Queen extends ChessPiece  {


    public Queen(Color color) {
        this.setColor(color);
    }

    public Queen(Color color, Vector position) {
        this.setColor(color);
        this.setCurrentPosition(position);
    }

    @Override
    List<Vector> getLegalMoves(ChessGame game) {
        return null;
    }
}
