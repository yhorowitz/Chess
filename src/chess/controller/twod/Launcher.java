package chess.controller.twod;

import chess.model.ChessGame;
import chess.view.twod.ChessGameUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage mainStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;

        ChessGame game = new ChessGame();
        ChessGameUI gameUI = new ChessGameUI();

        mainStage.setTitle("Chess");

        Scene scene = new Scene(gameUI);

        BoardController boardController = new BoardController(gameUI, game);

        scene.getStylesheets().add(Launcher.class.getResource("../../view/twod/Board.css").toExternalForm());
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.sizeToScene();
        mainStage.show();

    }
}
