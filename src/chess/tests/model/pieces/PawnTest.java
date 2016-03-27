package chess.tests.model.pieces;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.Vector;
import chess.model.pieces.Pawn;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Current tests as of 3/27/16:
 *      It can only move forward (based on color)
 *      Its first move can move 2 spaces
 *      Its first move can only move 1 space if it is blocked
 *
 *
 * Still needs testing for:
 *      When a piece is moved it is moved to the right place
 *      Moving in the right direction depending on what color it is
 *      After its first move it can only move 1 space
 *      Reaching the edge of the board allows it to change into another piece
 *      En Passant (special chess move)
 *      What happens when the piece moves to an area it is not allowed to move to
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
        System.out.println(legalMoves.size());
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
        Vector endPos = legalMoves.get(0);
        game.makeMove(pawn, startPos, endPos);
        assertSame(game.getBoardSpace(endPos).getPiece(), pawn);
    }

}
