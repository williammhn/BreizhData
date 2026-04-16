package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.data.Annee;
import model.data.Commune;
import model.data.DonneesAnnuelles;

public class DonneesAnnuellesDAO extends DAO<DonneesAnnuelles> {

    private CommuneDAO communeDAO = new CommuneDAO();
    private AnneeDAO anneeDAO = new AnneeDAO();

    private ArrayList<DonneesAnnuelles> donneesAnnuelles;

    public DonneesAnnuellesDAO() {
        this.donneesAnnuelles = new ArrayList<>();
    }

    public int create(DonneesAnnuelles donnees) {
        // Check if the year exists in the 'annee' table
        String insertQuery = "INSERT INTO donneesannuelles(lAnnee, laCommune, nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy, depensesCulturellesTotales, budgetTotal, population) VALUES ('"
                + donnees.getLAnnee().getAnnee() + "','" + donnees.getLaCommune().getIdCommune() + "','" + donnees.getNbMaison() + "','" + donnees.getNbAppart() + "','" + donnees.getPrixMoyen() + "','"
                + donnees.getPrixM2Moyen() + "','" + donnees.getSurfaceMoy() + "','" + donnees.getDepCulturellesTotales()
                + "','" + donnees.getBudgetTotal() + "','" + donnees.getPopulation() + "')";
        try (Connection con = getConnection(); 
             Statement insertSt = con.createStatement()) {
            return insertSt.executeUpdate(insertQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int update(DonneesAnnuelles donnees) {
        String query = "UPDATE donneesannuelles SET nbMaison='" + donnees.getNbMaison() + "', nbAppart='"
                + donnees.getNbAppart() + "', prixMoyen='" + donnees.getPrixMoyen() + "', prixM2Moyen='"
                + donnees.getPrixM2Moyen() + "', surfaceMoy='" + donnees.getSurfaceMoy() + "', depensesCulturellesTotales='"
                + donnees.getDepCulturellesTotales() + "', budgetTotal='" + donnees.getBudgetTotal() + "', population='"
                + donnees.getPopulation() + "' WHERE laCommune='" + donnees.getLaCommune().getIdCommune() 
                + "' AND lAnnee='" + donnees.getLAnnee().getAnnee() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            for (int i = 0; i < this.donneesAnnuelles.size(); i++) {
                if (donnees.getLaCommune().getIdCommune() == this.donneesAnnuelles.get(i).getLaCommune().getIdCommune()
                        && donnees.getLAnnee().getAnnee() == this.donneesAnnuelles.get(i).getLAnnee().getAnnee()) {
                    this.donneesAnnuelles.set(i, donnees);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int delete(DonneesAnnuelles donnees) {
        this.donneesAnnuelles.remove(donnees);
        String query = "DELETE FROM donneesannuelles WHERE laCommune='" + donnees.getLaCommune().getIdCommune()
                + "' AND lAnnee='" + donnees.getLAnnee().getAnnee() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public DonneesAnnuelles findById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List<DonneesAnnuelles> findAll() {
        this.donneesAnnuelles.clear(); // Nettoyer la liste actuelle avant d'ajouter les données récupérées
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM donneesannuelles");
            while (rs.next()) {
                int nbMaison = rs.getInt("nbMaison");
                int nbAppart = rs.getInt("nbAppart");
                float prixMoyen = rs.getFloat("prixMoyen");
                float prixM2Moyen = rs.getFloat("prixM2Moyen");
                float surfaceMoy = rs.getFloat("surfaceMoy");
                float depensesCulturellesTotales = rs.getFloat("depensesCulturellesTotales");
                float budgetTotal = rs.getFloat("budgetTotal");
                float population = rs.getFloat("population");
                int idComm = rs.getInt("laCommune");
                int annee = rs.getInt("lAnnee");

                Commune comm = communeDAO.findById(idComm);
                Annee an = anneeDAO.findById(annee);
                donneesAnnuelles.add(new DonneesAnnuelles(an, comm, nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy, depensesCulturellesTotales, budgetTotal, population));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.donneesAnnuelles;
    }

    
}
