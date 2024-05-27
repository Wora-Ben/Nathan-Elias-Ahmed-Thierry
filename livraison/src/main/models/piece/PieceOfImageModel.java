package main.models.piece;

import main.models.position.PositionModel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image puzzle piece
 */
public class PieceOfImageModel extends AbstractPieceModel {
    /**
     * Current piece image portion
     */
    BufferedImage value;

    /**
     * Constructor of image piece which have a value as a BufferedImage
     *
     * @param image                the piece image
     * @param currentPositionModel the piece coords
     * @param finalPositionModel   the final correct piece coords
     * @param movingPiece          if the piece is the moving piece
     */
    public PieceOfImageModel(BufferedImage image, PositionModel currentPositionModel, PositionModel finalPositionModel, boolean movingPiece) {
        super(currentPositionModel, finalPositionModel, movingPiece);
        this.value = image;
    }

    /**
     * Constructor of image moving piece which have a value as a BufferedImage
     *
     * @param currentPositionModel the piece coords
     * @param finalPositionModel   the final correct piece coords
     */
    public PieceOfImageModel(PositionModel currentPositionModel, PositionModel finalPositionModel) {
        super(currentPositionModel, finalPositionModel, true);
        this.value = null;
    }

    /**
     * Returns piece width
     *
     * @return Returns piece width
     */
    @Override
    public int getPieceWidth() {
        return value.getWidth();
    }

    /**
     * Returns piece height
     *
     * @return Returns piece height
     */
    @Override
    public int getPieceHeight() {
        return value.getHeight();
    }

    /**
     * This method define how a piece draw itself
     *
     * @param g Graphic used for drawing
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(value, getCurrentPosition().getPositionX() * value.getWidth(), getCurrentPosition().getPositionY() * value.getHeight(), null);
        g.setColor(Color.BLACK);
        g.drawRect(getCurrentPosition().getPositionX() * value.getWidth(), getCurrentPosition().getPositionY() * value.getHeight(), value.getWidth(), value.getHeight());
    }

    /**
     * Returns this piece as String
     *
     * @return Returns this piece as String
     */
    @Override
    public String toString() {
        return "(" + this.getCurrentPosition().getPositionX() + ":" + this.getCurrentPosition().getPositionY() + ")";
    }

}
