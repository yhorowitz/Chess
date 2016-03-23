package chess.tests.model.pieces;


import chess.model.ChessGame;
import chess.model.Color;
import chess.model.pieces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import chess.model.Vector;

import static org.junit.Assert.*;

public class KingTest {

    private ChessGame game;

    @Before
    public void setUp() {
        //set up a new empty chess game model for each test
        game = new ChessGame(false);
    }

    @Test
    public void LegalMovesWhenKingIsInTheTopLeftCorner() {
        final int X = 0;
        final int Y = 0;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(0, 1));
        whatResultsShouldBe.add(new Vector(1, 0));
        whatResultsShouldBe.add(new Vector(1, 1));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsInTheTopRightCorner() {
        final int X = 7;
        final int Y = 0;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(7, 1));
        whatResultsShouldBe.add(new Vector(6, 0));
        whatResultsShouldBe.add(new Vector(6, 1));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsInTheBottomLeftCorner() {
        final int X = 0;
        final int Y = 7;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(0, 6));
        whatResultsShouldBe.add(new Vector(1, 6));
        whatResultsShouldBe.add(new Vector(1, 7));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsInTheBottomRightCorner() {
        final int X = 7;
        final int Y = 7;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(6, 6));
        whatResultsShouldBe.add(new Vector(6, 7));
        whatResultsShouldBe.add(new Vector(7, 6));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsOnTheTopEdge() {
        final int X = 4;
        final int Y = 0;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(0, 3));
        whatResultsShouldBe.add(new Vector(0, 5));
        whatResultsShouldBe.add(new Vector(1, 3));
        whatResultsShouldBe.add(new Vector(1, 4));
        whatResultsShouldBe.add(new Vector(1, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsOnTheBottomEdge() {
        final int X = 4;
        final int Y = 7;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(7, 3));
        whatResultsShouldBe.add(new Vector(7, 5));
        whatResultsShouldBe.add(new Vector(6, 3));
        whatResultsShouldBe.add(new Vector(6, 4));
        whatResultsShouldBe.add(new Vector(6, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsOnTheRightEdge() {
        final int X = 7;
        final int Y = 4;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(7, 3));
        whatResultsShouldBe.add(new Vector(7, 5));
        whatResultsShouldBe.add(new Vector(6, 3));
        whatResultsShouldBe.add(new Vector(6, 4));
        whatResultsShouldBe.add(new Vector(6, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsOnTheLeftEdge() {
        final int X = 0;
        final int Y = 4;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(0, 3));
        whatResultsShouldBe.add(new Vector(0, 5));
        whatResultsShouldBe.add(new Vector(1, 3));
        whatResultsShouldBe.add(new Vector(1, 4));
        whatResultsShouldBe.add(new Vector(1, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsInMiddle() {
        final int X = 4;
        final int Y = 4;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(3, 3));
        whatResultsShouldBe.add(new Vector(3, 4));
        whatResultsShouldBe.add(new Vector(3, 5));
        whatResultsShouldBe.add(new Vector(4, 3));
        whatResultsShouldBe.add(new Vector(4, 5));
        whatResultsShouldBe.add(new Vector(5, 3));
        whatResultsShouldBe.add(new Vector(5, 4));
        whatResultsShouldBe.add(new Vector(5, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }

    @Test
    public void LegalMovesWhenKingIsInMiddleAndHasPiecesInItsPath() {
        final int X = 4;
        final int Y = 4;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        game.getBoard()[Y - 1][X + 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y - 1][X + 1].getPosition()));
        game.getBoard()[Y][X - 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y][X - 1].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        List<Vector> whatResultsShouldBe = new ArrayList<>();
        whatResultsShouldBe.add(new Vector(3, 3));
        whatResultsShouldBe.add(new Vector(3, 5));
        whatResultsShouldBe.add(new Vector(4, 3));
        whatResultsShouldBe.add(new Vector(4, 5));
        whatResultsShouldBe.add(new Vector(5, 4));
        whatResultsShouldBe.add(new Vector(5, 5));

        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
    }



    @Test
    public void LegalMovesWhenKingIsInMiddleAndIsCompletelySurrounded() {
        final int X = 4;
        final int Y = 4;

        game.getBoard()[Y][X].setPiece(new King(Color.BLACK, game.getBoard()[Y][X].getPosition()));
        game.getBoard()[Y - 1][X - 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y - 1][X - 1].getPosition()));
        game.getBoard()[Y - 1][X].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y - 1][X].getPosition()));
        game.getBoard()[Y - 1][X + 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y - 1][X + 1].getPosition()));
        game.getBoard()[Y][X - 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y][X - 1].getPosition()));
        game.getBoard()[Y][X + 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y][X + 1].getPosition()));
        game.getBoard()[Y + 1][X - 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y + 1][X - 1].getPosition()));
        game.getBoard()[Y + 1][X].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y + 1][X].getPosition()));
        game.getBoard()[Y + 1][X + 1].setPiece(new Pawn(Color.BLACK, game.getBoard()[Y + 1][X + 1].getPosition()));
        List<Vector> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());

        assertTrue(legalMoves.size() == 0);
    }
}
