package model.data;

/**
 * Represents annual data for a commune.
 */
public class DonneesAnnuelles {

    /**
     * Number of houses in a commune.
     */
    private int nbMaison;
    
    /**
     * Number of apartments in a commune.
     */
    private int nbAppart;
    
    /**
     * Average price for a commune.
     */
    private float prixMoyen;
    
    /**
     * Average price per square meter for a commune.
     */
    private float prixM2Moyen;
    
    /**
     * Average surface area of a commune.
     */
    private float surfaceMoy;
    
    /**
     * Total cultural expenses of a commune.
     */
    private float depCulturellesTotales;
    
    /**
     * Total budget of a commune.
     */
    private float budgetTotal;
    
    /**
     * Population of a commune.
     */
    private float population;
    
    /**
     * Year for the annual data.
     */
    private Annee lAnnee;
    
    /**
     * Commune for the annual data.
     */
    private Commune laCommune;

    //============================================ Constructor ============================================//

    /**
     * Constructor for the DonneeAnnuelle class.
     * 
     * @param an             the year of the data
     * @param commune        the commune for the data
     * @param maison         the number of houses in the commune
     * @param appart         the number of apartments in the commune
     * @param LePrixMoyen    the average price in the commune
     * @param LePrixM2Moyen  the average price per square meter in the commune
     * @param LaSurfaceMoy   the average surface area in the commune
     * @param depenses       the total cultural expenses of the commune
     * @param budget         the total budget of the commune
     * @param pop            the population of the commune
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public DonneesAnnuelles(Annee an, Commune commune, int maison, int appart, float LePrixMoyen, float LePrixM2Moyen, float LaSurfaceMoy, float depenses, float budget, float pop) throws IllegalArgumentException {

        if (maison < 0) throw new IllegalArgumentException("DonneesAnnuelle: The number of houses is invalid");
        if (appart < 0) throw new IllegalArgumentException("DonneesAnnuelle: The number of apartments is invalid");
        if (LePrixMoyen < 0) throw new IllegalArgumentException("DonneesAnnuelle: The average price is invalid");
        if (LePrixM2Moyen < 0) throw new IllegalArgumentException("DonneesAnnuelle: The average price per square meter is invalid");
        if (LaSurfaceMoy < 0) throw new IllegalArgumentException("DonneesAnnuelle: The average surface area is invalid");
        if (commune == null) throw new IllegalArgumentException("DonneesAnnuelle: The commune is null");
        if (an == null) throw new IllegalArgumentException("DonneesAnnuelle: The year is null");

        // Note that there are no exceptions for depenses, budget and pop, because in the database these attributes often have a value of -1.

        this.nbMaison = maison;
        this.nbAppart = appart;
        this.prixMoyen = LePrixMoyen;
        this.prixM2Moyen = LePrixM2Moyen;
        this.surfaceMoy = LaSurfaceMoy;
        this.depCulturellesTotales = depenses;
        this.budgetTotal = budget;
        this.population = pop;
        this.laCommune = commune;
        this.lAnnee = an;
    }

    //============================================ Methods ============================================//

    /**
     * Calculates the population density relative to the average surface area of the commune.
     * 
     * @return the population density (number of inhabitants per unit area)
     */
    public float calculerDensitePopulation() {
        return this.population / this.surfaceMoy;
    }

    /**
     * Calculates the total price of real estate in the commune.
     * 
     * @return the total price of real estate
     */
    public float calculerPrixTotalBiensImmobiliers() {
        return (this.nbMaison * this.prixMoyen) + (this.nbAppart * this.prixMoyen);
    }

    /**
     * Calculates the ratio of cultural expenses per inhabitant.
     * 
     * @return the ratio of cultural expenses per inhabitant
     */
    public float calculerRatioDepensesCulturellesParHabitant() {
        return this.depCulturellesTotales / this.population;
    }

