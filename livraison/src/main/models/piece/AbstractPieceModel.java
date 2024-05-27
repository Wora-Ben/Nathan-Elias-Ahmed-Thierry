package main.models.piece;

import main.models.position.PositionModel;

/**
 * Class of an abstract piece of puzzle
 */
public abstract class AbstractPieceModel implements Piece {
    /**
     * Current piece coords
     */
    private PositionModel currentPosition;

    /**
     * Piece the correct final coords
     */
    private PositionModel finalPosition;

    /**
     * Default puzzle piece size
     */
    protected static int DEFAULT_PUZZLE_PIECE_SIZE = 150;

    /**
     * If the piece is the moving piece or not
     */
    private boolean movingPiece;

    /**
     * Constructor
     *
     * @param currentPosition current piece coords
     * @param movingPiece     if the piece is the moving piece
     */
    public AbstractPieceModel(PositionModel currentPosition, PositionModel finalPositionModel, boolean movingPiece) {
        this.currentPosition = currentPosition;
        this.finalPosition = finalPositionModel;
        this.movingPiece = movingPiece;
    }

    /**
     * Returns current piece coords
     *
     * @return Returns current piece coords
     */
    @Override
    public PositionModel getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Return if the currentPosition == finalPosition, "This is at the correct place"
     *
     * @return Return boolean value indicates if this piece position coords  == this piece final position
     */
    @Override
    public boolean isTheFinalPosition() {
        return currentPosition.equals(finalPosition);
    }

    /**
     * Return if this piece is the moving piece
     *
     * @return Return if this piece is the moving piece
     */
    @Override
    public boolean isMovingPiece() {
        return movingPiece;
    }

    /**
     * Returns piece final coords
     *
     * @return Returns piece final coords
     */
    public PositionModel getFinalPosition() {
        return finalPosition;
    }

    /**
     * Change the current piece coords
     *
     * @param positionModel new piece coords
     */
    @Override
    public void setNewPosition(PositionModel positionModel) {
        currentPosition.setPositionX(positionModel.getPositionX());
        currentPosition.setPositionY(positionModel.getPositionY());
    }

    /**
     * Returns piece width
     *
     * @return Returns piece width
     */
    @Override
    public int getPieceWidth() {
        return DEFAULT_PUZZLE_PIECE_SIZE;
    }

    /**
     * Returns piece height
     *
     * @return Returns piece height
     */
    @Override
    public int getPieceHeight() {
        return DEFAULT_PUZZLE_PIECE_SIZE;
    }
}
