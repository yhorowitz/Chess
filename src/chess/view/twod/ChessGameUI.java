package chess.view.twod;

import chess.controller.twod.Launcher;
import chess.model.ChessGame;
import chess.model.Move;
import chess.model.Position;
import chess.view.twod.history.GameHistory;
import javafx.geometry.Insets;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;

/**
 * Container class for all view objects relating to the board
 */
public class ChessGameUI extends BorderPane {

    private BorderPane gamePane = new BorderPane(); //holds actual game info (ie board, history etc)

    private Board gameBoard = new Board();
    private GameHistory gameHistory = new GameHistory();

    //menu bar items
    private MenuBar menuBar = new MenuBar();
    private Menu systemMenu = new Menu("System");
    private Menu optionsMenu = new Menu("Options");
    private MenuItem exitItem = new MenuItem("Exit");
    private MenuItem toggleGameHistoryVisibility= new MenuItem("Hide Game History");

    public ChessGameUI() {
        gameBoard.getStyleClass().add("gameBoard");
        gameHistory.getStyleClass().add("historyPanel");

        gamePane.setCenter(gameBoard);
        gamePane.setBottom(gameHistory);

        //set up menu
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        toggleGameHistoryVisibility.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        systemMenu.getItems().add(exitItem);
        optionsMenu.getItems().add(toggleGameHistoryVisibility);
        menuBar.getMenus().add(systemMenu);
        menuBar.getMenus().add(optionsMenu);

        this.setTop(menuBar);
        this.setCenter(gamePane);
        showGameHistory();
    }

    public void updateBoard(ChessGame game) {
        gameBoard.update(game);
    }

    public BoardPosition[][] getGrid() {
        return gameBoard.getGrid();
    }

    public BoardPosition getBoardPosition(Position position) {
        return  gameBoard.getBoardPosition(position);
    }

    public void addMove(int moveNumber, Move move) {
        gameHistory.addMove(moveNumber, move);
    }

    public void removeAllBoardSelections() {
        for (int i = 0; i < gameBoard.getGrid().length; i++) {
            for (BoardPosition position : gameBoard.getGrid()[i]) {
                position.select(false);
            }
        }
    }

    public void removeAllBoardHighlights() {
        for (int i = 0; i < gameBoard.getGrid().length; i++) {
            for (BoardPosition position : gameBoard.getGrid()[i]) {
                position.highlight(false);
            }
        }
    }

    public void showGameHistory() {
        gamePane.setBottom(gameHistory);
        toggleGameHistoryVisibility.setText("Hide Game History");
    }

    public void hideGameHistory() {
        gamePane.setBottom(null);
        toggleGameHistoryVisibility.setText("Show Game History");
    }

    public MenuItem getExitMenuItem() {
        return this.exitItem;
    }

    public MenuItem getToggleGameHistoryItem() {
        return toggleGameHistoryVisibility;
    }

    public boolean gameHistoryIsVisible() {
        return gamePane.getBottom() != null;
    }
}
