package chess.controller.twod;


import chess.model.ChessGame;
import chess.model.GridSpace;
import chess.model.Vector;
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

        for (int i = 0; i < gameUI.getGrid().length; i++) {
            for (int j = 0; j < gameUI.getGrid().length; j++) {
                gameUI.getGrid()[i][j].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        BoardPosition gridSpace = (BoardPosition) event.getSource();

                        //todo highlight all squares that are legal moves of the piece
                        if (gridSpace.isSelected()) {
                            deselectPositionAtVector(gridSpace.getPosition());
                            removeHighlightFromAllBoardPositions();
                        }
                        else {
                            deselectAllBoardPositions();
                            removeHighlightFromAllBoardPositions();
                            selectPositionAtVector(gridSpace.getPosition());
                        }
                    }
                });
            }
        }
    }

    private void selectPositionAtVector(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        //update view
        gameUI.getGrid()[row][column].select(true);

        //update model
        game.getBoard()[row][column].select(true);
    }

    private void deselectPositionAtVector(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        //update view
        gameUI.getGrid()[row][column].select(false);

        //update model
        game.getBoard()[row][column].select(false);
    }

    private void highlightPositionAtVector(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        //update view
        gameUI.getGrid()[row][column].highlight(true);

        //update model
        game.getBoard()[row][column].highlight(true);
    }

    private void removeHighlightFromPositionAtVector(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        //update view
        gameUI.getGrid()[row][column].highlight(false);

        //update model
        game.getBoard()[row][column].highlight(false);
    }

    private void deselectAllBoardPositions() {
        //update view
        for (int i = 0; i < gameUI.getGrid().length; i++) {
            for (BoardPosition position : gameUI.getGrid()[i]) {
                position.select(false);
            }
        }

        //update model
        for (int i = 0; i < game.getBoard().length; i++) {
            for (GridSpace position : game.getBoard()[i]) {
                position.select(false);
            }
        }
    }

    private void removeHighlightFromAllBoardPositions() {
        //update view
        for (int i = 0; i < gameUI.getGrid().length; i++) {
            for (BoardPosition position : gameUI.getGrid()[i]) {
                position.highlight(false);
            }
        }

        //update model
        for (int i = 0; i < game.getBoard().length; i++) {
            for (GridSpace position : game.getBoard()[i]) {
                position.highlight(false);
            }
        }
    }



}
