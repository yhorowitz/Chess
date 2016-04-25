package chess.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;

import java.util.ArrayList;
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
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        return new ArrayList<>();
    }

    @Override
    public String getNotationLetter() {
        return "K";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2654 + add) + "";
    }

}
