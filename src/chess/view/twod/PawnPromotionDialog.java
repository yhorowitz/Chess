package chess.view.twod;


import chess.model.PieceColor;
import chess.model.pieces.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;

/**
 * When a pawn reaches the end of the board this dialog allows them
 * to choose what piece to turn into
 */
public class PawnPromotionDialog extends Dialog<ChessPiece> {

    Button queen = new Button();
    Button rook = new Button();
    Button bishop = new Button();
    Button knight = new Button();

    PieceColor pieceColor;
    ChessPiece result = null;

    public PawnPromotionDialog(PieceColor pieceColor) {
        initModality(Modality.APPLICATION_MODAL);

        this.pieceColor = pieceColor;
        result = new Queen(pieceColor);

        queen.setMinWidth(150);
        queen.setMinHeight(150);
        rook.setMinWidth(150);
        rook.setMinHeight(150);
        bishop.setMinWidth(150);
        bishop.setMinHeight(150);
        knight.setMinWidth(150);
        knight.setMinHeight(150);

        String color = pieceColor.toString().toLowerCase();
        queen.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../res/images/pieces/" + color + "/queen.png"))));
        rook.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../res/images/pieces/" + color + "/rook.png"))));
        bishop.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../res/images/pieces/" + color + "/bishop.png"))));
        knight.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../res/images/pieces/" + color + "/knight.png"))));

        HBox buttonContainer = new HBox();
        buttonContainer.setPadding(new Insets(5, 5, 5, 5));
        buttonContainer.getChildren().addAll(queen, rook, bishop, knight);

        addHandlersToButtons();

        this.getDialogPane().setContent(buttonContainer);

        this.setTitle("Pawn Promotion");
        this.setHeaderText("What would you like to promote your pawn into? ");

        this.setResizable(false);
        this.showAndWait();

    }

    private void addHandlersToButtons() {
        addHandlerToQueen();
        addHandlerToRook();
        addHandlerToBishop();
        addHandlerToKnight();
    }

    private void addHandlerToQueen() {
        queen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PawnPromotionDialog.this.setResult(new Queen(pieceColor));
                PawnPromotionDialog.this.close();
            }
        });
    }

    private void addHandlerToRook() {
        rook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PawnPromotionDialog.this.setResult(new Rook(pieceColor));
                PawnPromotionDialog.this.close();
            }
        });
    }

    private void addHandlerToBishop() {
        bishop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PawnPromotionDialog.this.setResult(new Bishop(pieceColor));
                PawnPromotionDialog.this.close();
            }
        });
    }

    private void addHandlerToKnight() {
        knight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PawnPromotionDialog.this.setResult(new Knight(pieceColor));
                PawnPromotionDialog.this.close();
            }
        });
    }



}
