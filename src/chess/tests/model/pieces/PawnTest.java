package chess.tests.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;
import chess.model.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Current tests as of 3/27/16:
 *      It can only move forward (based on color)
 *      Its first move can move 2 spaces
 *      Its first move can only move 1 space if it is blocked
 *      When a piece is moved it is moved to the right place
 *      A piece can be captured if it is the opposing color and in the right location
 *      A piece can not be captured if it is of the same color
 *
 * Still needs testing for:
 *      Moving in the right direction depending on what color it is
 *      After its first move it can only move 1 space
 *      Reaching the edge of the board allows it to change into another piece
 *      En Passant (special chess move)
 *      What happens when the piece moves to an area it is not allowed to move to
 *      Check legal move when at the edge of the board (pawns can move sideways during capture)
 *
 */
public class PawnTest {
    ChessGame game;

    @Before
    public void setUp() {
        game = new ChessGame(false);
    }

    @Test
    public void firstMoveCanMoveForwardTwoSpacesIfNotBlocked() {
        Color color = Color.BLACK;
        Vector position = new Vector(1, 3);
        int row = position.getY();
        int column = position.getX();

        Pawn pawn = new Pawn(color, position);
        game.getBoardSpace(position).setPiece(pawn);
        List<Vector> legalMoves = pawn.getLegalMoves(game);

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(column, row + 1));
        whatResultsShouldBe.add(new Vector(column, row + 2));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
            && legalMoves.containsAll(whatResultsShouldBe));

    }

    @Test
    public void firstMoveCanMoveForwardOneSpaceIfBlocked() {
        Vector position = new Vector(1, 3);
        int row = position.getY();
        int column = position.getX();

        Pawn pawn = new Pawn(Color.BLACK, position);
        game.getBoardSpace(position).setPiece(pawn);

        Vector blockingPosition = new Vector(column, row + 2);
        Pawn blockingPiece = new Pawn(Color.WHITE, blockingPosition);
        game.getBoardSpace(blockingPosition).setPiece(blockingPiece);

        List<Vector> legalMoves = pawn.getLegalMoves(game);

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(column, row + 1));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
                && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void pieceMovesToTheCorrectLocation() {
        Vector startPos = new Vector(1, 3);
        Pawn pawn = new Pawn(Color.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        List<Vector> legalMoves = pawn.getLegalMoves(game);
        assertTrue(legalMoves.size() > 0);
        Vector endPos = legalMoves.get(0);
        game.makeMove(pawn, startPos, endPos);
        assertSame(game.getBoardSpace(endPos).getPiece(), pawn);
        assertNull(game.getBoardSpace(startPos).getPiece());
    }



    @Test
    public void pawnCanCaptureIfPieceIsInTheRightPlace() {
        if (game.getCurrentTurn() != Color.BLACK)
            game.changeTurns();

        Vector startPos = new Vector(3, 1);
        Pawn pawn = new Pawn(Color.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        Vector capturePiecePos = new Vector(2,2);
        Pawn capturePiece = new Pawn(Color.WHITE, capturePiecePos);
        game.getBoardSpace(capturePiecePos).setPiece(capturePiece);

        Vector capturePiecePos2 = new Vector(4,2);
        Pawn capturePiece2 = new Pawn(Color.WHITE, capturePiecePos);
        game.getBoardSpace(capturePiecePos2).setPiece(capturePiece2);

        List<Vector> legalMoves = pawn.getLegalMoves(game);
        assertTrue(legalMoves.contains(capturePiecePos));
        assertTrue(legalMoves.contains(capturePiecePos2));
    }

    @Test
    public void pawnCantCaptureAPieceOfItsOwnColor() {
        if (game.getCurrentTurn() != Color.BLACK)
            game.changeTurns();

        Vector startPos = new Vector(3, 1);
        Pawn pawn = new Pawn(Color.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        Vector capturePiecePos = new Vector(2,2);
        Pawn capturePiece = new Pawn(Color.BLACK, capturePiecePos);
        game.getBoardSpace(capturePiecePos).setPiece(capturePiece);

        Vector capturePiecePos2 = new Vector(4,2);
        Pawn capturePiece2 = new Pawn(Color.BLACK, capturePiecePos);
        game.getBoardSpace(capturePiecePos2).setPiece(capturePiece2);

        List<Vector> legalMoves = pawn.getLegalMoves(game);
        assertFalse(legalMoves.contains(capturePiecePos));
        assertFalse(legalMoves.contains(capturePiecePos2));
    }
}
