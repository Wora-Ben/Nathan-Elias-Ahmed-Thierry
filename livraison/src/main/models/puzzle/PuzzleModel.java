package main.models.puzzle;

import main.models.AbstractModeleEcoutable;
import main.models.EcouteurModele;
import main.models.piece.NumericPieceModel;
import main.models.piece.Piece;
import main.models.piece.PieceOfImageModel;
import main.models.position.PositionModel;

import java.awt.image.BufferedImage;

/**
 * This class represents a puzzle
 */
public class PuzzleModel extends AbstractModeleEcoutable {

    /**
     * Pieces of the puzzle
     */
    private Piece pieces[][];

    /**
     * Number of moves made in puzzle
     */
    private int nbMouves;

    /**
     * Reference at the moving piece
     */
    private Piece movingPiece;

    private static final int[] MOVE_X = {0, 0, -1, 1};
    private static final int[] MOVE_Y = {-1, 1, 0, 0};

    /**
     * Default number of image pieces n * n dimension
     */
    private static final int DEFAULT_IMAGE_NB_PIECES = 4;

    /**
     * Number of height pieces
     */
    private int puzzlePieceHeight;

    /**
     * Number of width pieces
     */
    private int puzzlePieceWidth;

    /**
     * Constructor for classic numeric puzzle
     *
     * @param width  number of pieces at width
     * @param height number of pieces at height
     */
    public PuzzleModel(int width, int height) {
        super();
        pieces = new NumericPieceModel[height][width];
        int k = 1;
        // Initiate organized puzzle
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                pieces[j][i] = new NumericPieceModel(k++, new PositionModel(i, j), new PositionModel(i, j), false);
                if (j == height - 1 && i == width - 1) {
                    // Define the "hole piece" the moving piece at the last piece of puzzle
                    movingPiece = new NumericPieceModel(new PositionModel(i, j), new PositionModel(i, j));
                    pieces[height - 1][width - 1] = movingPiece;
                }
            }
        }
        puzzlePieceWidth = pieces[0][0].getPieceWidth();
        puzzlePieceHeight = pieces[0][0].getPieceHeight();
        randomizePuzzle();
    }

    /**
     * Default constructor which initiate a puzzle of 4*4 pieces
     */
    public PuzzleModel() {
        this(4, 4);
    }

    /***
     * Constructor for a image puzzle
     * @param image image of the puzzle
     */
    public PuzzleModel(BufferedImage image) {
        super();
        pieces = new Piece[DEFAULT_IMAGE_NB_PIECES][DEFAULT_IMAGE_NB_PIECES];

        //Find image piece width and height
        double kWidth = (double) image.getWidth() / DEFAULT_IMAGE_NB_PIECES;
        double kHeight = (double) image.getHeight() / DEFAULT_IMAGE_NB_PIECES;

        this.puzzlePieceWidth = (int) Math.round(kWidth);
        this.puzzlePieceHeight = (int) Math.round(kHeight);

        // Initiate organized puzzle
        for (int i = 0; i < DEFAULT_IMAGE_NB_PIECES; i++) {
            for (int j = 0; j < DEFAULT_IMAGE_NB_PIECES; j++) {
                pieces[i][j] = new PieceOfImageModel(image.getSubimage(j * this.puzzlePieceWidth, i * this.getPuzzlePieceHeight(), this.puzzlePieceWidth, this.puzzlePieceHeight), new PositionModel(j, i), new PositionModel(j, i), false);
                if (j == DEFAULT_IMAGE_NB_PIECES - 1 && i == DEFAULT_IMAGE_NB_PIECES - 1) {
                    // Define "the hole piece" the moving piece at the last piece of puzzle
                    movingPiece = new PieceOfImageModel(new PositionModel(j, i), new PositionModel(j, i));
                    pieces[i][j] = movingPiece;
                }
            }
        }
        randomizePuzzle();
    }

    /**
     * Function that moves the moving piece on a specific direction
     * if the number is 0 : Moving up
     * if the number is 1 : Moving down
     * if the number is 2 : Moving left
     * if the number is 3 : Moving right
     *
     * @return if the hole moved successfully
     */
    public boolean move(int direction) {
        int movingPieceX = movingPiece.getCurrentPosition().getPositionX();
        int movingPieceY = movingPiece.getCurrentPosition().getPositionY();

        movingPieceX += MOVE_X[direction];
        movingPieceY += MOVE_Y[direction];

        if ((movingPieceX < 0) || (movingPieceY < 0) || (movingPieceX >= pieces.length) || (movingPieceY >= pieces[0].length)) {
            return false;
        }

        swapPosition(movingPiece, pieces[movingPieceY][movingPieceX]);
        nbMouves++;
        fireChanges();
        return true;
    }

    /**
     * Randomize the puzzle 2^(2*n*m) move
     */
    private void randomizePuzzle() {
        int i = 0;
        while (i < (2 ^ (2 * getWidth() * getHeight()))) {
            if (moveRandomly()) {
                i++;
            }
        }
        nbMouves = 0;
    }

    /**
     * Function that moves hole "moving piece" on a random way *
     *
     * @return if the hole moved successfully
     */
    private boolean moveRandomly() {
        int k = (int) (Math.random() * ((3 - 0) + 1));
        return move(k);
    }


    /**
     * Swap to pieces position
     *
     * @param firstPiece  First piece of swap
     * @param secondPiece Second piece of swap
     */
    public void swapPosition(Piece firstPiece, Piece secondPiece) {
        PositionModel firstPositionModel = new PositionModel(firstPiece.getCurrentPosition().getPositionX(),
                firstPiece.getCurrentPosition().getPositionY());
        PositionModel secondPositionModel = new PositionModel(secondPiece.getCurrentPosition().getPositionX(),
                secondPiece.getCurrentPosition().getPositionY());

        pieces[firstPositionModel.getPositionY()][firstPositionModel.getPositionX()] = secondPiece;
        secondPiece.setNewPosition(firstPositionModel);
        pieces[secondPositionModel.getPositionY()][secondPositionModel.getPositionX()] = firstPiece;
        firstPiece.setNewPosition(secondPositionModel);

    }

    /**
     * Returns piece height
     *
     * @return Returns piece height
     */
    public int getPuzzlePieceHeight() {
        return puzzlePieceHeight;
    }

    /**
     * Returns piece width
     *
     * @return Returns piece width
     */
    public int getPuzzlePieceWidth() {
        return puzzlePieceWidth;
    }

    /**
     * Returns puzzle as a String
     *
     * @return Returns puzzle as a String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                str.append(" ").append(pieces[i][j].toString()).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * Returns number of moves
     *
     * @return Returns number of moves
     */
    public int getNbMoves() {
        return nbMouves;
    }

    /**
     * Return pieces of the puzzle
     *
     * @return Return pieces of the puzzle as an array of Piece
     */
    public Piece[][] getPieces() {
        return pieces;
    }

    /**
     * Returns number of height pieces
     *
     * @return Returns number of height pieces
     */

    public int getHeight() {
        return pieces.length;
    }

    /**
     * Returns number of width pieces
     *
     * @return Returns number of width pieces
     */
    public int getWidth() {
        return pieces[0].length;
    }

    /**
     * Returns the moving piece
     *
     * @return the moving piece
     */
    public Piece getMovingPiece() {
        return movingPiece;
    }

    protected void fireChanges() {
        for (EcouteurModele l : super.ecouteursModele) {
            l.update();
        }
    }

    /**
     * Check if coords(xPos,yPos) are connected to the moving piece
     *
     * @param xPos x position
     * @param yPos y position
     * @return if coords are connected to the moving piece
     */
    public boolean nearMovingPiece(int xPos, int yPos) {
        PositionModel movingPiecePosition = movingPiece.getCurrentPosition();
        return ((xPos == movingPiecePosition.getPositionX() || yPos == movingPiecePosition.getPositionY()) && ((yPos == movingPiecePosition.getPositionY() - 1 || yPos == movingPiecePosition.getPositionY() + 1) || (xPos == movingPiecePosition.getPositionX() - 1 || xPos == movingPiecePosition.getPositionX() + 1)));
    }

    /**
     * Returns true if all pieces of the puzzle are in their final position, otherwise false
     *
     * @return Returns true if all pieces of the puzzle are in their final position, otherwise false
     */
    public boolean isPuzzleSolved() {
        int nbCorrectPieces = 0;

        for (int lignes = 0; lignes < getHeight(); lignes++) {
            for (int colonnes = 0; colonnes < getWidth(); colonnes++) {
                if (!pieces[lignes][colonnes].isTheFinalPosition()) {
                    return false;
                }
                nbCorrectPieces += 1;
            }
        }
        return nbCorrectPieces == getHeight() * getWidth();
    }


}
