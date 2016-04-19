package chess.model;

import chess.model.pieces.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds a set of pieces for a single player
 */
public class PlayerPieceSet {

    private PieceColor pieceColor;
    private List<ChessPiece> pieces = new ArrayList<>();

    public PlayerPieceSet(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    /**
     * Adds a piece to the set of pieces
     *
     * @param pieceType The class of the piece being added
     * @param position The position the piece should be added to
     *
     * @return The piece that was added
     */
    public <T extends ChessPiece> T addPiece(Class<T> pieceType, Position position) {

        if (positionIsOccupied(position))
            throw new RuntimeException("That position already has a piece");

        T piece = null;

        try {
            Constructor constructor =  pieceType.getConstructor(new Class[] { PieceColor.class });
            piece = (T) constructor.newInstance(pieceColor);
            piece.setPieceColor(pieceColor);
            piece.setPosition(position);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        this.pieces.add(piece);

        return piece;
    }

    public <T extends ChessPiece> List<ChessPiece> getPiecesOfType(Class<T> pieceType) {

        List<ChessPiece> selectedPieces = new ArrayList<>();

        for (ChessPiece piece : pieces) {
            if (piece.getClass() == pieceType)
                selectedPieces.add(piece);
        }

        return selectedPieces;
    }

    public List<ChessPiece> getAllPieces() {
        return this.pieces;
    }

    private boolean positionIsOccupied(Position position) {
        for (ChessPiece piece : pieces) {
            if (piece.getPosition().equals(position))
                return true;
        }

        return false;
    }

}
