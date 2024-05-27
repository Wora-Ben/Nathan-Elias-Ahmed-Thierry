package main.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represent an abstract listenable model
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {
    /**
     * List of listeners
     */
    protected List<EcouteurModele> ecouteursModele;

    /**
     * Constructor
     */
    public AbstractModeleEcoutable() {
        ecouteursModele = new ArrayList<>();
    }

    /**
     * Add new listener to listeners list
     *
     * @param ecouteurModele the new listener
     */
    public void addListener(EcouteurModele ecouteurModele) {
        ecouteursModele.add(ecouteurModele);
    }

    /**
     * Remove from listeners list a listener
     *
     * @param ecouteurModele the listener to remove from listeners list
     */
    public void removeListener(EcouteurModele ecouteurModele) {
        ecouteursModele.remove(ecouteurModele);
    }

    protected abstract void fireChanges();
}
