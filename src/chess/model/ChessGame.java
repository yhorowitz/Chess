package chess.model;

import chess.model.pieces.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds the state of a single chess game
 */
public class ChessGame {
    public final static int BOARD_SIZE = 8;

    private BoardSpace[][] board = new BoardSpace[BOARD_SIZE][BOARD_SIZE];
    private PieceColor currentTurn = PieceColor.WHITE;
    private Position selectedPosition = null;

    private PlayerPieceSet blackPieces = new PlayerPieceSet(PieceColor.BLACK);
    private PlayerPieceSet whitePieces = new PlayerPieceSet(PieceColor.WHITE);

    private List<Move> gameHistory = new ArrayList<>();

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
                board[row][col] = new BoardSpace(new Position(row, col));
            }
        }
    }

    /**
     * Sets up the pieces in the correct places for a new game
     */
    private void setUpNewGame() {

        //add black pieces to the game
        board[0][0].setPiece(blackPieces.addPiece(Rook.class, board[0][0].getPosition()));
        board[0][1].setPiece(blackPieces.addPiece(Knight.class, board[0][1].getPosition()));
        board[0][2].setPiece(blackPieces.addPiece(Bishop.class, board[0][2].getPosition()));
        board[0][3].setPiece(blackPieces.addPiece(Queen.class, board[0][3].getPosition()));
        board[0][4].setPiece(blackPieces.addPiece(King.class, board[0][4].getPosition()));
        board[0][5].setPiece(blackPieces.addPiece(Bishop.class, board[0][5].getPosition()));
        board[0][6].setPiece(blackPieces.addPiece(Knight.class, board[0][6].getPosition()));
        board[0][7].setPiece(blackPieces.addPiece(Rook.class, board[0][7].getPosition()));
        for (int col = 0; col < 8; col++) {
            board[1][col].setPiece(blackPieces.addPiece(Pawn.class, board[1][col].getPosition()));
        }

        //add white pieces to the game
        for (int col = 0; col < 8; col++) {
            board[6][col].setPiece(whitePieces.addPiece(Pawn.class, board[6][col].getPosition()));
        }
        board[7][0].setPiece(whitePieces.addPiece(Rook.class, board[7][0].getPosition()));
        board[7][1].setPiece(whitePieces.addPiece(Knight.class, board[7][1].getPosition()));
        board[7][2].setPiece(whitePieces.addPiece(Bishop.class, board[7][2].getPosition()));
        board[7][3].setPiece(whitePieces.addPiece(Queen.class, board[7][3].getPosition()));
        board[7][4].setPiece(whitePieces.addPiece(King.class, board[7][4].getPosition()));
        board[7][5].setPiece(whitePieces.addPiece(Bishop.class, board[7][5].getPosition()));
        board[7][6].setPiece(whitePieces.addPiece(Knight.class, board[7][6].getPosition()));
        board[7][7].setPiece(whitePieces.addPiece(Rook.class, board[7][7].getPosition()));

    }

    /**
     * Sets a new position as the currently selected position
     *
     * @param position
     */
    public void selectNewPosition(Position position) {
        selectedPosition = position;
    }

    /**
     * Gets the currently selected position
     * @return
     */
    public Position getSelectedPosition() {
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
    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Changes which player's turn it is
     */
    public void changeTurns() {
        currentTurn = getCurrentTurn() == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
    }

    /**
     * Returns a single space on the board based on its position on the board
     *
     * @param position
     * @return
     */
    public BoardSpace getBoardSpace(Position position) {
        int row = position.getRow();
        int column = position.getCol();

        return board[row][column];
    }

    /**
     * Switches the position of a piece in the board
     *
     * @param from The starting position
     * @param to The ending position
     * @return Whether the move was successfully completed
     */
    public boolean makeMove(ChessPiece piece, Position from, Position to) {

        // /ensure the correct piece is being move
        if (getBoardSpace(from).getPiece() != piece)
            return false;

        Move currentMove = new Move(piece, from, to);

        if (isEnPassant(piece, from, to)) { //must be done before setting all pawns to no longer be eligible. See method documentation for details.
            int direction = piece.getPieceColor() == PieceColor.BLACK ? -1 : 1;
            BoardSpace captureSpace = getBoardSpace(new Position(to.getRow() + direction, to.getCol()));
            currentMove.setAsCaptureMove(captureSpace.getPiece(), captureSpace.getPosition());
            capture(captureSpace.getPosition());
        }
        setAllPawnToNotEligibleForEnPassant(); //must be called before the moveTo method. See method documentation for details
        if (isCapture(to)) {
            currentMove.setAsCaptureMove(getBoardSpace(to).getPiece(), to);
            capture(to);
        }

        //move the piece
        getBoardSpace(from).setPiece(null);
        getBoardSpace(to).setPiece(piece);
        piece.moveTo(to);

        if (isCheckmate())
            currentMove.setAsCheckmate();
        else if (isCheck())
            currentMove.setAsCheck();

        changeTurns();

        gameHistory.add(currentMove);
        System.out.println(gameHistory.get(gameHistory.size() - 1).getDetailedDescription());
        return true;

    }

    public boolean isCapture(Position position) {
        if (getBoardSpace(position).isOccupied())
            return true;
        else
            return false;
    }

    public void capture(Position position) {
        ChessPiece piece = getBoardSpace(position).getPiece();
        if (piece.getPieceColor() == PieceColor.BLACK)
            blackPieces.capture(piece);
        else
            whitePieces.capture(piece);

        getBoardSpace(position).setPiece(null);
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
    private boolean isEnPassant(ChessPiece piece, Position from, Position to) {

        if (!(piece instanceof Pawn)) { //piece being moved is not a pawn
            return false;
        }
        else if (from.getCol() == to.getCol()) { //is pawn but not a capture move (pawns only move diagonal when capturing)
            return false;
        }

        //get piece in space above the one being moved to
        //if it is a pawn that is eligible for en passant return true
        int direction = piece.getPieceColor() == PieceColor.BLACK ? -1 : 1;
        Position potentialCapturePosition = new Position(to.getRow() + direction, to.getCol());
        BoardSpace potentialCaptureSpace = getBoardSpace(potentialCapturePosition);
        ChessPiece potentialCapturePiece = potentialCaptureSpace.getPiece();

        if (!(potentialCapturePiece instanceof Pawn)) {  //the piece is not a pawn
            return false;
        }
        else if (((Pawn) potentialCapturePiece).isEligibleForEnPassant()) {
            return true;
        }
        else {
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
                ChessPiece piece = getBoardSpace(new Position(row, col)).getPiece();

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
        /*
            Ways to get out of check
            1. Move King
            2. Block with another piece
            3. Capture the checking piece

            For 1 just check if king is in check and if it has any moves
            For 2 and 3 need to simulate every legal move by the opponents piece and see if one
            of its moves end up with its King not being in check (there can still be check if its move puts
            the other king in check)
         */


//        King whiteKing = (King) whitePieces.getAlivePiecesOfType(King.class).get(0);
//        King blackKing = (King) blackPieces.getAlivePiecesOfType(King.class).get(0);
//
//        boolean whiteIsCheckmated = isWhiteInCheck() && whiteKing.getLegalMoves(this).size() == 0;
//        boolean blackIsCheckmated = isBlackInCheck() && blackKing.getLegalMoves(this).size() == 0;
//
//        return whiteIsCheckmated || blackIsCheckmated;
        return false;
    }


    /**
     * Checks if the current state of the game is a check
     * @return
     */
    public boolean isCheck() {
        return isWhiteInCheck() || isBlackInCheck();
    }

    public boolean isWhiteInCheck() {
        //go through each piece and see if one of its legal moves is the position of one of the kings

        Position whiteKingPosition = whitePieces.getAlivePiecesOfType(King.class).get(0).getPosition();

        //look for white in check
        for (ChessPiece piece : blackPieces.getAllAlivePieces()) {
            if (piece.getClass() != King.class) {
                for (Position position : piece.getLegalMoves(this)) {
                    if (position.equals(whiteKingPosition))
                        return true;
                }
            }
        }

        return false;
    }

    public boolean isBlackInCheck() {
        //go through each piece and see if one of its legal moves is the position of one of the kings

        Position blackKingPosition = blackPieces.getAlivePiecesOfType(King.class).get(0).getPosition();

        //look for black in check
        for (ChessPiece piece : whitePieces.getAllAlivePieces()) {
            if (piece.getClass() != King.class) {
                for (Position position : piece.getLegalMoves(this)) {
                    if (position.equals(blackKingPosition))
                        return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the current state of the game is a stalemate
     * @return
     */
    public boolean isStalemate() {return false; }

}
