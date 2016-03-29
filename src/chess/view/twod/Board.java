package chess.view.twod;

import chess.model.ChessGame;
import chess.model.Position;
import javafx.scene.layout.*;

public class Board extends TilePane {

    private BoardPosition[][] grid;
    
    public Board() {
        initBoard();
        update(new ChessGame());
    }

    public Board(ChessGame game) {
        initBoard();
        update(game);
    }

    public BoardPosition[][] getGrid() {
        return this.grid;
    }

    public BoardPosition getBoardPosition(Position position) {
        int row = position.getRow();
        int column = position.getCol();

        return grid[row][column];
    }

    public void initBoard(){

        this.setPrefColumns(8);
        this.setHgap(5);
        this.setVgap(5);

        grid = new BoardPosition[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                grid[row][col] = new BoardPosition(new Position(row, col));

                setBackgroundColorForGridSpace(grid[row][col], row, col);

                this.getChildren().add(grid[row][col]);

            }
        }

    }

    private void setBackgroundColorForGridSpace(BoardPosition btn, int row, int col) {
        if (col % 2 == 0) {
            if (row % 2 == 0)
                btn.getStyleClass().add("grey");
            else
                btn.getStyleClass().add("lightBrown");
        }
        else {
            if (row % 2 == 0)
                btn.getStyleClass().add("lightBrown");
            else
                btn.getStyleClass().add("grey");
        }
    }

    public void update(ChessGame game) {
        for (int row = 0; row < ChessGame.BOARD_SIZE; row++) {
            for (int col = 0; col < ChessGame.BOARD_SIZE; col++) {
                Position position = new Position(row, col);
                getBoardPosition(position).update(game.getBoardSpace(position));
            }
        }
    }
}
