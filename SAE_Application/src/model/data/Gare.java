package model.data;

import java.util.ArrayList;

/**
 * Represents a train station by its code, name, and whether it is a freight or passenger station.
 */
public class Gare {

    /**
     * Code of the train station.
     */
    private int codeGare;

    /**
     * Name of the train station.
     */
    private String nomGare;

    /**
     * Indicates if the station is a freight station.
     */
    private boolean estFret;

    /**
     * Indicates if the station is a passenger station.
     */
    private boolean estVoyageur;

    /**
     * The commune for the station.
     */
    private Commune laCommune;

    //============================================ Constructor ============================================//

    /**
     * Constructor for the Gare.
     *
     * @param code      the code for the station
     * @param nom       the name of the station
     * @param fret      whether the station is a freight station
     * @param voyageur  whether the station is a passenger station
     * @param commune   the commune for the station
     * @throws IllegalArgumentException if the code is less than 0 or if the name or commune is null
     */
    public Gare(int code, String nom, boolean fret, boolean voyageur, Commune commune) throws IllegalArgumentException {
        if (code >= 0) {
            if (nom != null) {
                if (commune != null) {
                    this.codeGare = code;
                    this.nomGare = nom;
                    this.estFret = fret;
                    this.estVoyageur = voyageur;
                    this.laCommune = commune;
                } else {
                    throw new IllegalArgumentException("Gare constructor: The commune is null");
                }
            } else {
                throw new IllegalArgumentException("Gare constructor: The name of the station is null");
            }
        } else {
            throw new IllegalArgumentException("Gare constructor: The code of the station is incorrect");
        }
    }

    //============================================ Methods ============================================//

    /**
     * Counts the number of stations in a list that have the same freight and passenger characteristics.
     *
     * @param gares the list of stations to analyze
     * @return the number of stations with the same characteristics
     * @throws IllegalArgumentException if the list of stations is null
     */
    public int compterGaresSimilaires(ArrayList<Gare> gares) throws IllegalArgumentException {
        if (gares == null) {
            throw new IllegalArgumentException("compterGaresSimilaires: The list of stations is null");
        }
        int cpt = 0;
        for (Gare gare : gares) {
            if (gare.getEstFret() == this.estFret && gare.getEstVoyageur() == this.estVoyageur) {
                cpt++;
            }
        }
        return cpt;
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the code of the station.
     *
     * @return the code of the station
     */
    public int getCodeGare() {
        return this.codeGare;
    }

    /**
     * Gets the name of the station.
     *
     * @return the name of the station
     */
    public String getNomGare() {
        return this.nomGare;
    }

    /**
     * Indicates if the station is a freight station.
     *
     * @return true if the station is a freight station, false otherwise
     */
    public boolean getEstFret() {
        return this.estFret;
    }

    /**
     * Indicates if the station is a passenger station.
     *
     * @return true if the station is a passenger station, false otherwise
     */
    public boolean getEstVoyageur() {
        return this.estVoyageur;
    }

    /**
     * Gets the commune of the station.
     *
     * @return the commune of the station
     */
    public Commune getLaCommune() {
        return this.laCommune;
    }

    /**
     * Sets the code of the station.
     *
     * @param code the new code of the station
     * @throws IllegalArgumentException if the code is less than 0
     */
    public void setCodeGare(int code) throws IllegalArgumentException {
        if (code < 0) {
            throw new IllegalArgumentException("setCodeGare: The code of the station is incorrect");
        }
        this.codeGare = code;
    }

    /**
     * Sets the name of the station.
     *
     * @param nom the new name of the station
     * @throws IllegalArgumentException if the name is null
     */
    public void setNomGare(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("setNomGare: The name of the station is null");
        }
        this.nomGare = nom;
    }

    /**
     * Sets whether the station is a freight station.
     *
     * @param estFret the new freight status of the station
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Sets whether the station is a passenger station.
     *
     * @param estVoyageur the new passenger status of the station
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Sets the commune of the station.
     *
     * @param laCommune the new commune of the station
     * @throws IllegalArgumentException if the commune is null
     */
    public void setLaCommune(Commune laCommune) throws IllegalArgumentException {
        if (laCommune == null) {
            throw new IllegalArgumentException("setLaCommune: The commune is null");
        }
        this.laCommune = laCommune;
    }

    /**
     * Returns a string representation of the station.
     *
     * @return a string representation of the station
     */
    public String toString() {

        String code = "Le code de la gare est: " + this.codeGare;
        String nom = "Le nom de la gare est: " + this.nomGare;
        String estFret = "La gare est commerciale " + this.estFret;
        String estVoyageur = "La gare est transporte des voyageurs " + this.estVoyageur;

        return "\n" + code + "\n" + nom + "\n" + estFret + "\n" + estVoyageur;
    }
}
