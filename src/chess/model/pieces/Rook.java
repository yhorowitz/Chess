package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
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
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        return new ArrayList<>();
    }

    @Override
    public String getNotationLetter() {
        return "R";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2656 + add) + "";
    }
}
