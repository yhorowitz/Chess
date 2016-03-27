package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board;
import javafx.application.Application;

public class Launcher {

    public static void main(String[] args) {

        Application.launch(Board.class, args);

        ChessGame game = new ChessGame();
        Board gameGUI = new Board();

        new BoardController(gameGUI, game);

    }

}
