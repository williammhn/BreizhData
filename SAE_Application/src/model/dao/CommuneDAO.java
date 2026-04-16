package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.data.Commune;
import model.data.Departement;

public class CommuneDAO extends DAO<Commune> {

    private ArrayList<Commune> comms;
    private DepartementDAO departementDAO = new DepartementDAO();

    public CommuneDAO() {
        this.comms = new ArrayList<>();
    }

    public int create(Commune commune) {
        this.comms.add(commune);
        String query = "INSERT INTO commune(idCommune, nomCommune, leDepartement) VALUES ('" + commune.getIdCommune() + "','" + commune.getNomCommune() + "','" + commune.getLeDepartement().getIdDep()+ "')";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int update(Commune commune) {
        String query = "UPDATE commune SET nomCommune='" + commune.getNomCommune() + "', leDepartement='" + commune.getLeDepartement().getIdDep() + "' WHERE idCommune='" + commune.getIdCommune() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            for (int i = 0; i < this.comms.size(); i++) {
                if (commune.getNomCommune().equals(this.comms.get(i).getNomCommune())) {
                    this.comms.remove(i);
                    this.comms.add(commune);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int delete(Commune commune) {
        this.comms.remove(commune); 
        String query = "DELETE FROM commune WHERE idCommune='" + commune.getIdCommune() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Commune findById(int id) {
        Commune comm = null;
        String query = "SELECT * FROM commune WHERE idCommune=" + id;
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String nom = rs.getString("nomCommune");
                int dep = rs.getInt("leDepartement");

                Departement leDepartement = departementDAO.findById(dep);
                comm = new Commune(id, nom, leDepartement);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comm;
    }

    public List<Commune> findAll() {
        this.comms.clear(); 
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM commune");
            while (rs.next()) {
                int id = rs.getInt("idCommune");
                String nom = rs.getString("nomCommune");
                int departement = rs.getInt("leDepartement");

                Departement leDepartement = departementDAO.findById(departement);
                Commune commune = new Commune(id, nom, leDepartement);
                this.comms.add(commune);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.comms;
    }
}