    /**
     * Checks if the commune's budget is balanced.
     * 
     * @return true if the budget is balanced, false otherwise
     */
    public boolean estBudgetEquilibre() {
        return this.budgetTotal >= this.calculerPrixTotalBiensImmobiliers() + this.depCulturellesTotales;
    }

    /**
     * Calculates various statistics for a commune.
     *
     * @return a string containing the statistics
     */
    public String calculerStatistiques() {
        float densitePopulation = calculerDensitePopulation();
        float prixTotalBiens = calculerPrixTotalBiensImmobiliers();
        float ratioDepensesCulturellesParHabitant = calculerRatioDepensesCulturellesParHabitant();
        String budgetEquilibre = this.estBudgetEquilibre() ? "Le budget de la commune est équilibré." : "Le budget de la commune n'est pas équilibré.";
        
        return "Densité de population: " + densitePopulation + "\n" +
               "Prix total des biens immobiliers: " + prixTotalBiens + "\n" +
               "Ratio des depenses culturelles par habitant: " + ratioDepensesCulturellesParHabitant + "\n" +
               budgetEquilibre;
    }

    /**
     * Generates a report based on the annual data and calculated statistics.
     *
     * @return a string representing the report
     */
    public String genererRapport() {
        String statistiques = calculerStatistiques();
        return "\nRapport Annuel pour la Commune de " + laCommune.getNomCommune() + " (" + lAnnee.getAnnee() + ")\n" +
               "-------------------------------------------------\n" +
               toString() + 
               "\nStatistiques:\n" + statistiques;
    }

    //============================================ Getters and Setters ============================================//

    /**
     * Gets the number of houses.
     * 
     * @return the number of houses
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Gets the number of apartments.
     * 
     * @return the number of apartments
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * Gets the average price.
     * 
     * @return the average price
     */
    public float getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * Gets the average price per square meter.
     * 
     * @return the average price per square meter
     */
    public float getPrixM2Moyen() {
        return this.prixM2Moyen;
    }

    /**
     * Gets the average surface area.
     * 
     * @return the average surface area
     */
    public float getSurfaceMoy() {
        return this.surfaceMoy;
    }

    /**
     * Gets the total cultural expenses.
     * 
     * @return the total cultural expenses
     */
    public float getDepCulturellesTotales() {
        return this.depCulturellesTotales;
    }

    /**
     * Gets the total budget.
     * 
     * @return the total budget
     */
    public float getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * Gets the population.
     * 
     * @return the population
     */
    public float getPopulation() {
        return this.population;
    }

    /**
     * Gets the commune.
     * 
     * @return the commune
     */
    public Commune getLaCommune() {
        return this.laCommune;
    }

    /**
     * Gets the year.
     * 
     * @return the year
     */
    public Annee getLAnnee() {
        return this.lAnnee;
    }

    /**
     * Sets the number of houses.
     * 
     * @param maison the number of houses
     * @throws IllegalArgumentException if the number of houses is less than 0
     */
    public void setNbMaison(int maison) throws IllegalArgumentException {
        if (maison < 0) {
            throw new IllegalArgumentException("setNbMaison: The number of houses is invalid");
        }
        this.nbMaison = maison;
    }

    /**
     * Sets the number of apartments.
     * 
     * @param appart the number of apartments
     * @throws IllegalArgumentException if the number of apartments is less than 0
     */
    public void setNbAppart(int appart) throws IllegalArgumentException {
        if (appart < 0) {
            throw new IllegalArgumentException("setNbAppart: The number of apartments is invalid");
        }
        this.nbAppart = appart;
    }

    /**
     * Sets the average price.
     * 
     * @param LePrixMoyen the average price
     * @throws IllegalArgumentException if the average price is less than 0
     */
    public void setPrixMoyen(float LePrixMoyen) throws IllegalArgumentException {
        if (LePrixMoyen < 0) {
            throw new IllegalArgumentException("setPrixMoyen: The average price is invalid");
        }
        this.prixMoyen = LePrixMoyen;
    }

