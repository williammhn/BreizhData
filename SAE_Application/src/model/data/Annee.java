package model.data;

/**
 * Represents a year with its inflation rate.
 */
public class Annee {

    /**
     * The year.
     */
    private int annee;

    /**
     * The inflation rate for the year.
     */
    private float tauxInflation;

    //============================================ Constructor ============================================//

    /**
     * Constructor with parameters that initializes the year and the given inflation rate.
     *
     * @param annee the year
     * @param inflation the inflation rate
     * @throws IllegalArgumentException if the year or the inflation rate is incorrect
     */
    public Annee(int annee, float inflation) throws IllegalArgumentException {
        if (annee >= 0) {
            if (inflation >= 0) {
                this.annee = annee;
                this.tauxInflation = inflation;
            } else {
                throw new IllegalArgumentException("Annee constructor: The inflation rate is incorrect");
            }
        } else {
            throw new IllegalArgumentException("Annee constructor: The year is incorrect");
        }
    }

    //============================================ Methods ============================================//

    /**
     * Compares the inflation rate of this year with another year.
     *
     * @param autre the other year to compare
     * @return a negative value if this year has a lower inflation rate,
     *         zero if the rates are equal, a positive value if this year has a
     *         higher inflation rate
     * @throws IllegalArgumentException if the other year is null
     */
    public int comparerTauxInflation(Annee autre) throws IllegalArgumentException {
        if (autre == null) {
            throw new IllegalArgumentException("comparerTauxInflation: The other year is null");
        }
        return Float.compare(this.tauxInflation, autre.getTauxInflation());
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the year.
     *
     * @return the year
     */
    public int getAnnee() {
        return this.annee;
    }

    /**
     * Gets the inflation rate.
     *
     * @return the inflation rate
     */
    public float getTauxInflation() {
        return this.tauxInflation;
    }

    /**
     * Sets the year.
     *
     * @param annee the new year
     * @throws IllegalArgumentException if the year is less than 0
     */
    public void setAnnee(int annee) throws IllegalArgumentException {
        if (annee < 0) {
            throw new IllegalArgumentException("setAnnee: The year is incorrect");
        }
        this.annee = annee;
    }

    /**
     * Sets the inflation rate.
     *
     * @param tauxInflation the new inflation rate
     * @throws IllegalArgumentException if the inflation rate is less than 0
     */
    public void setTauxInflation(float tauxInflation) throws IllegalArgumentException {
        if (tauxInflation < 0) {
            throw new IllegalArgumentException("setTauxInflation: The inflation rate is incorrect");
        }
        this.tauxInflation = tauxInflation;
    }

    /**
     * Returns a string representation of the year and its inflation rate.
     *
     * @return a string representation of the year and its inflation rate
     */
    public String toString() {

        String annee = "Année: " + this.annee;
        String tauxInflation = "Taux d'inflation: " + this.tauxInflation;

        return annee + "\n" + tauxInflation + "\n";
    }
}
