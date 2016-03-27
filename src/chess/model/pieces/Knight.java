package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;


public class Knight extends ChessPiece  {

    public Knight(Color color) {
        this.setColor(color);
    }

    public Knight(Color color, Vector position) {
        this.setColor(color);
        this.setCurrentPosition(position);
    }

    @Override
    public List<Vector> getLegalMoves(ChessGame game) {
        return null;
    }
}
