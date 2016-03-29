package chess.view.twod;

import chess.model.BoardSpace;
import chess.model.Color;
import chess.model.pieces.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import chess.model.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardPosition extends Button {

    private Vector position;
    private boolean highlight = false;
    private boolean selected = false;

    BoardPosition(Vector vector) {
        super();
        this.setPosition(vector);
        this.getStyleClass().add("boardSpace");
    }

    private void setPosition(Vector vector) {
        this.position = vector;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void highlight(boolean highlight) {
        this.highlight = highlight;

        if (highlight)
            this.getStyleClass().add("highlight");
        else
            this.getStyleClass().remove("highlight");
    }

    public void select(boolean select) {
        this.selected = select;

        if (select) {
            this.getStyleClass().remove("highlight");
            this.getStyleClass().add("selected");
        }
        else{
            this.getStyleClass().remove("selected");
        }
    }

    public boolean isSelected() {
        return this.selected;
    }

    private void updateImage(ChessPiece piece) {
        if (piece == null) {
            this.setGraphic(null);
        }
        else {
            String pieceColor = piece.getColor().toString().toLowerCase();
            String pieceType = piece.getClass().getSimpleName().toLowerCase();

            Image image = new Image(getClass().getResourceAsStream("../../res/images/pieces/" +
                    pieceColor + "/" +
                    pieceType + ".png"));

            this.setGraphic(new ImageView(image));
        }

    }

    public void update(BoardSpace space) {
        updateImage(space.getPiece());
    }

    public void addEventListener(EventHandler handler) {
        this.setOnAction(handler);
    }

}
