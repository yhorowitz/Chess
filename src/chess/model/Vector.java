package chess.model;

/**
 * Holds an x and y value representing a space on the
 * chess board
 *
 * IMPORTANT NOTE:
 *      X represents the column
 *      Y represents the row
 *
 *      This class takes the x parameter first, followed by the Y
 *      An 2D array will look at the rows first and then then columns
 *      This means when accessing the correct space in an array you must access it as such:
 *          array[Y][X] ;
 *      but when creating a vector it needs to be created as:
 *          new Vector(X, Y)
 */
public class Vector {

    int x;
    int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector))
            return false;
        else if (this.getX() != ((Vector) o).getX())
            return false;
        else if (this.getY() != ((Vector) o).getY())
            return false;
        else
            return true;
    }

    @Override
    public String toString() {
        return "X: " + getX() + ", Y: " + getY();
    }
}
