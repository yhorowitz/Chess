package chess.view.twod;

import chess.model.PieceColor;
import chess.model.PlayerPieceSet;
import chess.model.pieces.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;

/**
 * Shows which pieces have been captured for each player
 */
public class Graveyard extends GridPane{

    private PieceColor color;
    private Label queen = new Label();
    private Label[] rooks = new Label[2];
    private Label[] bishops = new Label[2];
    private Label[] knights = new Label[2];
    private Label[] pawns = new Label[8];

    private boolean queenIsCaptured = false;
    private int numOfRooksCaptured = 0;
    private int numOfBishopsCaptured = 0;
    private int numOfKnightsCaptured = 0;
    private int numOfPawnsCaptured = 0;

    public Graveyard(PieceColor color) {
        this.color = color;

        queen.getStyleClass().add("graveyardPiece");

        for (int i = 0; i < rooks.length; i++) {
            rooks[i] = new Label();
            bishops[i] = new Label();
            knights[i] = new Label();

            rooks[i].getStyleClass().add("graveyardPiece");
            bishops[i].getStyleClass().add("graveyardPiece");
            knights[i].getStyleClass().add("graveyardPiece");
        }
        for (int i = 0; i < pawns.length; i++) {
            pawns[i] = new Label();
            pawns[i].getStyleClass().add("graveyardPiece");
        }

        this.add(queen, 0, 0);
        this.add(rooks[0], 1, 0);
        this.add(rooks[1], 2, 0);
        this.add(bishops[0], 0, 1);
        this.add(bishops[1], 1, 1);
        this.add(knights[0], 2, 1);
        this.add(knights[1], 0, 2);
        this.add(pawns[0], 1, 2);
        this.add(pawns[1], 2, 2);
        this.add(pawns[2], 0, 3);
        this.add(pawns[3], 1, 3);
        this.add(pawns[4], 2, 3);
        this.add(pawns[5], 0, 4);
        this.add(pawns[6], 1, 4);
        this.add(pawns[7], 2, 4);

        update(new PlayerPieceSet(color));
    }

    public void update(PlayerPieceSet pieceSet) {
        updateNumOfEachPieceCaptured(pieceSet.getAllCapturedPieces());

        updateQueen();
        updateRooks();
        updateBishops();
        updateKnights();
        updatePawns();
    }

    private void updateQueen() {
        Image image;

        if (queenIsCaptured)
            image = getFilledImageForPieceType(Queen.class);
        else
            image = getEmptyImageForPieceType(Queen.class);

        queen.setGraphic(new ImageView(image));
    }

    private void updateRooks() {
        Image image;
        for (int i = 0; i < rooks.length; i++){
            if (numOfRooksCaptured > i)
                image = getFilledImageForPieceType(Rook.class);
            else
                image = getEmptyImageForPieceType(Rook.class);

            rooks[i].setGraphic(new ImageView(image));
        }
    }

    private void updateBishops() {
        Image image;
        for (int i = 0; i < bishops.length; i++){
            if (numOfBishopsCaptured > i)
                image = getFilledImageForPieceType(Bishop.class);
            else
                image = getEmptyImageForPieceType(Bishop.class);

            bishops[i].setGraphic(new ImageView(image));
        }
    }

    private void updateKnights() {
        Image image;
        for (int i = 0; i < knights.length; i++){
            if (numOfKnightsCaptured > i)
                image = getFilledImageForPieceType(Knight.class);
            else
                image = getEmptyImageForPieceType(Knight.class);

            knights[i].setGraphic(new ImageView(image));
        }
    }

    private void updatePawns() {
        Image image;
        for (int i = 0; i < pawns.length; i++){
            if (numOfPawnsCaptured > i)
                image = getFilledImageForPieceType(Pawn.class);
            else
                image = getEmptyImageForPieceType(Pawn.class);

            pawns[i].setGraphic(new ImageView(image));
        }
    }

    private <T extends ChessPiece> Image getFilledImageForPieceType(Class<T> pieceType) {
        String color = this.color.toString().toLowerCase();
        String type = pieceType.getSimpleName().toLowerCase();
        Image image = new Image(getClass().getResourceAsStream("/images/pieces/captured/" + color + "/" + type + ".png"));
        return image;
    }

    private <T extends ChessPiece> Image getEmptyImageForPieceType(Class<T> pieceType) {


        String color = this.color.toString().toLowerCase();
        String type = pieceType.getSimpleName().toLowerCase();
        //URL location = Graveyard.class.getProtectionDomain().getCodeSource().getLocation();
        //System.out.println(location.getFile());
        //System.out.println(getClass().getResourceAsStream("../../chess/res/images/pieces/empty/" + color + "/" + type + ".png"));

        Image image = new Image(getClass().getResourceAsStream("/images/pieces/empty/" + color + "/" + type + ".png"));
        return image;
    }

    private void resetCapturedPiecesCounters() {
        queenIsCaptured = false;
        numOfRooksCaptured = 0;
        numOfBishopsCaptured = 0;
        numOfKnightsCaptured = 0;
        numOfPawnsCaptured = 0;
    }

    private void updateNumOfEachPieceCaptured(List<ChessPiece> capturedPieces) {
        resetCapturedPiecesCounters();
        for (ChessPiece piece: capturedPieces) {
            if (piece instanceof Queen) {
                queenIsCaptured = true;
            }
            else if (piece instanceof Rook) {
                numOfRooksCaptured++;
            }
            else if (piece instanceof Bishop) {
                numOfBishopsCaptured++;
            }
            else if (piece instanceof Knight) {
                numOfKnightsCaptured++;
            }
            else if (piece instanceof Pawn) {
                numOfPawnsCaptured++;
            }
        }
    }

}
