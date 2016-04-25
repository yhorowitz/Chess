package chess.tests.model;


import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.PlayerPieceSet;
import chess.model.Position;
import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;
import chess.model.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessGameTest {
    ChessGame game;
    ChessPiece blackKing;
    ChessPiece whiteKing;

    @Before
    public void setUp() {
        game = new ChessGame();
        //blackKing = addNewPieceToGame(new King(PieceColor.BLACK), new Position(0,4));
        //whiteKing = addNewPieceToGame(new King(PieceColor.WHITE), new Position(7,4));
    }

    private ChessPiece addNewPieceToGame(ChessPiece piece, Position position) {
        PieceColor color = piece.getPieceColor();
        PlayerPieceSet pieceSet = color == PieceColor.BLACK ? game.getBlackPieces() : game.getWhitePieces();
        piece.setPosition(position);
        pieceSet.addPiece(piece);
        game.getBoardSpace(position).setPiece(piece);
        return piece;
    }

    private void changeKingsPosition(PieceColor color, Position moveToPosition) {
        PlayerPieceSet pieceSet = color == PieceColor.BLACK ? game.getBlackPieces() : game.getWhitePieces();
        King king = (King) pieceSet.getAlivePiecesOfType(King.class).get(0);
        game.getBoardSpace(king.getPosition()).setPiece(null);
        king.setPosition(moveToPosition);
        game.getBoardSpace(moveToPosition).setPiece(king);
    }

    @Test
    public void moveCausesCheckWhenKingIsAlreadyInCheck() {

        //play out actual game (The moveCausesCheckMethodRequires a history of game moves)
        Position from = new Position(6, 4);
        Position to = new Position(4, 4);
        game.makeMove(from, to);

        from = new Position(1, 5);
        to = new Position(3, 5);
        game.makeMove(from, to);

        from = new Position(4, 4);
        to = new Position(3, 5);
        game.makeMove(from, to);

        from = new Position(1, 1);
        to = new Position(2, 1);
        game.makeMove(from, to);

        from = new Position(3, 5);
        to = new Position(2, 5);
        game.makeMove(from, to);

        from = new Position(2, 1);
        to = new Position(3, 1);
        game.makeMove(from, to);

        from = new Position(2, 5);
        to = new Position(1, 5);
        game.makeMove(from, to);

        //king should now be in check
        assertTrue(game.isBlackInCheck());

        from = new Position(3, 1);
        to = new Position(4, 1);
        assertTrue(game.moveCausesCheckForItsOwnKing(from, to));

        //check that the king is still in check after making the move anyway
        game.makeMove(from, to);
        assertTrue(game.isBlackInCheck());
        assertTrue(game.isBlackCheckmated());
        assertTrue(game.isCheckmate());
    }
}
