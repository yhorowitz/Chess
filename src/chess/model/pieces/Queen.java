package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Position;

import java.util.List;


public class Queen extends ChessPiece  {


    public Queen(Color color) {
        this.setColor(color);
    }

    public Queen(Color color, Position position) {
        this.setColor(color);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game) {
        return null;
    }
}
