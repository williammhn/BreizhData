package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.data.Departement;

public class DepartementDAO extends DAO<Departement> {

    private ArrayList<Departement> deps;

    public DepartementDAO() {
        this.deps = new ArrayList<Departement>();
    }

    public int create(Departement dep) {
        this.deps.add(dep);
        String query = "INSERT INTO departement(idDep, nomDep, investissementCulturel2019) VALUES ('" + dep.getIdDep() + "','" + dep.getNomDep() + "','" + dep.getInvesCulturel2019() + "')";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int update(Departement dep) {
        String query = "UPDATE departement SET investissementCulturel2019='" + dep.getInvesCulturel2019() + "' WHERE idDep='" + dep.getIdDep() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            for (int i = 0; i < this.deps.size(); i++) {
                if (dep.getIdDep() == this.deps.get(i).getIdDep()) {
                    this.deps.remove(i);
                    this.deps.add(dep);
                    break;
                }
            }
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int delete(Departement dep) {
        this.deps.remove(dep); 
        String query = "DELETE FROM departement WHERE idDep='" + dep.getIdDep() + "'";
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            return st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public Departement findById(int id) {
        Departement departement = null;
        String query = "SELECT * FROM departement WHERE idDep=" + id;
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String nom = rs.getString("nomDep");
                float invest = rs.getFloat("investissementCulturel2019");
                departement = new Departement(id, nom, invest);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return departement;
    }

    public List<Departement> findAll() {
        this.deps.clear();
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM departement");
            while (rs.next()) {
                int idDep = rs.getInt("idDep");
                String nomDep = rs.getString("nomDep");
                float invest = rs.getFloat("investissementCulturel2019");
                deps.add(new Departement(idDep, nomDep, invest));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.deps;
    }
}
