package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util .*;

import model.data.Aeroport;
import model.data.Departement;

public class AeroportDAO extends DAO <Aeroport> {

    private ArrayList<Aeroport> aers;
    private DepartementDAO departementDAO = new DepartementDAO();


    public AeroportDAO() {
        this.aers = new ArrayList<Aeroport>();
    }

    public int create(Aeroport aer) {
        this.aers.add(aer);
        String query = "INSERT INTO aeroport(nom, adresse, leDepartement) VALUES ('" + aer.getNom() + "','" + aer.getAdresse() + "','" + aer.getLeDepartement().getIdDep() + " ')";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace ();
            return -1;
        }
    }

    public int update(Aeroport aer) {
        String query = "UPDATE aeroport SET nom='" + aer.getNom() + "' WHERE  adresse = '" + aer.getAdresse() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            for (int i = 0; i<this.aers.size(); i++ ){
                if (aer.getAdresse().equals(this.aers.get(i).getAdresse()) ) {
                    this.aers.remove(i);
                    this.aers.add(aer);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace ();
            return -1;
        }
    }

    public int delete(Aeroport aer) {
        this.aers.remove(aer);
        String query = "DELETE FROM aeroport WHERE nom='" + aer.getNom() + "'";
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace ();
            return -1;
        }
    }

    public Aeroport findById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public List <Aeroport> findAll () {
        this.aers.clear();
        try (Connection con = getConnection (); Statement st = con.createStatement ()) {
            ResultSet rs = st.executeQuery("SELECT * FROM aeroport");
            while (rs.next()) {
                
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                int departement = rs.getInt("leDepartement");

                Departement leDepartement = departementDAO.findById(departement);
                this.aers.add(new Aeroport(nom, adresse, leDepartement));
            }
        } catch (SQLException ex) {
            ex.printStackTrace ();
        }
        return aers;
    }

    
    
}