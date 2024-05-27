package main.models.mouse;

import main.models.AbstractModeleEcoutable;
import main.models.EcouteurModele;
import main.models.position.PositionModel;


/**
 * Class to keep track of mouse coords
 */
public class MousePositionModel extends AbstractModeleEcoutable {
    /**
     * Current mouse position
     */
    private PositionModel mousePos;


    /**
     * Constructor that initialise the mouse coords at (-1,-1)
     */
    public MousePositionModel() {
        super();
        mousePos = new PositionModel(-1, -1);
    }

    /**
     * This register the mouse current x and y coords
     *
     * @param x x coords position
     * @param y y coords position
     */
    public void mouseMoved(int x, int y) {
        this.mousePos.setPositionX(x);
        this.mousePos.setPositionY(y);
        fireChanges();
    }

    /**
     * Return the x position of the mouse
     *
     * @return x position of the mouse
     */
    public int getMouseXPos() {
        return mousePos.getPositionX();
    }


    /**
     * Return the y position of the mouse
     *
     * @return y position of the mouse
     */
    public int getMouseYPos() {
        return mousePos.getPositionY();
    }

    /**
     * This method call all listeners Listener.update()
     */
    @Override
    protected void fireChanges() {
        for (EcouteurModele l : super.ecouteursModele) {
            l.update();
        }

    }
}
