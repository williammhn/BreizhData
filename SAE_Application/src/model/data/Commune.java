package model.data;

import java.util.ArrayList;

/**
 * Represents a commune with its id and name.
 */
public class Commune {

    /**
     * The id of the commune.
     */
    private int idCommune;

    /**
     * The name of the commune.
     */
    private String nomCommune;

    /**
     * The department of the commune.
     */
    private Departement leDepartement;

    /**
     * List of train stations in the commune.
     */
    private ArrayList<Gare> lesGares;

    /**
     * List of neighboring communes.
     */
    private ArrayList<Commune> communesVoisines;

    //============================================ Constructor ============================================//
    
    /**
     * Constructor for the Commune.
     * 
     * @param id  the id of the commune
     * @param nom the name of the commune
     * @param departement the department of the commune
     * @throws IllegalArgumentException if the id is less than 0 or if the name or department is null
     */
    public Commune(int id, String nom, Departement departement) throws IllegalArgumentException {
        if (id >= 0 && nom != null && departement != null) {
            this.idCommune = id;
            this.nomCommune = nom;
            this.leDepartement = departement;
            this.lesGares = new ArrayList<>();
            this.communesVoisines = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("Commune constructor: one of the parameters is null or id is negative");
        }
    }

    //============================================ Methods ============================================//

    /**
     * Adds a neighboring commune.
     * 
     * @param voisin the neighboring commune to add
     * @throws IllegalArgumentException if the neighboring commune is null
     */
    public void addVoisine(Commune voisin) throws IllegalArgumentException {
        if (voisin == null) {
            throw new IllegalArgumentException("addVoisine: The neighboring commune is null");
        }
        this.communesVoisines.add(voisin);
        voisin.getCommunesVoisines().add(this);
    }

    /**
     * Adds a train station to the commune.
     * 
     * @param gare the train station to add
     * @throws IllegalArgumentException if the train station is null
     */
    public void addGare(Gare gare) throws IllegalArgumentException {
        if (gare == null) {
            throw new IllegalArgumentException("addGare: The train station is null");
        }
        this.lesGares.add(gare);
    }
    
    /**
     * Returns the list of names of neighboring communes.
     * 
     * @return a list of names of neighboring communes
     */
    public ArrayList<String> listerCommunesVoisines() {
        ArrayList<String> nomsVoisines = new ArrayList<>();
        for (Commune voisine : this.communesVoisines) {
            nomsVoisines.add(voisine.getNomCommune());
        }
        return nomsVoisines;
    }

    /**
     * Returns the list of names of train stations.
     * 
     * @return a list of names of train stations
     */
    public ArrayList<String> listerGares() {
        ArrayList<String> nomsGares = new ArrayList<>();
        for (Gare gare : this.lesGares) {
            nomsGares.add(gare.getNomGare());
        }
        return nomsGares;
    }

    /**
     * Checks if a commune is a neighbor.
     * 
     * @param voisine the commune to check
     * @return true if the commune is a neighbor, false otherwise
     * @throws IllegalArgumentException if the commune to check is null
     */
    public boolean estVoisine(Commune voisine) throws IllegalArgumentException {
        if (voisine == null) {
            throw new IllegalArgumentException("estVoisine: The commune to check is null");
        }
        return this.communesVoisines.contains(voisine);
    }

    /**
     * Returns the number of neighboring communes.
     * 
     * @return the number of neighboring communes
     */
    public int nombreCommunesVoisines() {
        return this.communesVoisines.size();
    }

    /**
     * Returns the number of train stations.
     * 
     * @return the number of train stations
     */
    public int nombreGares() {
        return this.lesGares.size();
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the id of the commune.
     * 
     * @return the id of the commune
     */
    public int getIdCommune() {
        return this.idCommune;
    }

    /**
     * Gets the name of the commune.
     * 
     * @return the name of the commune
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Gets the list of train stations.
     * 
     * @return the list of train stations
     */
    public ArrayList<Gare> getLesGares() {
        return lesGares;
    }

    /**
     * Gets the list of neighboring communes.
     * 
     * @return the list of neighboring communes
     */
    public ArrayList<Commune> getCommunesVoisines() {
        return communesVoisines;
    }

    /**
     * Gets the department of the commune.
     * 
     * @return the department of the commune
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Sets the id of the commune.
     * 
     * @param commune the new id of the commune
     * @throws IllegalArgumentException if the id is less than 0
     */
    public void setIdCommune(int commune) throws IllegalArgumentException {
        if (commune < 0) {
            throw new IllegalArgumentException("setIdCommune: The id of the commune is incorrect");
        }
        this.idCommune = commune;
    }

    /**
     * Sets the name of the commune.
     * 
     * @param nom the new name of the commune
     * @throws IllegalArgumentException if the name is null
     */
    public void setNomCommune(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("setNomCommune: The name of the commune is null");
        }
        this.nomCommune = nom;
    }

    /**
     * Sets the department of the commune.
     * 
     * @param departement the new department of the commune
     * @throws IllegalArgumentException if the department is null
     */
    public void setLeDepartement(Departement departement) throws IllegalArgumentException {
        if (departement == null) {
            throw new IllegalArgumentException("setLeDepartement: The department of the commune is null");
        }
        this.leDepartement = departement;
    }

    /**
     * Returns a string representation of idCommune and nomCommune.
     * 
     * @return a string representation of idCommune and nomCommune
     */
    public String toString() {
        String idCommune = "idCommune: " + this.idCommune;
        String nomCommune = "nomCommune: " + this.nomCommune;

        return idCommune + "\n" + nomCommune + "\n";
    }
}
