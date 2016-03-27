package chess.view.twod;

import chess.model.GridSpace;
import chess.model.pieces.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import chess.model.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardPosition extends Button {

    private GridSpace info;

    BoardPosition(Vector vector) {
        super();
        this.info = new GridSpace(vector);
        this.getStyleClass().add("boardSpace");
    }

    public void setPiece(ChessPiece piece) {
        this.info.setPiece(piece);
        this.setPieceImage();
    }

    public Vector getPosition() {
        return this.info.getPosition();
    }

    public void highlight(boolean highlight) {
        this.info.highlight(highlight);

        if (highlight)
            this.getStyleClass().add("highlight");
        else
            this.getStyleClass().remove("highlight");
    }

    public void select(boolean select) {
        this.info.select(select);

        if (select) {
            this.getStyleClass().remove("highlight");
            this.getStyleClass().add("selected");
        }
        else{
            this.getStyleClass().remove("selected");
        }
    }

    public boolean isSelected() {
        return this.info.isSelected();
    }

    public void setPieceImage() {
        String color = this.info.getPiece().getColor().toString().toLowerCase();
        String pieceType = this.info.getPiece().getClass().getSimpleName().toLowerCase();

        //System.out.println((getClass().getResource("../../..")));
        Image image = new Image(getClass().getResourceAsStream("../../../res/images/pieces/" +
                                color + "/" +
                                pieceType + ".png"));

        this.setGraphic(new ImageView(image));
    }

    public void addEventListener(EventHandler handler) {
        this.setOnAction(handler);
    }

}
