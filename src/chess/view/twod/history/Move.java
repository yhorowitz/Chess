package chess.view.twod.history;

import javafx.beans.property.SimpleStringProperty;

public class Move {

    private final SimpleStringProperty moveNumber;
    private final SimpleStringProperty algebraicNotation;
    private final SimpleStringProperty details;

    public Move(String moveNumber, String algebraicNotation, String details) {
        this.moveNumber = new SimpleStringProperty(moveNumber);
        this.algebraicNotation = new SimpleStringProperty(algebraicNotation);
        this.details = new SimpleStringProperty(details);
    }

    public String getMoveNumber() {
        return moveNumber.get();
    }

    public SimpleStringProperty moveNumberProperty() {
        return moveNumber;
    }

    public void setMoveNumber(String moveNumber) {
        this.moveNumber.set(moveNumber);
    }

    public String getAlgebraicNotation() {
        return algebraicNotation.get();
    }

    public SimpleStringProperty algebraicNotationProperty() {
        return algebraicNotation;
    }

    public void setAlgebraicNotation(String algebraicNotation) {
        this.algebraicNotation.set(algebraicNotation);
    }

    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }
}