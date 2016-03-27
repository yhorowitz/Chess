package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;


public class King extends ChessPiece  {


    public King(Color color) {
        this.setColor(color);
    }

    public King(Color color, Vector position) {
        this.setColor(color);
        this.setCurrentPosition(position);
    }

    @Override
    public List<Vector> getLegalMoves(ChessGame game) {
        return null;
    }
}
