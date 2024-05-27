package main.view;

import main.controller.SwingPuzzleController;
import main.models.mouse.MousePositionModel;
import main.models.puzzle.PuzzleModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Main JFrame
 */
public class MainView extends JFrame implements ActionListener {
    private JButton classicPuzzleButton;
    private JButton imagePuzzleButton;
    private JButton quitButton;
    private JPanel mainPanel;
    private PuzzleModel puzzleModel;


    public MainView() {
        super("Puzzle à glissières");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        //Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Menu panel
        JPanel menuPanel = new JPanel();

        classicPuzzleButton = new JButton("Classic puzzle");
        classicPuzzleButton.setBackground(Color.WHITE);
        imagePuzzleButton = new JButton("Image puzzle");
        imagePuzzleButton.setBackground(Color.WHITE);
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.WHITE);
        classicPuzzleButton.addActionListener(this);
        imagePuzzleButton.addActionListener(this);
        quitButton.addActionListener(this);

        try {
            //Load the logo
            BufferedImage myPicture = ImageIO.read(getClass().getResourceAsStream("/logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            mainPanel.add(picLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Add buttons to the menu
        menuPanel.add(classicPuzzleButton);
        menuPanel.add(imagePuzzleButton);
        menuPanel.add(quitButton);

        //Add menu to main panel
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        //Initiate the JFrame
        this.buildFrame();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Check if the quit button is clicked
        if (e.getSource() == quitButton) {
            //Quit
            this.dispose();
            System.exit(0);
        }
        //Check which button is clicked
        buildPuzzle(e.getSource());
    }

    /**
     * Build the frame with the menu panel
     */
    public void buildFrame() {
        this.getContentPane().removeAll();
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(512, 250));
        this.repaint();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    /**
     * Check which button is clicked and response to it
     *
     * @param o JButton
     */
    private void buildPuzzle(Object o) {

        //Classical puzzle
        if (o == classicPuzzleButton) {
            try {
                //Clear content pane
                this.getContentPane().removeAll();

                //Ask user dimension of the puzzle
                String response = JOptionPane.showInputDialog("Veuillez choisir la taille du puzzle (Vide == puzzle 4*4)", "");

                if (!response.isBlank() || !response.isEmpty()) {
                    int value = Integer.parseInt(response);
                    if (value < 0 || value > 8) {
                        JOptionPane.showMessageDialog(null, "La taille est très grande!", "Erreur", JOptionPane.ERROR_MESSAGE);
                        buildFrame();
                        return;
                    }
                    puzzleModel = new PuzzleModel(value, value);
                } else {
                    puzzleModel = new PuzzleModel();
                }
                MousePositionModel mousePositionModel = new MousePositionModel();
                SwingPuzzleController swingPuzzleController = new SwingPuzzleController(puzzleModel, mousePositionModel);
                PuzzleView puzzleView = new PuzzleView(this, puzzleModel, mousePositionModel);

                puzzleView.addMouseListener(swingPuzzleController);
                puzzleView.addMouseMotionListener(swingPuzzleController);
                puzzleView.addKeyListener(swingPuzzleController);

                this.getContentPane().add(puzzleView);
                this.setPreferredSize(this.getContentPane().getComponent(0).getPreferredSize());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                this.buildFrame();

            }
        }

        //Customized image puzzle
        if (o == imagePuzzleButton) {

            //Open file chooser
            JFileChooser chooser = new JFileChooser();

            //Apply a filter for the file chooser
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "jpeg");
            chooser.setFileFilter(filter);

            //Check response of the fileChooser
            int response = chooser.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    //Clear content pane
                    this.getContentPane().removeAll();

                    //Open selected image
                    BufferedImage image = ImageIO.read(chooser.getSelectedFile());

                    //Initiate the puzzle by calling specific image constructor
                    puzzleModel = new PuzzleModel(image);

                    //Initiate Controller & View & mouse position model
                    MousePositionModel mousePositionModel = new MousePositionModel();
                    SwingPuzzleController swingPuzzleController = new SwingPuzzleController(puzzleModel, mousePositionModel);
                    PuzzleView puzzleView = new PuzzleView(this, puzzleModel, mousePositionModel);

                    puzzleView.addMouseListener(swingPuzzleController);
                    puzzleView.addMouseMotionListener(swingPuzzleController);
                    puzzleView.addKeyListener(swingPuzzleController);

                    //Add the view to the content pane
                    this.getContentPane().add(puzzleView);

                    //Redefine the size
                    this.setPreferredSize(this.getContentPane().getComponent(0).getPreferredSize());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    this.buildFrame();
                }
            }
        }

        this.repaint();
        this.pack();
    }
}
