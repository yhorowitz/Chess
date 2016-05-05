package chess.tests.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.pieces.Pawn;
import chess.model.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Current tests as of 3/27/16:
 *
 * Still needs testing for:
 *      Can move horizontally or vertically
 *      When the piece is in a corner
 *      When the piece is on the edge
 *      When the piece isnt on an edge and isnt blocked
 *      When the piece isnt on an edge and is blocked on some sides
 *      When the piece isnt on an edge and is completely blocked
 *
 */
public class RookTest {

    ChessGame game;

    @Before
    public void setUp() {
        game = new ChessGame(false);
    }

    @Test
    public void canMoveOnlyDownAndRightWhenInTopLeftCorner() {
        PieceColor pieceColor = PieceColor.BLACK;
        Position position = new Position(0, 0);

        Rook rook = new Rook(pieceColor, position);
        game.getBoardSpace(position).setPiece(rook);
        List<Position> legalMoves = rook.getLegalMoves(game, false);

        List<Position> whatResultsShouldBe = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            whatResultsShouldBe.add(new Position(0, i)); //add space to the right
            whatResultsShouldBe.add(new Position(i, 0));//add space below
        }

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
                && legalMoves.containsAll(whatResultsShouldBe));
    }



    @Test
    public void canMoveOnlyUpAndLeftWhenInBottomRightCorner() {
        PieceColor pieceColor = PieceColor.BLACK;
        Position position = new Position(7, 7);

        Rook rook = new Rook(pieceColor, position);
        game.getBoardSpace(position).setPiece(rook);
        List<Position> legalMoves = rook.getLegalMoves(game, false);

        List<Position> whatResultsShouldBe = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            whatResultsShouldBe.add(new Position(7, i)); //add space to the right
            whatResultsShouldBe.add(new Position(i, 7));//add space below
        }

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
                && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void canCaptureAnEnemyPiece() {
        PieceColor rookColor = PieceColor.BLACK;
        Position blackRookPosition = new Position(7, 7);
        PieceColor pawnColor = PieceColor.WHITE;
        Position whitePawnPosition = new Position(6, 7);

        Rook rook = new Rook(rookColor, blackRookPosition);
        game.getBoardSpace(blackRookPosition).setPiece(rook);
        Pawn whitePawn = new Pawn(pawnColor, whitePawnPosition);
        game.getBoardSpace(whitePawnPosition).setPiece(whitePawn);

        List<Position> legalMoves = rook.getLegalMoves(game, false);

        assertTrue(legalMoves.contains(whitePawnPosition));
    }

    @Test
    public void cannotCaptureAFriendlyPiece() {
        PieceColor rookColor = PieceColor.BLACK;
        Position blackRookPosition = new Position(7, 7);
        PieceColor pawnColor = PieceColor.BLACK;
        Position blackPawnPosition = new Position(6, 7);

        Rook rook = new Rook(rookColor, blackRookPosition);
        game.getBoardSpace(blackRookPosition).setPiece(rook);
        Pawn whitePawn = new Pawn(pawnColor, blackPawnPosition);
        game.getBoardSpace(blackPawnPosition).setPiece(whitePawn);

        List<Position> legalMoves = rook.getLegalMoves(game, false);

        assertFalse(legalMoves.contains(blackPawnPosition));
    }

}
