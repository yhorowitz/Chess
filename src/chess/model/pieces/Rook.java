package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.List;


public class Rook extends ChessPiece  {


    public Rook(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Rook(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game) {
        return null;
    }

    @Override
    public String getNotationSymbol() {
        return "R";
    }
}
