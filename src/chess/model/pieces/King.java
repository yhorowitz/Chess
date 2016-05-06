package chess.model.pieces;

import chess.model.BoardSpace;
import chess.model.ChessGame;
import chess.model.PieceColor;
import chess.model.Position;
import chess.view.twod.Board;

import java.util.ArrayList;
import java.util.List;


public class King extends ChessPiece  {


    public King(PieceColor pieceColor) {
        this.setPieceColor(pieceColor);
    }

    public King(PieceColor pieceColor, Position position) {
        this.setPieceColor(pieceColor);
        this.setPosition(position);
    }

    @Override
    public List<Position> getLegalMoves(ChessGame game, boolean removeMovesThatCauseCheck) {
        List<Position> legalMoves = new ArrayList<>();

        int currRow = getPosition().getRow();
        int currCol = getPosition().getCol();

        //check all spaces around the king
        BoardSpace upperLeft = game.getBoardSpace(new Position(currRow - 1, currCol - 1));
        BoardSpace upper = game.getBoardSpace(new Position(currRow - 1, currCol));
        BoardSpace upperRight = game.getBoardSpace(new Position(currRow - 1, currCol + 1));
        BoardSpace left = game.getBoardSpace(new Position(currRow, currCol - 1));
        BoardSpace right = game.getBoardSpace(new Position(currRow, currCol + 1));
        BoardSpace bottomLeft = game.getBoardSpace(new Position(currRow + 1, currCol - 1));
        BoardSpace bottom = game.getBoardSpace(new Position(currRow + 1, currCol));
        BoardSpace bottomRight = game.getBoardSpace(new Position(currRow + 1, currCol + 1));

        if (spaceIsEmptyOrCanBeCaptured(upperLeft)) {
            legalMoves.add(upperLeft.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(upper)) {
            legalMoves.add(upper.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(upperRight)) {
            legalMoves.add(upperRight.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(left)) {
            legalMoves.add(left.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(right)) {
            legalMoves.add(right.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(bottomLeft)) {
            legalMoves.add(bottomLeft.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(bottom)) {
            legalMoves.add(bottom.getPosition());
        }

        if (spaceIsEmptyOrCanBeCaptured(bottomRight)) {
            legalMoves.add(bottomRight.getPosition());
        }

        //check for king side castling
        if (canKingSideCastle(game)) {
            legalMoves.add(new Position(currRow, currCol + 2));
        }

        //check for queen side castling
        if (canQueenSideCastle(game)) {
            legalMoves.add(new Position(currRow, currCol -3));
        }

        if (removeMovesThatCauseCheck)
            removeMovesThatCauseCheck(game, legalMoves);

        return legalMoves;
    }

    private boolean spaceIsEmptyOrCanBeCaptured(BoardSpace space) {
        return space != null && (space.isEmpty() || space.getPiece().getPieceColor() != this.getPieceColor());
    }

    private boolean canKingSideCastle(ChessGame game) {
        boolean canCastle = true;

        int currRow = getPosition().getRow();
        int currCol = getPosition().getCol();

        BoardSpace oneToTheRight = game.getBoardSpace(new Position(currRow, currCol + 1));
        BoardSpace twoToTheRight = game.getBoardSpace(new Position(currRow, currCol + 2));
        BoardSpace rooksPosition = game.getBoardSpace(new Position(currRow, currCol + 3));

        if (oneToTheRight == null || twoToTheRight == null || rooksPosition == null)
            canCastle = false;
        else if (this.hasMoved() || (rooksPosition.getPiece() != null && rooksPosition.getPiece().hasMoved()))
            canCastle = false;
        else if (oneToTheRight.isOccupied() || twoToTheRight.isOccupied())
            canCastle = false;
        else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), oneToTheRight.getPosition()))
            canCastle = false;
        else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), twoToTheRight.getPosition()))
            canCastle = false;

        return  canCastle;
    }

    private boolean canQueenSideCastle(ChessGame game) {
        boolean canCastle = true;

        int currRow = getPosition().getRow();
        int currCol = getPosition().getCol();

        BoardSpace oneToTheLeft = game.getBoardSpace(new Position(currRow, currCol - 1));
        BoardSpace twoToTheLeft = game.getBoardSpace(new Position(currRow, currCol - 2));
        BoardSpace threeToTheLeft = game.getBoardSpace(new Position(currRow, currCol - 3));
        BoardSpace rooksPosition = game.getBoardSpace(new Position(currRow, currCol - 4));

        if (oneToTheLeft == null || twoToTheLeft == null || threeToTheLeft == null || rooksPosition == null)
            canCastle = false;
        else if (this.hasMoved() || (rooksPosition.getPiece() != null && rooksPosition.getPiece().hasMoved()))
            canCastle = false;
        else if (oneToTheLeft.isOccupied() || twoToTheLeft.isOccupied() || threeToTheLeft.isOccupied())
            canCastle = false;
        else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), oneToTheLeft.getPosition()))
            canCastle = false;
        else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), twoToTheLeft.getPosition()))
            canCastle = false;
        else if (game.moveCausesCheckForItsOwnKing(this.getPosition(), threeToTheLeft.getPosition()))
            canCastle = false;

        return  canCastle;
    }

    @Override
    public String getNotationLetter() {
        return "K";
    }

    @Override
    public String getNotationSymbol(){
        int add = getPieceColor() == PieceColor.BLACK ? 6: 0;
        return (char)(0x2654 + add) + "";
    }

}
