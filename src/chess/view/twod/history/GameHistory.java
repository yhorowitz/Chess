package chess.view.twod.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * Displays every move taken in the game
 */
public class GameHistory extends VBox {

    private TableView gameHistoryTable = new TableView();

    private TableColumn turnNumberCol = new TableColumn("Turn");
    private TableColumn moveCol = new TableColumn("Move");
    private TableColumn detailedDescriptionCol = new TableColumn("Details");

    private final ObservableList<Move> moveHistory = FXCollections.observableArrayList();

    public GameHistory() {

        //set columns to not be sortable
        turnNumberCol.setSortable(false);
        moveCol.setSortable(false);
        detailedDescriptionCol.setSortable(false);

        //set column width
        changeTableWidth(700);

        //set column data sources
        turnNumberCol.setCellValueFactory(new PropertyValueFactory<Move, String>("moveNumber"));
        moveCol.setCellValueFactory(new PropertyValueFactory<Move, String>("algebraicNotation"));
        detailedDescriptionCol.setCellValueFactory(new PropertyValueFactory<Move, String>("details"));

        gameHistoryTable.setMaxHeight(150);
        gameHistoryTable.setMaxWidth(820);
        gameHistoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gameHistoryTable.setEditable(false);
        gameHistoryTable.setPlaceholder(new Label("No moves have been made yet"));
        gameHistoryTable.getColumns().addAll(turnNumberCol, moveCol, detailedDescriptionCol);
        gameHistoryTable.setItems(moveHistory);

        this.getChildren().addAll(gameHistoryTable);

    }

    public void changeTableWidth(int width) {
        gameHistoryTable.setMinWidth(width);
        gameHistoryTable.setMaxWidth(width);

        //change width of columns to match new table size
        turnNumberCol.setMinWidth(width * .1);
        turnNumberCol.setMaxWidth(width * .1);
        moveCol.setMinWidth(width * .15);
        moveCol.setMaxWidth(width * .15);
        detailedDescriptionCol.setMinWidth(width * .75);
        detailedDescriptionCol.setMaxWidth(width * .75);
    }

    public void addMove(int moveNumber, chess.model.Move move) {
        moveHistory.add(new Move(moveNumber + "", move.getAlgebraicNotation(), move.getDetailedDescription()));
        gameHistoryTable.scrollTo(moveHistory.size() - 1);
    }

}
