package chess.model;

import chess.model.pieces.*;

import java.util.List;

/**
 * Holds the state of a single chess game
 */
public class ChessGame {
    final int SIZE = 8;

    private GridSpaceModel[][] board = new GridSpaceModel[8][8];
    private Color currentTurn = Color.WHITE;

    public ChessGame() {
        setUpNewGame();
    }

    public ChessGame(boolean setUpPieces) {
        if (setUpPieces) {
            setUpNewGame();
        }
    }

    private void setUpNewGame() {

        //set up all spaces on the game
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new GridSpaceModel(new Vector(i, j));
            }
        }

        //add black pieces to the game
        board[0][0].setPiece(new Rook(Color.BLACK, board[0][0].getPosition()));
        board[0][1].setPiece(new Knight(Color.BLACK, board[0][1].getPosition()));
        board[0][2].setPiece(new Bishop(Color.BLACK, board[0][2].getPosition()));
        board[0][3].setPiece(new King(Color.BLACK, board[0][3].getPosition()));
        board[0][4].setPiece(new Queen(Color.BLACK, board[0][4].getPosition()));
        board[0][5].setPiece(new Bishop(Color.BLACK, board[0][5].getPosition()));
        board[0][6].setPiece(new Knight(Color.BLACK, board[0][6].getPosition()));
        board[0][7].setPiece(new Rook(Color.BLACK, board[0][17].getPosition()));
        for (int i = 0; i < SIZE; i++) {
            board[1][i].setPiece(new Pawn(Color.BLACK, board[1][i].getPosition()));
        }

        //add white pieces to the game
        for (int i = 0; i < SIZE; i++) {
            board[6][i].setPiece(new Pawn(Color.WHITE, board[6][i].getPosition()));
        }
        board[7][0].setPiece(new Rook(Color.BLACK, board[0][0].getPosition()));
        board[7][1].setPiece(new Knight(Color.BLACK, board[0][1].getPosition()));
        board[7][2].setPiece(new Bishop(Color.BLACK, board[0][2].getPosition()));
        board[7][3].setPiece(new King(Color.BLACK, board[0][3].getPosition()));
        board[7][4].setPiece(new Queen(Color.BLACK, board[0][4].getPosition()));
        board[7][5].setPiece(new Bishop(Color.BLACK, board[0][5].getPosition()));
        board[7][6].setPiece(new Knight(Color.BLACK, board[0][6].getPosition()));
        board[7][7].setPiece(new Rook(Color.BLACK, board[0][7].getPosition()));

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
