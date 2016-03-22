package chess.model;

import chess.model.pieces.ChessPiece;

import java.util.List;

/**
 * Holds the state of a single chess game
 */
public class ChessGame {

    private GridSpaceModel[][] board = new GridSpaceModel[8][8];
    private Color currentTurn = Color.WHITE;

    ChessGame() {
        setUpNewGame();
    }

    private void setUpNewGame() {

    }

    public Color getCurrentTurn() {
        return null;
    }

    public void changeTurns() {

    }

    public GridSpaceModel[][] getBoard() {
        return this.board;
    }

    /**
     * Switches the position of a piece in the board
     *
     * @param from The starting position
     * @param to The ending position
     * @return Whether the move was successfully completed
     */
    public boolean makeMove(ChessPiece piece, Vector from, Vector to) {
        return false;
    }

    public List<Vector> getLegalMoves(ChessPiece piece) {
        return null;
    }

    public boolean checkForWin() {
        return false;
    }



}
