package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board2D;
import chess.view.twod.GridSpace2D;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javax.swing.*;

public class Game2DController {

    private Board2D gameUI;
    private ChessGame game;

    public Game2DController(Board2D gameUI, ChessGame game) {
        this.gameUI = gameUI;
        this.game = game;

        for (int i = 0; i < gameUI.getGridGUI().length; i++) {
            for (int j = 0; j < gameUI.getGridGUI().length; j++) {
                gameUI.getGridGUI()[i][j].addEventListener(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        GridSpace2D gridSpace = (GridSpace2D) event.getSource();
                        if (gridSpace.isSelected())
                            gridSpace.select(false);
                        else
                            gridSpace.select(true);
                    }
                });
            }
        }
    }



}
