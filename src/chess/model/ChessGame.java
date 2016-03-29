package chess.model;

import chess.model.pieces.*;

/**
 * Holds the state of a single chess game
 */
public class ChessGame {
    public final static int BOARD_SIZE = 8;

    private BoardSpace[][] board = new BoardSpace[8][8];
    private Color currentTurn = Color.WHITE;
    private Vector selectedPosition = null;


    public ChessGame() {
        createBoard();
        setUpNewGame();
    }

    /**
     * Constructor that allows you to create an instance with an
     * empty board
     *
     * @param setUpAsNewGame
     */
    public ChessGame(boolean setUpAsNewGame) {
        createBoard();

        if (setUpAsNewGame) {
            setUpNewGame();
        }
    }

    /**
     * Initializes the array for all spaces on the board
     */
    private void createBoard() {
        //set up all spaces on the game
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = new BoardSpace(new Vector(col, row));
            }
        }
    }

    /**
     * Sets up the pieces in the correct places for a new game
     */
    private void setUpNewGame() {

        //add black pieces to the game
        board[0][0].setPiece(new Rook(Color.BLACK, board[0][0].getPosition()));
        board[0][1].setPiece(new Knight(Color.BLACK, board[0][1].getPosition()));
        board[0][2].setPiece(new Bishop(Color.BLACK, board[0][2].getPosition()));
        board[0][3].setPiece(new Queen(Color.BLACK, board[0][3].getPosition()));
        board[0][4].setPiece(new King(Color.BLACK, board[0][4].getPosition()));
        board[0][5].setPiece(new Bishop(Color.BLACK, board[0][5].getPosition()));
        board[0][6].setPiece(new Knight(Color.BLACK, board[0][6].getPosition()));
        board[0][7].setPiece(new Rook(Color.BLACK, board[0][7].getPosition()));
        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(new Pawn(Color.BLACK, board[1][col].getPosition()));
        }

        //add white pieces to the game
        for (int col = 0; col < 8; col++) {
            board[6][col].setPiece(new Pawn(Color.WHITE, board[6][col].getPosition()));
        }
        board[7][0].setPiece(new Rook(Color.WHITE, board[7][0].getPosition()));
        board[7][1].setPiece(new Knight(Color.WHITE, board[7][1].getPosition()));
        board[7][2].setPiece(new Bishop(Color.WHITE, board[7][2].getPosition()));
        board[7][3].setPiece(new Queen(Color.WHITE, board[7][3].getPosition()));
        board[7][4].setPiece(new King(Color.WHITE, board[7][4].getPosition()));
        board[7][5].setPiece(new Bishop(Color.WHITE, board[7][5].getPosition()));
        board[7][6].setPiece(new Knight(Color.WHITE, board[7][6].getPosition()));
        board[7][7].setPiece(new Rook(Color.WHITE, board[7][7].getPosition()));
    }

    /**
     * Sets a new position as the currently selected position
     *
     * @param vector
     */
    public void selectNewPosition(Vector vector) {
        selectedPosition = vector;
    }

    /**
     * Gets the currently selected position
     * @return
     */
    public Vector getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Gets the pieces at the currently selected position
     * @return
     */
    public ChessPiece getSelectedPiece() {
        return getBoardSpace(getSelectedPosition()).getPiece();
    }

    /**
     * Gets the current players turn (based on color)
     *
     * @return
     */
    public Color getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Changes which player's turn it is
     */
    public void changeTurns() {
        currentTurn = getCurrentTurn() == Color.BLACK ? Color.WHITE : Color.BLACK;
    }

    /**
     * Returns a single space on the board based on its position on the board
     *
     * @param vector
     * @return
     */
    public BoardSpace getBoardSpace(Vector vector) {
        int row = vector.getY();
        int column = vector.getX();

        return board[row][column];
    }

    /**
     * Switches the position of a piece in the board
     *
     * @param from The starting position
     * @param to The ending position
     * @return Whether the move was successfully completed
     */
    public boolean makeMove(ChessPiece piece, Vector from, Vector to) {
        //ensure the correct piece is being move
        if (getBoardSpace(from).getPiece() != piece)
            return false;

        getBoardSpace(from).setPiece(null);
        getBoardSpace(to).setPiece(piece);
        if (isEnPassant(piece, from, to)) { //must be done before setting all pawns to no longer be eligible. See method documentation for details.
            int direction = piece.getColor() == Color.BLACK ? -1 : 1;
            BoardSpace captureSpace = getBoardSpace(new Vector(to.getX(), to.getY() + direction));
            captureSpace.setPiece(null);
        }
        setAllPawnToNotEligibleForEnPassant(); //must be called before the moveTo method. See method documentation for details
        piece.moveTo(to);
        changeTurns();

        return true;

    }

    /**
     * Checks if a move is performing an En Passant.
     *
     * NOTE: This must be done before all pawns that are eligible for En Passant are reset to be ineligible.
     *       This is for the obvious reason that if it first resets it, the pawn will never be able to do an en passant.
     *
     * @param piece
     * @param from
     * @param to
     * @return
     */
    private boolean isEnPassant(ChessPiece piece, Vector from, Vector to) {

        if (!(piece instanceof Pawn))  //piece being moved is not a pawn
            return false;
        else if (from.getX() == to.getX()) //is pawn but not a capture move (pawns only move diagonal when capturing)
            return false;

        //get piece in space above the one being moved to
        //if it is a pawn that is eligible for en passant return true
        int direction = piece.getColor() == Color.BLACK ? -1 : 1;
        Vector potentialCaptureVector = new Vector(to.getX(), to.getY() + direction);
        BoardSpace potentialCaptureSpace = getBoardSpace(potentialCaptureVector);
        ChessPiece potentialCapturePiece = potentialCaptureSpace.getPiece();

        if (!(potentialCapturePiece instanceof Pawn))  //the piece is not a pawn
            return false;
        else if (((Pawn) potentialCapturePiece).isEligibleForEnPassant()) {
            System.out.println("is en passant");
            return true;
        }
        else {
            System.out.println("is not en passant");
            return false;
        }
    }

    /**
     * Set all pawns which are eligible for En Passant to no longer be eligible
     *
     * NOTE: This must be done before the piece is moved, otherwise if it is a pawn moving 2 spaces it will become ineligible
     */
    public void setAllPawnToNotEligibleForEnPassant() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                ChessPiece piece = getBoardSpace(new Vector(col, row)).getPiece();

                if (piece instanceof Pawn && ((Pawn) piece).isEligibleForEnPassant())
                    ((Pawn) piece).setEligibleForEnPassant(false);

            }
        }
    }

    /**
     * Checks if the current state of the game is a checkmate
     * @return
     */
    public boolean isCheckmate() {
        return false;
    }

    /**
     * Checks if the current state of the game is a check
     * @return
     */
    public boolean isCheck() {
        return false;
    }

    /**
     * Checks if the current state of the game is a stalemate
     * @return
     */
    public boolean iaStalematertt() {return false; }



}
