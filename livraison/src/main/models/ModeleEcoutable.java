package main.models;

/**
 * This interface represent a listenable model
 */
public interface ModeleEcoutable {

    /**
     * Add new listener
     *
     * @param ecouteurModele the new listener
     */
    public void addListener(EcouteurModele ecouteurModele);

    /**
     * Remove from listeners list a listener
     *
     * @param ecouteurModele listener to be removed
     */
    public void removeListener(EcouteurModele ecouteurModele);
}