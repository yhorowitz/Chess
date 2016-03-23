package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;

import java.util.List;


public class Rook extends ChessPiece  {


    public Rook(Color color) {
        this.setColor(color);
    }

    public Rook(Color color, Vector position) {
        this.setColor(color);
        this.setCurrentPosition(position);
    }

    @Override
    List<Vector> getLegalMoves(ChessGame game) {
        return null;
    }
}
