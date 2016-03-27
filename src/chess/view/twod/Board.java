package chess.view.twod;

import chess.model.pieces.*;
import chess.model.Color;
import chess.model.Vector;
import javafx.scene.layout.*;

public class Board extends TilePane {

    private BoardPosition[][] grid;
    
    public Board() {
        initBoard();
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
        //add black pieces to the game
        grid[0][0].setPiece(new Rook(Color.BLACK, grid[0][0].getPosition()));
        grid[0][1].setPiece(new Knight(Color.BLACK, grid[0][1].getPosition()));
        grid[0][2].setPiece(new Bishop(Color.BLACK, grid[0][2].getPosition()));
        grid[0][3].setPiece(new Queen(Color.BLACK, grid[0][3].getPosition()));
        grid[0][4].setPiece(new King(Color.BLACK, grid[0][4].getPosition()));
        grid[0][5].setPiece(new Bishop(Color.BLACK, grid[0][5].getPosition()));
        grid[0][6].setPiece(new Knight(Color.BLACK, grid[0][6].getPosition()));
        grid[0][7].setPiece(new Rook(Color.BLACK, grid[0][7].getPosition()));
        for (int col = 0; col < 8; col++) {
            grid[1][col].setPiece(new Pawn(Color.BLACK, grid[1][col].getPosition()));
        }

        //add white pieces to the game
        for (int col = 0; col < 8; col++) {
            grid[6][col].setPiece(new Pawn(Color.WHITE, grid[6][col].getPosition()));
        }
        grid[7][0].setPiece(new Rook(Color.WHITE, grid[7][0].getPosition()));
        grid[7][1].setPiece(new Knight(Color.WHITE, grid[7][1].getPosition()));
        grid[7][2].setPiece(new Bishop(Color.WHITE, grid[7][2].getPosition()));
        grid[7][3].setPiece(new Queen(Color.WHITE, grid[7][3].getPosition()));
        grid[7][4].setPiece(new King(Color.WHITE, grid[7][4].getPosition()));
        grid[7][5].setPiece(new Bishop(Color.WHITE, grid[7][5].getPosition()));
        grid[7][6].setPiece(new Knight(Color.WHITE, grid[7][6].getPosition()));
        grid[7][7].setPiece(new Rook(Color.WHITE, grid[7][7].getPosition()));

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
}
