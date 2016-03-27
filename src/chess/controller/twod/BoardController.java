package chess.controller.twod;


import chess.model.ChessGame;
import chess.model.BoardSpace;
import chess.model.Vector;
import chess.model.pieces.ChessPiece;
import chess.view.twod.Board;
import chess.view.twod.BoardPosition;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.List;

public class BoardController {

    private Board gameUI;
    private ChessGame game;

    public BoardController(Board gameUI, ChessGame game) {
        this.gameUI = gameUI;
        this.game = game;

        for (int row = 0; row < gameUI.getGrid().length; row++) {
            for (int col = 0; col < gameUI.getGrid().length; col++) {
                gameUI.getGrid()[row][col].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        BoardPosition gridSpace = (BoardPosition) event.getSource();
                        Vector position = gridSpace.getPosition();

                        System.out.println("Current position is " + position.toString());
                        if (gridSpace.isSelected()) {
                            deselectPositionAtVector(gridSpace.getPosition());
                            removeHighlightFromAllBoardPositions();
                        }
                        else {
                            deselectAllBoardPositions();
                            removeHighlightFromAllBoardPositions();
                            selectPositionAtVector(gridSpace.getPosition());

                            //if space is not empty highlight legal moves
                            if (game.getBoardSpace(position).isOccupied()) {
                                List<Vector> legalMoves = getLegalMoves(position);

                                if (legalMoves.size() > 0)
                                    highlightPositions(legalMoves);
                            }
                        }
                    }
                });
            }
        }
    }

    private List<Vector> getLegalMoves(Vector vector) {
        ChessPiece piece = game.getBoardSpace(vector).getPiece();
        return piece.getLegalMoves(game);
    }

    private void highlightPositions(List<Vector> positions) {
        for (Vector position : positions) {
            System.out.println("Legal Move is " + position.toString());
            //update view
            gameUI.getBoardPosition(position).highlight(true);
            //update model
            game.getBoardSpace(position).highlight(true);
        }
    }

    private void selectPositionAtVector(Vector vector) {
        //update view
        gameUI.getBoardPosition(vector).select(true);

        //update model
        game.getBoardSpace(vector).select(true);
    }

    private void deselectPositionAtVector(Vector vector) {
        //update view
        gameUI.getBoardPosition(vector).select(false);

        //update model
        game.getBoardSpace(vector).select(false);
    }

    private void highlightPositionAtVector(Vector vector) {
        //update view
        gameUI.getBoardPosition(vector).highlight(true);

        //update model
        game.getBoardSpace(vector).highlight(true);
    }

    private void removeHighlightFromPositionAtVector(Vector vector) {
        //update view
        gameUI.getBoardPosition(vector).highlight(false);

        //update model
        game.getBoardSpace(vector).highlight(false);
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
            for (BoardSpace position : game.getBoard()[i]) {
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
            for (BoardSpace position : game.getBoard()[i]) {
                position.highlight(false);
            }
        }
    }



}
