package main.controller;

import main.models.puzzle.PuzzleModel;
import main.models.mouse.MousePositionModel;
import main.models.position.PositionModel;

import java.awt.event.*;

/**
 * Puzzle controller
 */
public class SwingPuzzleController implements KeyListener, MouseMotionListener, MouseListener {
    /**
     * reference of puzzle model
     */
    private PuzzleModel puzzleModel;
    /**
     * reference of mouse position
     */
    private MousePositionModel mousePositionModel;

    /**
     * Constructor of
     *
     * @param puzzleModel        puzzle model
     * @param mousePositionModel mouse position model
     */
    public SwingPuzzleController(PuzzleModel puzzleModel, MousePositionModel mousePositionModel) {
        this.puzzleModel = puzzleModel;
        this.mousePositionModel = mousePositionModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Check which key is pressed
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                puzzleModel.move(0);
                printModel();
                break;
            case KeyEvent.VK_S:
                puzzleModel.move(1);
                printModel();
                break;
            case KeyEvent.VK_D:
                puzzleModel.move(3);
                printModel();
                break;
            case KeyEvent.VK_Q:
                puzzleModel.move(2);
                printModel();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Save current mouse position
        mousePositionModel.mouseMoved(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Check if this position is near the moving piece & the mouse left button is clicked
        if (puzzleModel.nearMovingPiece(e.getX() / puzzleModel.getPuzzlePieceWidth(), e.getY() / puzzleModel.getPuzzlePieceHeight()) && e.getButton() == MouseEvent.BUTTON1) {
            //Get the current moving piece coords
            PositionModel movingPiecePos = puzzleModel.getMovingPiece().getCurrentPosition();
            //Get the piece dimension
            int pieceWidth = puzzleModel.getPuzzlePieceWidth();
            int pieceHeight = puzzleModel.getPuzzlePieceHeight();

            //Check if the column or the row piece
            if (movingPiecePos.getPositionX() == e.getX() / pieceWidth) {
                //check whether the piece is the one above or the one below
                puzzleModel.move((e.getY() / pieceHeight - movingPiecePos.getPositionY()) < 0 ? 0 : 1);
            } else {
                //check whether the piece is the one on the left or the one on the right
                puzzleModel.move((e.getX() / pieceWidth - movingPiecePos.getPositionX()) < 0 ? 2 : 3);
            }
            printModel();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Print model on the console
     */
    private void printModel() {
        System.out.println(puzzleModel);
    }
}
