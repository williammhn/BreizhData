package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.data.Commune;
import model.data.Gare;

public class GareDAO extends DAO<Gare> {


    private CommuneDAO communeDAO = new CommuneDAO();

    private ArrayList<Gare> gares;
    private int fret;
    private int voyag;


    public GareDAO() {
        this.gares = new ArrayList<Gare>();

    }

    public int create(Gare gare) {
        if (gare.getEstFret()) fret = 1;
        else fret = 0;
        if (gare.getEstVoyageur()) voyag = 1;
        else voyag = 0;
        this.gares.add(gare);
        String query = "INSERT INTO gare(codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES ('" + gare.getCodeGare() + "','" + gare.getNomGare() + "','" + fret + "','" + voyag + "','" + gare.getLaCommune().getIdCommune() + "')";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int update(Gare gare) {
        if (gare.getEstFret()) fret = 1;
        else fret = 0;
        if (gare.getEstVoyageur()) voyag = 1;
        else voyag = 0;
        String query = "UPDATE gare SET nomGare='" + gare.getNomGare() + "', estFret='" + fret + "', estVoyageur='" + voyag + "' WHERE codeGare='" + gare.getCodeGare() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            for (int i = 0; i < this.gares.size(); i++) {
                if (gare.getCodeGare() == this.gares.get(i).getCodeGare()) {
                    this.gares.remove(i);
                    this.gares.add(gare);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int delete(Gare gare) {
        this.gares.remove(gare);
        String query = "DELETE FROM gare WHERE codeGare='" + gare.getCodeGare() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Gare findById(int id) {
        Gare gar = null;
        String query = "SELECT * FROM gare WHERE codeGare=" + id;
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String nom = rs.getString("nomGare");
                boolean estFret = rs.getBoolean("estFret");
                boolean estVoyageur = rs.getBoolean("estVoyageur");
                int comm = rs.getInt("laCommune");

                Commune commune = communeDAO.findById(comm);
                Gare gare = new Gare(id,nom,estFret, estVoyageur, commune);
                this.gares.add(gare); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return gar;
    }

    public List<Gare> findAll() {
        this.gares.clear(); // Nettoyer la liste actuelle avant d'ajouter les gares récupérées
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM gare");
            while (rs.next()) {
                int code = rs.getInt("codeGare");
                String nom = rs.getString("nomGare");
                boolean estFret = rs.getBoolean("estFret");
                boolean estVoyageur = rs.getBoolean("estVoyageur");
                int comm = rs.getInt("laCommune");

                Commune commune = communeDAO.findById(comm);
                this.gares.add(new Gare(code, nom, estFret, estVoyageur, commune));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.gares;
    }


}
