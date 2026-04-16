package model.data;

import java.util.ArrayList;

/**
 * Represents a department with its id, name, and cultural investment in 2019.
 */
public class Departement {

    /**
     * The id of the department.
     */
    private int idDep;

    /**
     * The name of the department.
     */
    private String nomDep;

    /**
     * The cultural investment for the year 2019.
     */
    private float invesCulturel2019;

    /**
     * List of airports in the department.
     */
    private ArrayList<Aeroport> lesAeroports;

    /**
     * List of the valid names of a department.
     */
    private final String[] lesDepsValides = {"MORBIHAN", "ILLE-ET-VILAINE", "COTES-D'ARMOR", "FINISTERE"};


    //============================================ Constructor ============================================//

    /**
     * Constructor for the Department.
     *
     * @param id    the id of the department
     * @param nom   the name of the department
     * @param inves the cultural investment for the year 2019
     * @throws IllegalArgumentException if the id is less than 0, or if the name or investment is null or incorrect
     */
    public Departement(int id, String nom, float inves) throws IllegalArgumentException {
        boolean nomValide = false;
        if (id >= 0) {
            if (nom != null) {
                if (inves >= 0) {
                    for (int i = 0; i<lesDepsValides.length; i++) {
                        if ( nom.equals(lesDepsValides[i]) ) nomValide = true;
                    }
                    if(nomValide) {
                        this.idDep = id;
                        this.nomDep = nom;
                        this.invesCulturel2019 = inves;
                        this.lesAeroports = new ArrayList<>();
                    } else {
                        throw new IllegalArgumentException("Departement constructor : the name of the department is not a name of a Breton departement");
                    }
                } else {
                    throw new IllegalArgumentException("Departement constructor: The investment is incorrect");
                }
            } else {
                throw new IllegalArgumentException("Departement constructor: The name of the department is null");
            }
        } else {
            throw new IllegalArgumentException("Departement constructor: The id of the department is incorrect");
        }
    }

    //============================================ Methods ============================================//

    /**
     * Adds an airport to the department.
     *
     * @param aeroport the airport to add
     * @throws IllegalArgumentException if the airport is null
     */
    public void add(Aeroport aeroport) throws IllegalArgumentException {
        if (aeroport == null) {
            throw new IllegalArgumentException("add: The airport is null");
        }
        this.lesAeroports.add(aeroport);
    }

    /**
     * Compares the cultural investment of this department in 2019 with another department.
     *
     * @param autreDep the other department to compare
     * @return 1 if this department has a higher investment, 0 if equal, -1 if lower
     * @throws IllegalArgumentException if the other department is null
     */
    public int compareInves2019(Departement autreDep) throws IllegalArgumentException {
        if (autreDep == null) {
            throw new IllegalArgumentException("compareInves2019: The other department is null");
        }
        int res;
        if (this.invesCulturel2019 > autreDep.getInvesCulturel2019()) res = 1;
        else if (this.invesCulturel2019 == autreDep.getInvesCulturel2019()) res = 0;
        else res = -1;
        return res;
    }

        /**
     * Returns the list of names of train stations.
     * 
     * @return a list of names of train stations
     */
    public ArrayList<String> listerAeroports() {
        ArrayList<String> nomsAeroports= new ArrayList<>();
        for (Aeroport aer : this.lesAeroports) {
            nomsAeroports.add(aer.getNom());
        }
        return nomsAeroports;
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the id of the department.
     *
     * @return the id of the department
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Gets the name of the department.
     *
     * @return the name of the department
     */
    public String getNomDep() {
        return this.nomDep;
    }

    /**
     * Gets the cultural investment for the year 2019.
     *
     * @return the cultural investment for the year 2019
     */
    public float getInvesCulturel2019() {
        return this.invesCulturel2019;
    }
    
    /**
     * Gets the list of airports
     * 
     * @return the list of airports
     */
    public ArrayList<Aeroport> getLesAeroports() {
        return this.lesAeroports;
    }

    /**
     * Sets the id of the department.
     *
     * @param dep the new id of the department
     * @throws IllegalArgumentException if the id is less than 0
     */
    public void setIdDep(int dep) throws IllegalArgumentException {
        if (dep < 0) {
            throw new IllegalArgumentException("setIdDep: The id of the department is incorrect");
        }
        this.idDep = dep;
    }

    /**
     * Sets the name of the department.
     *
     * @param nom the new name of the department
     * @throws IllegalArgumentException if the name is null
     */
    public void setNomDep(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("setNomDep: The name of the department is null");
        }
        this.nomDep = nom;
    }

    /**
     * Sets the cultural investment for the year 2019.
     *
     * @param inves the new cultural investment
     * @throws IllegalArgumentException if the investment is less than 0
     */
    public void setInvesCulturel2019(float inves) throws IllegalArgumentException {
        if (inves < 0) {
            throw new IllegalArgumentException("setInvesCulturel2019: The cultural investment is incorrect");
        }
        this.invesCulturel2019 = inves;
    }

    /**
     * Returns a string representation of the department.
     *
     * @return a string representation of the department
     */
    public String toString() {

        String idDep = "idDep: " + this.idDep;
        String nomDep = "nomDep: " + this.nomDep;
        String invesCulturel2019 = "investCulturel2019: " + this.invesCulturel2019;
        String aeroports = "Airports: " + this.lesAeroports.toString();

        return idDep + "\n" + nomDep + "\n" + invesCulturel2019 + "\n" + aeroports + "\n";
    }
}
