package chess.view.twod;

import chess.controller.twod.Launcher;
import chess.model.ChessGame;
import chess.model.Move;
import chess.model.Position;
import chess.view.twod.history.GameHistory;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 * Container class for all view objects relating to the board
 */
public class ChessGameUI extends BorderPane {

    private Board gameBoard = new Board();
    private GameHistory gameHistory = new GameHistory();

    public ChessGameUI() {
        this.setCenter(gameBoard);
        showGameHistory();

        this.setMargin(gameHistory, new Insets(20, 0, 0, 0));
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
        this.setBottom(gameHistory);
    }

    public void hideGameHistory() {
        this.setBottom(null);
    }
}
