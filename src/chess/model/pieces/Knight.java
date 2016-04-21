package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
import java.util.List;


public class Knight extends ChessPiece  {

    public Knight(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public Knight(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        return new ArrayList<>();
    }

    @Override
    public String getNotationSymbol() {
        return "N";
    }
}
