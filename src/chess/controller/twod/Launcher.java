package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board;
import chess.view.twod.history.GameHistory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ChessGame game = new ChessGame();
        Board gameBoard = new Board();
        GameHistory gameHistory = new GameHistory();

        primaryStage.setTitle("Chess");

        VBox uiContainer = new VBox();
        uiContainer.getStyleClass().add("transparent");
        uiContainer.setPadding(new Insets(10, 0, 0, 10));
        uiContainer.getChildren().addAll(gameBoard, gameHistory);

        Scene scene = new Scene(new Group());
        ((Group)scene.getRoot()).getChildren().addAll(uiContainer);

        BoardController boardController = new BoardController(gameBoard, gameHistory, game);

        scene.getStylesheets().add(Launcher.class.getResource("../../view/twod/Board.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }
}
