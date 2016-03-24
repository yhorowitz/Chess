package chess.controller.twod;


import chess.view.twod.Board2D;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

import javax.swing.*;

public class Game2DController {

    private Board2D game;

    public Game2DController(Board2D game) {
        this.game = game;

        //add listener when the scene size changes so that both horizontal and vertical width change together
        game.getScene().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //game.getScene().
            }
        });
    }



}
