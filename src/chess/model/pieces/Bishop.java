package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Position;

import java.util.List;


public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        this.setColor(color);
    }

    public Bishop(Color color, Position position) {
        this.setColor(color);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game) {
        return null;
    }
}
