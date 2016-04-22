package chess.tests.model;

import chess.model.PieceColor;
import chess.model.PlayerPieceSet;
import chess.model.Position;
import chess.model.pieces.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.*;

/**
 * Tests for the PlayerPieceSet class
 */
public class PlayerPieceSetTest {

    PlayerPieceSet set;

    @Before
    public void setUp() {
        set = new PlayerPieceSet(PieceColor.WHITE);
    }
    @Test
    public void addingAPieceThrowsNoErrors() {
        King king = set.addPiece(King.class, new Position(1, 1));
        Pawn pawn = set.addPiece(Pawn.class, new Position(1, 2));
        Rook rook = set.addPiece(Rook.class, new Position(1, 3));
    }

    @Test
    public void addingAPieceReturnsObjectOfTheRightClass() {
        King king = set.addPiece(King.class, new Position(1, 1));
        Pawn pawn = set.addPiece(Pawn.class, new Position(1, 2));

        assertEquals(king.getClass(), King.class);
        assertEquals(pawn.getClass(), Pawn.class);
    }

    @Test (expected = RuntimeException.class)
    public void settingAPieceInAnAlreadyOccupiedPosition() {
        set.addPiece(King.class, new Position(1, 1));
        set.addPiece(Pawn.class, new Position(1, 1));
    }

    @Test
    public void getPieceOfTypeOnlyGetsPieceOfThatType() {
        set.addPiece(King.class, new Position(1, 1));
        set.addPiece(Pawn.class, new Position(1, 2));
        set.addPiece(Pawn.class, new Position(1, 3));
        set.addPiece(Pawn.class, new Position(1, 4));

        List<ChessPiece> pawns = set.getAllPiecesOfType(Pawn.class);
        for (ChessPiece piece : pawns)
            assertEquals(piece.getClass(), Pawn.class);
    }

    @Test
    public void getPiecesReturnsCorrectNumberOfPieces() {
        set.addPiece(King.class, new Position(1, 1));
        set.addPiece(Pawn.class, new Position(1, 2));
        set.addPiece(Pawn.class, new Position(1, 3));
        set.addPiece(Pawn.class, new Position(1, 4));
        set.addPiece(Queen.class, new Position(1, 5));

        List<ChessPiece> pawns = set.getAllPiecesOfType(Pawn.class);
        List<ChessPiece> queens = set.getAllPiecesOfType(Queen.class);
        List<ChessPiece> allPieces = set.getAllAlivePieces();

        assertEquals(pawns.size(), 3);
        assertEquals(queens.size(), 1);
        assertEquals(allPieces.size(), 5);

    }
}
