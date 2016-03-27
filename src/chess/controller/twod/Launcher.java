package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ChessGame game = new ChessGame();
        Board gameGUI = new Board();

        primaryStage.setTitle("Chess");

        Scene scene = new Scene(gameGUI, 875, 875);

        BoardController boardController = new BoardController(gameGUI, game);

        scene.getStylesheets().add(Launcher.class.getResource("../../view/twod/Board.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
