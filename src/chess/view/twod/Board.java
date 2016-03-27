package chess.view.twod;

import chess.model.pieces.*;
import chess.model.Color;
import chess.model.Vector;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Board extends Application {

    private BoardPosition[][] grid;
    private TilePane boardPane= new TilePane();
    public Board() {
        initBoard();
    }

    public BoardPosition[][] getGridGUI() {
        return this.grid;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("2D Chess");

        Scene scene = new Scene(boardPane, 875, 875);
        scene.getStylesheets().add(Board.class.getResource("Board.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void initBoard(){

        boardPane.setPrefColumns(8);
        boardPane.setHgap(5);
        boardPane.setVgap(5);

        grid = new BoardPosition[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[j][i] = new BoardPosition(new Vector(j, i));

                setBackgroundColorForGridSpace(grid[j][i], j, i);

                boardPane.getChildren().add(grid[j][i]);

                grid[j][i].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        BoardPosition gridSpace = (BoardPosition) event.getSource();
                        if (gridSpace.isSelected()) {
                            gridSpace.select(false);
                        } else {
                            gridSpace.select(true);
                        }
                    }
                });

            }
        }
        //add black pieces to the game
        grid[0][0].setPiece(new Rook(Color.BLACK, grid[0][0].getPosition()));
        grid[1][0].setPiece(new Knight(Color.BLACK, grid[1][0].getPosition()));
        grid[2][0].setPiece(new Bishop(Color.BLACK, grid[2][0].getPosition()));
        grid[3][0].setPiece(new Queen(Color.BLACK, grid[3][0].getPosition()));
        grid[4][0].setPiece(new King(Color.BLACK, grid[4][0].getPosition()));
        grid[5][0].setPiece(new Bishop(Color.BLACK, grid[5][0].getPosition()));
        grid[6][0].setPiece(new Knight(Color.BLACK, grid[6][0].getPosition()));
        grid[7][0].setPiece(new Rook(Color.BLACK, grid[7][0].getPosition()));
        for (int i = 0; i < 8; i++) {
            grid[i][1].setPiece(new Pawn(Color.BLACK, grid[i][1].getPosition()));
        }

        //add white pieces to the game
        for (int i = 0; i < 8; i++) {
            grid[i][6].setPiece(new Pawn(Color.WHITE, grid[i][6].getPosition()));
        }
        grid[0][7].setPiece(new Rook(Color.WHITE, grid[0][7].getPosition()));
        grid[1][7].setPiece(new Knight(Color.WHITE, grid[1][7].getPosition()));
        grid[2][7].setPiece(new Bishop(Color.WHITE, grid[2][7].getPosition()));
        grid[3][7].setPiece(new Queen(Color.WHITE, grid[3][7].getPosition()));
        grid[4][7].setPiece(new King(Color.WHITE, grid[4][7].getPosition()));
        grid[5][7].setPiece(new Bishop(Color.WHITE, grid[5][7].getPosition()));
        grid[6][7].setPiece(new Knight(Color.WHITE, grid[6][7].getPosition()));
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
