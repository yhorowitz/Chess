package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.List;


public class Queen extends ChessPiece  {


    public Queen(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Queen(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game) {
        return null;
    }

    @Override
    public String getNotationSymbol() {
        return "Q";
    }
}
