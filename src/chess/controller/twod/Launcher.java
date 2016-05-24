package chess.controller.twod;

import chess.model.ChessGame;
import chess.view.twod.ChessGameUI;
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
        ChessGameUI gameUI = new ChessGameUI();

        primaryStage.setTitle("Chess");

        Scene scene = new Scene(gameUI);

        BoardController boardController = new BoardController(primaryStage, gameUI, game);

        scene.getStylesheets().add(Launcher.class.getResource("/chess/view/twod/Board.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();

    }
}
