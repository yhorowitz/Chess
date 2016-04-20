package chess.view.twod.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


/**
 * Created by Yitzi on 4/20/2016.
 */
public class GameHistory extends VBox {

    private TableView gameHistoryTable = new TableView();

    private final Label headerLabel = new Label("Game History");
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
        turnNumberCol.setMinWidth(50);
        turnNumberCol.setMaxWidth(50);
        moveCol.setMinWidth(50);
        moveCol.setMaxWidth(50);
        detailedDescriptionCol.setMinWidth(529);

        //set column data sources
        turnNumberCol.setCellValueFactory(new PropertyValueFactory<Move, String>("moveNumber"));
        moveCol.setCellValueFactory(new PropertyValueFactory<Move, String>("algebraicNotation"));
        detailedDescriptionCol.setCellValueFactory(new PropertyValueFactory<Move, String>("details"));

        gameHistoryTable.setMaxHeight(150);
        gameHistoryTable.setMaxWidth(630);
        gameHistoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gameHistoryTable.setEditable(false);
        gameHistoryTable.setPlaceholder(new Label("No moves have been made yet"));
        gameHistoryTable.getColumns().addAll(turnNumberCol, moveCol, detailedDescriptionCol);
        gameHistoryTable.setItems(moveHistory);

        //this.setSpacing(5);
        //this.setPadding(new Insets(10, 0, 0, 10));
        this.getChildren().addAll(headerLabel, gameHistoryTable);

    }

    public void addMove(int moveNumber, chess.model.Move move) {
        moveHistory.add(new Move(moveNumber + "", move.getAlgebraicNotation(), move.getDetailedDescription()));
        gameHistoryTable.scrollTo(moveHistory.size() - 1);

    }

}
