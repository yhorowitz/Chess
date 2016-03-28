package chess.view.twod;

import chess.model.ChessGame;
import chess.model.Vector;
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

    public BoardPosition getBoardPosition(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        return grid[row][column];
    }

    public void initBoard(){

        this.setPrefColumns(8);
        this.setHgap(5);
        this.setVgap(5);

        grid = new BoardPosition[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                grid[row][col] = new BoardPosition(new Vector(col, row));

                setBackgroundColorForGridSpace(grid[row][col], col, row);

                this.getChildren().add(grid[row][col]);

            }
        }

    }

    private void setBackgroundColorForGridSpace(BoardPosition btn, int x, int y) {
        if (x % 2 == 0) {
            if (y % 2 == 0)
                btn.getStyleClass().add("grey");
            else
                btn.getStyleClass().add("lightBrown");
        }
        else {
            if (y % 2 == 0)
                btn.getStyleClass().add("lightBrown");
            else
                btn.getStyleClass().add("grey");
        }
    }

    public void update(ChessGame game) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Vector vector = new Vector(col, row);
                getBoardPosition(vector).update(game.getBoardSpace(vector));
            }
        }
    }
}
