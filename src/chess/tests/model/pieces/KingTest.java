//package chess.tests.model.pieces;
//
//
//import chess.model.ChessGame;
//import chess.model.PieceColor;
//import chess.model.pieces.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import chess.model.Position;
//
//import static org.junit.Assert.*;
//
///**
// * Current tests as of 3/27/16:
// *      Each corner
// *      Being on the different edges
// *      Being in the center with no pieces around it
// *      Being in the center with a few piece around it
// *      Being in the center with it blocked from every piece
// *
// * Still needs testing for:
// *      Castling (ensure that it can't if blocked or one of the pieces already moved)
// *      Moving into a check
// *      Moving through a check (when castling)
// *
// */
//public class KingTest {
//
//    private ChessGame game;
//
//    @Before
//    public void setUp() {
//        //set up a new empty chess game model for each test
//        game = new ChessGame(false);
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInTheTopLeftCorner() {
//        final int X = 0;
//        final int Y = 0;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(0, 1));
//        whatResultsShouldBe.add(new Position(1, 0));
//        whatResultsShouldBe.add(new Position(1, 1));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInTheTopRightCorner() {
//        final int X = 7;
//        final int Y = 0;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(7, 1));
//        whatResultsShouldBe.add(new Position(6, 0));
//        whatResultsShouldBe.add(new Position(6, 1));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInTheBottomLeftCorner() {
//        final int X = 0;
//        final int Y = 7;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(0, 6));
//        whatResultsShouldBe.add(new Position(1, 6));
//        whatResultsShouldBe.add(new Position(1, 7));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInTheBottomRightCorner() {
//        final int X = 7;
//        final int Y = 7;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(6, 6));
//        whatResultsShouldBe.add(new Position(6, 7));
//        whatResultsShouldBe.add(new Position(7, 6));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsOnTheTopEdge() {
//        final int X = 4;
//        final int Y = 0;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(0, 3));
//        whatResultsShouldBe.add(new Position(0, 5));
//        whatResultsShouldBe.add(new Position(1, 3));
//        whatResultsShouldBe.add(new Position(1, 4));
//        whatResultsShouldBe.add(new Position(1, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsOnTheBottomEdge() {
//        final int X = 4;
//        final int Y = 7;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(7, 3));
//        whatResultsShouldBe.add(new Position(7, 5));
//        whatResultsShouldBe.add(new Position(6, 3));
//        whatResultsShouldBe.add(new Position(6, 4));
//        whatResultsShouldBe.add(new Position(6, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsOnTheRightEdge() {
//        final int X = 7;
//        final int Y = 4;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(7, 3));
//        whatResultsShouldBe.add(new Position(7, 5));
//        whatResultsShouldBe.add(new Position(6, 3));
//        whatResultsShouldBe.add(new Position(6, 4));
//        whatResultsShouldBe.add(new Position(6, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsOnTheLeftEdge() {
//        final int X = 0;
//        final int Y = 4;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(0, 3));
//        whatResultsShouldBe.add(new Position(0, 5));
//        whatResultsShouldBe.add(new Position(1, 3));
//        whatResultsShouldBe.add(new Position(1, 4));
//        whatResultsShouldBe.add(new Position(1, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInMiddle() {
//        final int X = 4;
//        final int Y = 4;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(3, 3));
//        whatResultsShouldBe.add(new Position(3, 4));
//        whatResultsShouldBe.add(new Position(3, 5));
//        whatResultsShouldBe.add(new Position(4, 3));
//        whatResultsShouldBe.add(new Position(4, 5));
//        whatResultsShouldBe.add(new Position(5, 3));
//        whatResultsShouldBe.add(new Position(5, 4));
//        whatResultsShouldBe.add(new Position(5, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//    @Test
//    public void LegalMovesWhenKingIsInMiddleAndHasPiecesInItsPath() {
//        final int X = 4;
//        final int Y = 4;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        game.getBoard()[Y - 1][X + 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y - 1][X + 1].getPosition()));
//        game.getBoard()[Y][X - 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y][X - 1].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        List<Position> whatResultsShouldBe = new ArrayList<>();
//        whatResultsShouldBe.add(new Position(3, 3));
//        whatResultsShouldBe.add(new Position(3, 5));
//        whatResultsShouldBe.add(new Position(4, 3));
//        whatResultsShouldBe.add(new Position(4, 5));
//        whatResultsShouldBe.add(new Position(5, 4));
//        whatResultsShouldBe.add(new Position(5, 5));
//
//        assertTrue(legalMoves.size() == whatResultsShouldBe.size());
//        assertTrue(whatResultsShouldBe.containsAll(legalMoves) && legalMoves.containsAll(whatResultsShouldBe));
//    }
//
//
//
//    @Test
//    public void LegalMovesWhenKingIsInMiddleAndIsCompletelySurrounded() {
//        final int X = 4;
//        final int Y = 4;
//
//        game.getBoard()[Y][X].setPiece(new King(PieceColor.BLACK, game.getBoard()[Y][X].getPosition()));
//        game.getBoard()[Y - 1][X - 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y - 1][X - 1].getPosition()));
//        game.getBoard()[Y - 1][X].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y - 1][X].getPosition()));
//        game.getBoard()[Y - 1][X + 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y - 1][X + 1].getPosition()));
//        game.getBoard()[Y][X - 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y][X - 1].getPosition()));
//        game.getBoard()[Y][X + 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y][X + 1].getPosition()));
//        game.getBoard()[Y + 1][X - 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y + 1][X - 1].getPosition()));
//        game.getBoard()[Y + 1][X].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y + 1][X].getPosition()));
//        game.getBoard()[Y + 1][X + 1].setPiece(new Pawn(PieceColor.BLACK, game.getBoard()[Y + 1][X + 1].getPosition()));
//        List<Position> legalMoves = game.getLegalMoves(game.getBoard()[Y][X].getPiece());
//
//        assertTrue(legalMoves.size() == 0);
//    }
//}
