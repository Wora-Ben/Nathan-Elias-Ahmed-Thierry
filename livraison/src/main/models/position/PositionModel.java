package main.models.position;

/**
 * This class represent a coords in a matrix
 */
public class PositionModel {
    /**
     * x position
     */
    private int positionX;

    /**
     * y position
     */
    private int positionY;

    /**
     * Constructor
     *
     * @param positionX x position
     * @param positionY y position
     */
    public PositionModel(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Returns x position
     *
     * @return Returns x position
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Returns y position
     *
     * @return Returns y position
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Change x position
     *
     * @param positionX x position
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Change y position
     *
     * @param positionY y position
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Returns coords as String
     *
     * @return Returns coords as String
     */
    @Override
    public String toString() {
        return "(" + positionX + "," + positionY + ")";
    }

    /**
     * Check if two positions are equal
     *
     * @param o second Position
     * @return two positions are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof PositionModel) {
            PositionModel tmpO = (PositionModel) o;
            return (tmpO.positionX == this.positionX) && (tmpO.positionY == this.positionY);
        }

        return false;
    }
}
