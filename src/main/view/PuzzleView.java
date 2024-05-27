package main.view;

import javax.swing.*;
import java.awt.*;

import main.models.mouse.MousePositionModel;
import main.models.piece.Piece;
import main.models.puzzle.PuzzleModel;
import main.models.EcouteurModele;

/**
 * Main puzzle viewer
 */
public class PuzzleView extends JPanel implements EcouteurModele {
    private PuzzleModel puzzleModel;
    private MousePositionModel mousePositionModel;
    private JLabel numberOfMovementsTextField;
    private MainView mainView;
    private JButton quitButton;

    /**
     * Constructor of the puzzle viewer
     *
     * @param mainView           parent Component
     * @param puzzleModel        puzzle
     * @param mousePositionModel mouse position
     */
    public PuzzleView(MainView mainView, PuzzleModel puzzleModel, MousePositionModel mousePositionModel) {
        super();
        this.mainView = mainView;
        this.setLayout(new BorderLayout());
        this.puzzleModel = puzzleModel;
        this.mousePositionModel = mousePositionModel;
        this.setFocusable(true);
        puzzleModel.addListener(this);
        mousePositionModel.addListener(this);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.addActionListener(e -> {
            mainView.buildFrame();
        });
        numberOfMovementsTextField = new JLabel("Nombre de coups = " + puzzleModel.getNbMoves());
        bottomPanel.add(quitButton);
        bottomPanel.add(numberOfMovementsTextField);


        this.add(bottomPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(puzzleModel.getWidth() * puzzleModel.getPuzzlePieceWidth(), puzzleModel.getHeight() * puzzleModel.getPuzzlePieceHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setFocusable(true);
        requestFocus();

        Piece[][] pieces = puzzleModel.getPieces();
        int pieceHeight = puzzleModel.getPuzzlePieceHeight();
        int pieceWidth = puzzleModel.getPuzzlePieceWidth();
        int mouseXPos = mousePositionModel.getMouseXPos();
        int mouseYPos = mousePositionModel.getMouseYPos();

        for (int lignes = 0; lignes < puzzleModel.getHeight(); lignes++) {
            for (int colonnes = 0; colonnes < puzzleModel.getWidth(); colonnes++) {
                int pieceXCoords = colonnes * pieceWidth;
                int pieceYCoords = lignes * pieceHeight;

                if (pieces[lignes][colonnes].isMovingPiece()) {
                    g.setColor(Color.BLACK);
                    g.fillRect(pieceXCoords, pieceYCoords, pieceWidth, pieceHeight);
                }

                if (!pieces[lignes][colonnes].isMovingPiece()) {
                    pieces[lignes][colonnes].draw(g);
                }

                if ((mouseXPos >= pieceXCoords && mouseXPos <= (pieceXCoords + pieceWidth)) && (mouseYPos >= pieceYCoords && mouseYPos <= pieceYCoords + pieceHeight) && puzzleModel.nearMovingPiece(colonnes, lignes)) {
                    if (!pieces[lignes][colonnes].isMovingPiece()) {
                        g.setColor(Color.GREEN);
                    }
                    g.fillRect(pieceXCoords + 1, pieceYCoords + 1, pieceWidth, pieceHeight);
                    g.setColor(Color.BLACK);
                }
            }
        }
        numberOfMovementsTextField.setText("Nombre de coups = " + puzzleModel.getNbMoves());

        //Check if the puzzle is solved
        if (puzzleModel.isPuzzleSolved()) {
            JOptionPane.showMessageDialog(null, "Congrats !", "Puzzle resolved", JOptionPane.INFORMATION_MESSAGE);
            mainView.buildFrame();
        }
    }

    @Override
    public void update() {
        repaint();
    }


}
