package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board2D;

public class Launcher2D {

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        Board2D gameGUI = new Board2D();

        new Game2DController(gameGUI, game);
    }

}
