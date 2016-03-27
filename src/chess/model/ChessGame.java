package chess.model;

import chess.model.pieces.*;

import java.util.List;

/**
 * Holds the state of a single chess game
 */
public class ChessGame {
    final int SIZE = 8;

    private BoardSpace[][] board = new BoardSpace[8][8];
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
                board[i][j] = new BoardSpace(new Vector(i, j));
            }
        }

        //add black pieces to the game
        board[0][0].setPiece(new Rook(Color.BLACK, board[0][0].getPosition()));
        board[1][0].setPiece(new Knight(Color.BLACK, board[1][0].getPosition()));
        board[2][0].setPiece(new Bishop(Color.BLACK, board[2][0].getPosition()));
        board[3][0].setPiece(new Queen(Color.BLACK, board[3][0].getPosition()));
        board[4][0].setPiece(new King(Color.BLACK, board[4][0].getPosition()));
        board[5][0].setPiece(new Bishop(Color.BLACK, board[5][0].getPosition()));
        board[6][0].setPiece(new Knight(Color.BLACK, board[6][0].getPosition()));
        board[7][0].setPiece(new Rook(Color.BLACK, board[7][0].getPosition()));
        for (int i = 0; i < 8; i++) {
            board[i][1].setPiece(new Pawn(Color.BLACK, board[i][1].getPosition()));
        }

        //add white pieces to the game
        for (int i = 0; i < 8; i++) {
            board[i][6].setPiece(new Pawn(Color.WHITE, board[i][6].getPosition()));
        }
        board[0][7].setPiece(new Rook(Color.WHITE, board[0][7].getPosition()));
        board[1][7].setPiece(new Knight(Color.WHITE, board[1][7].getPosition()));
        board[2][7].setPiece(new Bishop(Color.WHITE, board[2][7].getPosition()));
        board[3][7].setPiece(new Queen(Color.WHITE, board[3][7].getPosition()));
        board[4][7].setPiece(new King(Color.WHITE, board[4][7].getPosition()));
        board[5][7].setPiece(new Bishop(Color.WHITE, board[5][7].getPosition()));
        board[6][7].setPiece(new Knight(Color.WHITE, board[6][7].getPosition()));
        board[7][7].setPiece(new Rook(Color.WHITE, board[7][7].getPosition()));

    }

    public Color getCurrentTurn() {
        return null;
    }

    public void changeTurns() {

    }

    public BoardSpace[][] getBoard() {
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

    public boolean checkForDraw() {return false; }



}
