package model.data;

import java.util.ArrayList;

/**
 * Represents an airport with a name and an address.
 */
public class Aeroport {

    /**
     * The name of the airport.
     */
    private String nom;

    /**
     * The address of the airport.
     */
    private String adresse;

    /**
     * The department where the airport is located.
     */
    private Departement leDepartement;

    //============================================ Constructor ============================================//

    /**
     * Constructor with parameters that initializes the airport with the given name and address.
     *
     * @param nom the name of the airport
     * @param adresse the address of the airport
     * @param leDepartement the department where the airport is located
     * @throws IllegalArgumentException if the name or address is null
     */
    public Aeroport(String nom, String adresse, Departement leDepartement) throws IllegalArgumentException {
        if (nom != null) {
            if (adresse != null) {
                this.nom = nom;
                this.adresse = adresse;
                this.leDepartement = leDepartement;
            } else {
                throw new IllegalArgumentException("Aeroport constructor: The address of the airport is null");
            }
        } else {
            throw new IllegalArgumentException("Aeroport constructor: The name of the airport is null");
        }
    }

    //============================================ Methods ============================================//

    /**
     * Method that returns the commune to which the airport belongs based on its address.
     * @param communes the list of available communes
     * @return the commune to which the airport belongs, or null if no match is found
     * @throws IllegalArgumentException if the list of communes is null
     */
    public Commune findCommune(ArrayList<Commune> communes) throws IllegalArgumentException {
        if (communes == null) {
            throw new IllegalArgumentException("findCommune: The list of communes is null");
        }
        Commune res = null;
        for (Commune commune : communes) {
            if (this.adresse.contains(commune.getNomCommune())) {
                res = commune;
            }
        }
        return res;
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the name of the airport.
     *
     * @return the name of the airport
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Gets the address of the airport.
     *
     * @return the address of the airport
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Gets the department where the airport is located.
     *
     * @return the department where the airport is located
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Sets the name of the airport.
     *
     * @param nom the new name of the airport
     * @throws IllegalArgumentException if the name is null
     */
    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("setNom: The name of the airport is null");
        }
        this.nom = nom;
    }

    /**
     * Sets the address of the airport.
     *
     * @param adresse the new address of the airport
     * @throws IllegalArgumentException if the address is null
     */
    public void setAdresse(String adresse) throws IllegalArgumentException {
        if (adresse == null) {
            throw new IllegalArgumentException("setAdresse: The address of the airport is null");
        }
        this.adresse = adresse;
    }

    /**
     * Sets the department where the airport is located.
     *
     * @param leDepartement the new department where the airport is located
     * @throws IllegalArgumentException if the department is null
     */
    public void setLeDepartement(Departement leDepartement) throws IllegalArgumentException {
        if (leDepartement == null) {
            throw new IllegalArgumentException("setLeDepartement: The department is null");
        }
        this.leDepartement = leDepartement;
    }

    /**
     * Returns a string representation of the airport.
     *
     * @return a string representation of the airport
     */
    public String toString() {
        String nom = "Name: " + this.nom;
        String adresse = "Address: " + this.adresse;
        return nom + "\n" + adresse + "\n";
    }
}
