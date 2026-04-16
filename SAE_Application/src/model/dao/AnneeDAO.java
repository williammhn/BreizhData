package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.data.Annee;

public class AnneeDAO extends DAO<Annee> {

    private ArrayList<Annee> annees;

    public AnneeDAO() {
        this.annees = new ArrayList<Annee>();
    }

    public int create(Annee annee) {
        this.annees.add(annee); 
        String query = "INSERT INTO annee(annee, tauxInflation) VALUES ('" + annee.getAnnee() + "','" + annee.getTauxInflation() + "')";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int update(Annee annee) {
        String query = "UPDATE annee SET tauxInflation='" + annee.getTauxInflation() + "' WHERE annee='" + annee.getAnnee() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            for (int i = 0; i < this.annees.size(); i++) {
                if (annee.getAnnee() == this.annees.get(i).getAnnee()) {
                    this.annees.remove(i);
                    this.annees.add(annee);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int delete(Annee annee) {
        this.annees.remove(annee); 
        String query = "DELETE FROM annee WHERE annee='" + annee.getAnnee() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Annee findById(int id) {
        Annee an = null;
        String query = "SELECT * FROM annee WHERE annee='" + id + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                float inflation = rs.getFloat("tauxInflation");
                an = new Annee(id, inflation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return an;
    }

    public List<Annee> findAll() {
        this.annees.clear(); 
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM annee");
            while (rs.next()) {
                int annee = rs.getInt("annee");
                float tauxInflation = rs.getFloat("tauxInflation");
                this.annees.add(new Annee(annee, tauxInflation));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.annees;
    }
}
