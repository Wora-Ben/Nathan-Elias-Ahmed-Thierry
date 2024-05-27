package main.models.piece;

import main.models.position.PositionModel;

import java.awt.*;

/**
 * Interface define main methods of a puzzle piece
 */
public interface Piece {
    /**
     * Returns current piece coords
     *
     * @return Returns current piece coords
     */
    public PositionModel getCurrentPosition();

    /**
     * Returns if this piece at the final correct position
     *
     * @return Returns if this piece at the final correct position
     */
    public boolean isTheFinalPosition();

    /**
     * Returns if this piece is the moving piece
     *
     * @return Returns if this piece is the moving piece
     */
    public boolean isMovingPiece();

    /**
     * This method define how a piece draw itself
     *
     * @param g Graphic used for drawing
     */
    public void draw(Graphics g);

    /**
     * This methods set new coords of this piece
     *
     * @param newPositionModel new coords
     */
    public void setNewPosition(PositionModel newPositionModel);

    /**
     * Returns current piece width
     *
     * @return Returns current piece width
     */
    public int getPieceWidth();

    /**
     * Returns current piece height
     *
     * @return Returns current piece height
     */
    public int getPieceHeight();
}
