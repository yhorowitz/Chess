package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.List;


public class King extends ChessPiece  {


    public King(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public King(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game) {
        return null;
    }

    @Override
    public String getNotationSymbol() {
        return "K";
    }
}
