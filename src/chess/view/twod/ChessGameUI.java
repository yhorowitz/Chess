package chess.view.twod;

import chess.model.ChessGame;
import chess.model.Move;
import chess.model.Position;
import chess.view.twod.history.GameHistory;
import javafx.scene.layout.BorderPane;

/**
 * Container class for all view objects relating to the board
 */
public class ChessGameUI extends BorderPane {

    private Board gameBoard = new Board();
    private GameHistory gameHistory = new GameHistory();

    public ChessGameUI() {
        this.setCenter(gameBoard);
        this.setBottom(gameHistory);

        System.out.println("pad left: " + this.getPadding().getLeft());
        System.out.println("pad right: " + this.getPadding().getRight());
        System.out.println("pad bot: " + this.getPadding().getBottom());
        System.out.println("pad top: " + this.getPadding().getTop());
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
}
