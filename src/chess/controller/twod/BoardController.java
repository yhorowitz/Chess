package chess.controller.twod;


import chess.model.ChessGame;
import chess.model.BoardSpace;
import chess.model.Move;
import chess.model.Position;
import chess.model.pieces.ChessPiece;
import chess.view.twod.Board;
import chess.view.twod.BoardPosition;
import chess.view.twod.history.GameHistory;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.List;

public class BoardController {

    private Board gameUI;
    private GameHistory gameHistoryUI;
    private ChessGame game;

    public BoardController(Board gameUI, GameHistory gameHistoryUI, ChessGame game) {
        this.gameUI = gameUI;
        this.gameHistoryUI = gameHistoryUI;
        this.game = game;

        for (int row = 0; row < gameUI.getGrid().length; row++) {
            for (int col = 0; col < gameUI.getGrid().length; col++) {
                gameUI.getGrid()[row][col].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        BoardPosition gridSpace = (BoardPosition) event.getSource();
                        Position position = gridSpace.getPosition();
                        BoardSpace space = BoardController.this.game.getBoardSpace(position);

                        if (gridSpace.isSelected()) {
                            //remove the selection and all highlights on the board
                            deselectPositionAtVector(gridSpace.getPosition());
                            removeHighlightFromAllBoardPositions();
                        }
                        else if (space.isOccupied() && (space).getPiece().getPieceColor() == BoardController.this.game.getCurrentTurn()){
                            //select the space and highlight all legal moves

                            deselectAllBoardPositions();
                            removeHighlightFromAllBoardPositions();
                            selectPositionAtVector(gridSpace.getPosition());

                            //if space is not empty highlight legal moves
                            if (space.isOccupied()) {
                                List<Position> legalMoves = getLegalMoves(position);

                                if (legalMoves.size() > 0)
                                    highlightPositions(legalMoves);
                            }
                        }
                        else if (gridSpace.isHighlighted()) {
                            //move the selected piece to the selected position and clear all highlights
                            ChessPiece piece = BoardController.this.game.getSelectedPiece();
                            Position from = BoardController.this.game.getSelectedPosition();
                            Position to = position;

                            BoardController.this.game.makeMove(piece, from, to);
                            BoardController.this.gameUI.update(BoardController.this.game);

                            deselectAllBoardPositions();
                            removeHighlightFromAllBoardPositions();

                            List<Move> gameHistory = game.getGameHistory();
                            gameHistoryUI.addMove(gameHistory.size(), gameHistory.get(gameHistory.size() - 1));

                        }
                    }
                });
            }
        }
    }

    private List<Position> getLegalMoves(Position position) {
        ChessPiece piece = game.getBoardSpace(position).getPiece();
        return piece.getLegalMoves(game);
    }

    private void highlightPositions(List<Position> positions) {
        for (Position position : positions) {
            //update view
            gameUI.getBoardPosition(position).highlight(true);
            //update model
            game.getBoardSpace(position).highlight(true);
        }
    }

    private void selectPositionAtVector(Position position) {
        //update view
        gameUI.getBoardPosition(position).select(true);

        //update model
        game.getBoardSpace(position).select(true);
        game.selectNewPosition(position);
    }

    private void deselectPositionAtVector(Position position) {
        //update view
        gameUI.getBoardPosition(position).select(false);

        //update model
        game.getBoardSpace(position).select(false);
        game.selectNewPosition(null);
    }

    private void highlightPositionAtVector(Position position) {
        //update view
        gameUI.getBoardPosition(position).highlight(true);

        //update model
        game.getBoardSpace(position).highlight(true);
    }

    private void removeHighlightFromPositionAtVector(Position position) {
        //update view
        gameUI.getBoardPosition(position).highlight(false);

        //update model
        game.getBoardSpace(position).highlight(false);
    }

    private void deselectAllBoardPositions() {
        //update view
        for (int i = 0; i < gameUI.getGrid().length; i++) {
            for (BoardPosition position : gameUI.getGrid()[i]) {
                position.select(false);
            }
        }

        //update model
        for (int row = 0; row < ChessGame.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessGame.BOARD_SIZE; col++) {
                game.getBoardSpace(new Position(row, col)).highlight(false);
            }
        }
        game.selectNewPosition(null);

    }

    private void removeHighlightFromAllBoardPositions() {
        //update view
        for (int row = 0; row < ChessGame.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessGame.BOARD_SIZE; col++) {
                gameUI.getBoardPosition(new Position(row, col)).highlight(false);
            }
        }

        //update model
        for (int row = 0; row < ChessGame.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessGame.BOARD_SIZE; col++) {
                game.getBoardSpace(new Position(row, col)).highlight(false);
            }
        }
    }



}
