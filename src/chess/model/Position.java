package chess.model;

/**
 * Holds a row and col value representing a space on the
 * chess board
 */
public class Position {

    int row;
    int col;

    public Position(int row, int col) {
        setRow(row);
        setCol(col);
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position))
            return false;
        else if (this.getRow() != ((Position) o).getRow())
            return false;
        else if (this.getCol() != ((Position) o).getCol())
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "Row: " + getRow() + ", Column: " + getCol();
    }
}
