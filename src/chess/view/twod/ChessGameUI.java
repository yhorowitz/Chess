package chess.view.twod;

import chess.model.ChessGame;
import chess.model.Move;
import chess.model.PieceColor;
import chess.model.Position;
import chess.view.twod.history.GameHistory;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Container class for all view objects relating to the board
 */
public class ChessGameUI extends BorderPane {

    private BorderPane gamePane = new BorderPane(); //holds actual game info (ie board, history etc)

    private VBox graveyardPane = new VBox();
    Graveyard blackGraveyard = new Graveyard(PieceColor.BLACK);
    Graveyard whiteGraveyard = new Graveyard(PieceColor.WHITE);

    private Board gameBoard = new Board();
    private GameHistory gameHistory = new GameHistory();

    //menu bar items
    private MenuBar menuBar = new MenuBar();
    private Menu systemMenu = new Menu("System");
    private Menu optionsMenu = new Menu("Options");
    private MenuItem exitItem = new MenuItem("Exit");
    private MenuItem toggleGameHistoryVisibility= new MenuItem("Hide Game History");
    private MenuItem toggleGraveyardVisibility= new MenuItem("Hide Graveyards");
    private MenuItem invertBoard= new MenuItem("Invert Board");

    public ChessGameUI() {
        gameBoard.getStyleClass().add("gameBoard");
        gameHistory.getStyleClass().add("historyPanel");
        graveyardPane.getStyleClass().add("graveyard");

        graveyardPane.setSpacing(5);
        Label decorativeSwirl = new Label();
        Image image = new Image(getClass().getResourceAsStream("/images/decorative_swirl.png"));
        decorativeSwirl.setGraphic(new ImageView(image));
        graveyardPane.getChildren().add(blackGraveyard);
        graveyardPane.getChildren().add(decorativeSwirl);
        graveyardPane.getChildren().add(whiteGraveyard);

        gamePane.setLeft(graveyardPane);
        gamePane.setCenter(gameBoard);
        gamePane.setBottom(gameHistory);

        //set up menu
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        toggleGameHistoryVisibility.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));
        toggleGraveyardVisibility.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));
        invertBoard.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
        systemMenu.getItems().add(exitItem);
        optionsMenu.getItems().add(toggleGameHistoryVisibility);
        optionsMenu.getItems().add(toggleGraveyardVisibility);
        optionsMenu.getItems().add(invertBoard);
        menuBar.getMenus().add(systemMenu);
        menuBar.getMenus().add(optionsMenu);

        this.setTop(menuBar);
        this.setCenter(gamePane);
        showGameHistory();
    }

    public void resetGameHistoryWidth() {
        int width = graveyardIsVisible() ? 820 : 629;
        gameHistory.changeTableWidth(width);
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

    public void showGraveyards() {
        gamePane.setLeft(graveyardPane);
        toggleGraveyardVisibility.setText("Hide Graveyards");
    }

    public void hideGraveyards() {
        gamePane.setLeft(null);
        toggleGraveyardVisibility.setText("Show Graveyards");
    }

    public void invertBoard(ChessGame game) {
        this.gameBoard.invertBoard(game);
    }

    public MenuItem getExitMenuItem() {
        return this.exitItem;
    }

    public MenuItem getToggleGameHistoryItem() {
        return toggleGameHistoryVisibility;
    }

    public MenuItem getToggleGraveyardItem() {
        return toggleGraveyardVisibility;
    }

    public MenuItem getInvertBoardItem() {
        return invertBoard;
    }

    public boolean gameHistoryIsVisible() {
        return gamePane.getBottom() != null;
    }

    public boolean graveyardIsVisible() {
        return gamePane.getLeft() != null;
    }

    public Graveyard getBlackGraveyard() {
        return this.blackGraveyard;
    }

    public Graveyard getWhiteGraveyard() {
        return this.whiteGraveyard;
    }
}
