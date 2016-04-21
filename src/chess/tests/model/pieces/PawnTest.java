package chess.tests.model.pieces;

import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;
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
        PieceColor pieceColor = PieceColor.BLACK;
        Position position = new Position(3, 1);
        int row = position.getCol();
        int column = position.getRow();

        Pawn pawn = new Pawn(pieceColor, position);
        game.getBoardSpace(position).setPiece(pawn);
        List<Position> legalMoves = pawn.getLegalMoves(game, true);

        List<Position> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Position(row + 1, column));
        whatResultsShouldBe.add(new Position(row + 2, column));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
            && legalMoves.containsAll(whatResultsShouldBe));

    }

    @Test
    public void firstMoveCanMoveForwardOneSpaceIfBlocked() {
        Position position = new Position(3, 1);
        int row = position.getCol();
        int column = position.getRow();

        Pawn pawn = new Pawn(PieceColor.BLACK, position);
        game.getBoardSpace(position).setPiece(pawn);

        Position blockingPosition = new Position(row + 2, column);
        Pawn blockingPiece = new Pawn(PieceColor.WHITE, blockingPosition);
        game.getBoardSpace(blockingPosition).setPiece(blockingPiece);

        List<Position> legalMoves = pawn.getLegalMoves(game, true);

        List<Position> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Position(row + 1, column));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
                && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void pieceMovesToTheCorrectLocation() {
        Position startPos = new Position(3, 1);
        Pawn pawn = new Pawn(PieceColor.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        List<Position> legalMoves = pawn.getLegalMoves(game, true);
        assertTrue(legalMoves.size() > 0);
        Position endPos = legalMoves.get(0);
        game.makeMove(startPos, endPos);
        assertSame(game.getBoardSpace(endPos).getPiece(), pawn);
        assertNull(game.getBoardSpace(startPos).getPiece());
    }

    @Test
    public void pawnCanCaptureIfPieceIsInTheRightPlace() {
        if (game.getCurrentTurn() != PieceColor.BLACK)
            game.changeTurns();

        Position startPos = new Position(1, 3);
        Pawn pawn = new Pawn(PieceColor.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        Position capturePiecePos = new Position(2, 2);
        Pawn capturePiece = new Pawn(PieceColor.WHITE, capturePiecePos);
        game.getBoardSpace(capturePiecePos).setPiece(capturePiece);

        Position capturePiecePos2 = new Position(2, 4);
        Pawn capturePiece2 = new Pawn(PieceColor.WHITE, capturePiecePos);
        game.getBoardSpace(capturePiecePos2).setPiece(capturePiece2);

        List<Position> legalMoves = pawn.getLegalMoves(game, true);
        assertTrue(legalMoves.contains(capturePiecePos));
        assertTrue(legalMoves.contains(capturePiecePos2));
    }

    @Test
    public void pawnCantCaptureAPieceOfItsOwnColor() {
        if (game.getCurrentTurn() != PieceColor.BLACK)
            game.changeTurns();

        Position startPos = new Position(1, 3);
        Pawn pawn = new Pawn(PieceColor.BLACK, startPos);
        game.getBoardSpace(startPos).setPiece(pawn);

        Position capturePiecePos = new Position(2, 2);
        Pawn capturePiece = new Pawn(PieceColor.BLACK, capturePiecePos);
        game.getBoardSpace(capturePiecePos).setPiece(capturePiece);

        Position capturePiecePos2 = new Position(2, 4);
        Pawn capturePiece2 = new Pawn(PieceColor.BLACK, capturePiecePos);
        game.getBoardSpace(capturePiecePos2).setPiece(capturePiece2);

        List<Position> legalMoves = pawn.getLegalMoves(game, true);
        assertFalse(legalMoves.contains(capturePiecePos));
        assertFalse(legalMoves.contains(capturePiecePos2));
    }
}