    /**
     * Sets the average price per square meter.
     * 
     * @param LePrixM2Moyen the average price per square meter
     * @throws IllegalArgumentException if the average price per square meter is less than 0
     */
    public void setPrixM2Moyen(float LePrixM2Moyen) throws IllegalArgumentException {
        if (LePrixM2Moyen < 0) {
            throw new IllegalArgumentException("setPrixM2Moyen: The average price per square meter is invalid");
        }
        this.prixM2Moyen = LePrixM2Moyen;
    }

    /**
     * Sets the average surface area.
     * 
     * @param surface the average surface area
     * @throws IllegalArgumentException if the average surface area is less than 0
     */
    public void setSurfaceMoy(float surface) throws IllegalArgumentException {
        if (surface < 0) {
            throw new IllegalArgumentException("setSurfaceMoy: The average surface area is invalid");
        }
        this.surfaceMoy = surface;
    }

    /**
     * Sets the total cultural expenses.
     * 
     * @param depenses the total cultural expenses
     * @throws IllegalArgumentException if the total cultural expenses are less than 0
     */
    public void setDepCulturellesTotales(float depenses) throws IllegalArgumentException {
        if (depenses < 0) {
            throw new IllegalArgumentException("setDepCulturellesTotales: The total cultural expenses are invalid");
        }
        this.depCulturellesTotales = depenses;
    }

    /**
     * Sets the total budget.
     * 
     * @param budget the total budget
     * @throws IllegalArgumentException if the total budget is less than 0
     */
    public void setBudgetTotal(float budget) throws IllegalArgumentException {
        if (budget < 0) {
            throw new IllegalArgumentException("setBudgetTotal: The total budget is invalid");
        }
        this.budgetTotal = budget;
    }

    /**
     * Sets the population of the commune.
     * 
     * @param pop the population
     * @throws IllegalArgumentException if the population is less than 0
     */
    public void setPopulation(float pop) throws IllegalArgumentException {
        if (pop < 0) {
            throw new IllegalArgumentException("setPopulation: The population is invalid");
        }
        this.population = pop;
    }

    /**
     * Sets the commune for the data.
     * 
     * @param comm the commune
     * @throws IllegalArgumentException if the commune is null
     */
    public void setLaCommune(Commune comm) throws IllegalArgumentException {
        if (comm == null) {
            throw new IllegalArgumentException("setLaCommune: The commune is null");
        }
        this.laCommune = comm;
    }

    /**
     * Sets the year for the data.
     * 
     * @param annee the year
     * @throws IllegalArgumentException if the year is null
     */
    public void setLAnnee(Annee annee) throws IllegalArgumentException {
        if (annee == null) {
            throw new IllegalArgumentException("setLAnnee: The year is null");
        }
        this.lAnnee = annee;
    }

    /**
     * Returns a string representation of the annual data.
     * 
     * @return a string representation of the annual data
     */
    public String toString() {

        String commune = "" + this.laCommune;
        String lAnnee = "" + this.lAnnee;
        String nbMaison = "Nombre de maison: " + this.nbMaison;
        String nbAppart = "Nombre d'appartement: " + this.nbAppart;
        String prixMoyen = "Prix moyen: " + this.prixMoyen;
        String prixM2Moyen = "Prix m2 moyen: " + this.prixM2Moyen;
        String surfaceMoy = "Surface moyenne: " + this.surfaceMoy;
        String depCulturellesTotales = "Depenses culturelles totales: " + this.depCulturellesTotales;
        String budgetTotal = "Budget total: " + this.budgetTotal;
        String population = "Population: " + this.population;

        return commune + lAnnee + nbMaison + "\n" + nbAppart + "\n" + prixMoyen + "\n" + prixM2Moyen + "\n" + surfaceMoy + "\n"
                + depCulturellesTotales + "\n" + budgetTotal + "\n" + population +"\n";
    }
}
