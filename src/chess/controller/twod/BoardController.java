package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board;
import chess.view.twod.BoardPosition;
import javafx.event.Event;
import javafx.event.EventHandler;

public class BoardController {

    private Board gameUI;
    private ChessGame game;

    public BoardController(Board gameUI, ChessGame game) {
        this.gameUI = gameUI;
        this.game = game;

        for (int i = 0; i < gameUI.getGridGUI().length; i++) {
            for (int j = 0; j < gameUI.getGridGUI().length; j++) {
                gameUI.getGridGUI()[i][j].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        BoardPosition gridSpace = (BoardPosition) event.getSource();
                        if (gridSpace.isSelected()) {
                            gridSpace.select(false);
                        }
                        else {
                            gridSpace.select(true);
                        }
                    }
                });
            }
        }
    }



}
