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
 *      Moving in the right direction depending on what color it is
 *      After its first move it can only move 1 space
 *      Reaching the edge of the board allows it to change into another piece
 *      En Passant (special chess move)
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
        game.getBoard()[row][column].setPiece(pawn);
        List<Vector> legalMoves = game.getLegalMoves(pawn);

       JOptionPane.showMessageDialog(null, legalMoves.get(0).getX() + ", " + legalMoves.get(0).getY());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(row + 1, column));
        whatResultsShouldBe.add(new Vector(row + 2, column));

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
        game.getBoard()[row][column].setPiece(pawn);

        Vector blockingPosition = new Vector(column + 2, row);
        int blockingRow = blockingPosition.getY();
        int blockingColumn = blockingPosition.getX();
        Pawn blockingPiece = new Pawn(Color.WHITE, blockingPosition);
        game.getBoard()[blockingRow][blockingColumn].setPiece(blockingPiece);

        List<Vector> legalMoves = game.getLegalMoves(pawn);

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(row + 1, column));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves)
                && legalMoves.containsAll(whatResultsShouldBe));
    }

}
