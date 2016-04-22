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
    private List<ChessPiece> alivePieces = new ArrayList<>();
    private List<ChessPiece> capturedPieces = new ArrayList<>();

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

        this.alivePieces.add(piece);

        return piece;
    }

    public void addPiece(ChessPiece piece) {
        if (piece.getPosition() == null)
            throw new IllegalArgumentException("Piece must already have its position set");
        else
            this.alivePieces.add(piece);
    }

    public <T extends ChessPiece> List<ChessPiece> getCapturedPiecesOfType(Class<T> pieceType) {

        List<ChessPiece> selectedPieces = new ArrayList<>();

        for (ChessPiece piece : capturedPieces) {
            if (piece.getClass() == pieceType)
                selectedPieces.add(piece);
        }

        return selectedPieces;
    }

    public <T extends ChessPiece> List<ChessPiece> getAlivePiecesOfType(Class<T> pieceType) {

        List<ChessPiece> selectedPieces = new ArrayList<>();

        for (ChessPiece piece : alivePieces) {
            if (piece.getClass() == pieceType)
                selectedPieces.add(piece);
        }

        return selectedPieces;
    }

    public <T extends ChessPiece> List<ChessPiece> getAllPiecesOfType(Class<T> pieceType) {

        List<ChessPiece> selectedPieces = new ArrayList<>();

        for (ChessPiece piece : alivePieces) {
            if (piece.getClass() == pieceType)
                selectedPieces.add(piece);
        }

        for (ChessPiece piece : capturedPieces) {
            if (piece.getClass() == pieceType)
                selectedPieces.add(piece);
        }

        return selectedPieces;
    }

    public List<ChessPiece> getAllPieces() {
        List<ChessPiece> allPieces = new ArrayList<>();
        allPieces.addAll(alivePieces);
        allPieces.addAll(capturedPieces);

        return allPieces;
    }

    public List<ChessPiece> getAllCapturedPieces() {
        return this.capturedPieces;
    }

    public List<ChessPiece> getAllAlivePieces() {
        return this.alivePieces;
    }

    private boolean positionIsOccupied(Position position) {
        for (ChessPiece piece : alivePieces) {
            if (piece.getPosition().equals(position))
                return true;
        }

        return false;
    }

    public ChessPiece getPieceAtPosition(Position position) {
        for (ChessPiece piece : alivePieces) {
            if (piece.getPosition().equals(position))
                return piece;
        }

        return null;
    }

    public void capture(ChessPiece piece) {
        boolean pieceRemoved = alivePieces.remove(piece);
        if(!pieceRemoved)
            throw new IllegalArgumentException("That piece is not in this players set");
        else
            capturedPieces.add(piece);
    }

}
