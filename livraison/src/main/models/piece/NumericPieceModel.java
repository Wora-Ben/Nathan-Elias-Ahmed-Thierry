package main.models.piece;

import main.models.position.PositionModel;

import java.awt.*;

/**
 * Numeric puzzle piece
 */
public class NumericPieceModel extends AbstractPieceModel {
    private int value;

    /**
     * Constructor of numeric piece which have a value of type int
     *
     * @param value                the piece value
     * @param currentPositionModel the piece coords
     * @param movingPiece          if the piece is the moving piece
     */
    public NumericPieceModel(int value, PositionModel currentPositionModel, PositionModel finalPositionModel, boolean movingPiece) {
        super(currentPositionModel, finalPositionModel, movingPiece);
        this.value = value;
    }

    /**
     * Constructor of numeric moving piece which have a value of type int
     *
     * @param currentPositionModel the moving piece coords
     */
    public NumericPieceModel(PositionModel currentPositionModel, PositionModel finalPositionModel) {
        this(-1, currentPositionModel, finalPositionModel, true);
    }

    /**
     * Method to draw this piece
     *
     * @param g Graphic used to draw this piece
     */
    @Override
    public void draw(Graphics g) {
        //Drawing this piece
        Font font = g.getFont().deriveFont(Font.BOLD, 30f);
        g.setFont(font);

        int x = getCurrentPosition().getPositionX() * this.getPieceWidth();
        int y = getCurrentPosition().getPositionY() * this.getPieceHeight();

        int textX = x - 10 + (this.getPieceWidth() / 2);
        int textY = y + 10 + (this.getPieceHeight() / 2);

        g.drawString(String.valueOf(value), textX, textY);
        g.drawRect(x, y, this.getPieceWidth(), this.getPieceHeight());
    }

    @Override
    public String toString() {
        return "  " + value + " ";
    }

}
