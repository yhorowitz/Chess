package chess.controller.twod;


import chess.model.ChessGame;
import chess.view.twod.Board2D;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

import javax.swing.*;

public class Game2DController {

    private Board2D gameUI;
    private ChessGame game;

    public Game2DController(Board2D gameUI, ChessGame game) {
        this.gameUI = gameUI;
        this.game = game;
    }



}
