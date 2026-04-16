package model.dao;

import model.data.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO<User> {

    private ArrayList<User> users;

    public UserDAO() {
        this.users = new ArrayList<User>();
    }

    @Override
    public List<User> findAll() {
        this.users.clear();
        String query = "SELECT * FROM user";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                    rs.getString("nomUtilisateur"),
                    rs.getString("mdp"),
                    rs.getBoolean("estAdmin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    public User findByNomUtilisateur(String nomUtilisateur) {
        String query = "SELECT * FROM user WHERE nomUtilisateur = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomUtilisateur);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getString("nomUtilisateur"),
                        rs.getString("mdp"),
                        rs.getBoolean("estAdmin")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(User user) {
        String query = "UPDATE user SET mdp = ?, estAdmin = ? WHERE nomUtilisateur = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getMdp());
            stmt.setBoolean(2, user.isEstAdmin());
            stmt.setString(3, user.getNomUtilisateur());
            for (int i = 0; i < this.users.size(); i++) {
                if (user.getNomUtilisateur().equals(this.users.get(i).getNomUtilisateur())) {
                    this.users.remove(i);
                    this.users.add(user);
                    break;
                }
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(User user) {
        this.users.remove(user);
        String query = "DELETE FROM user WHERE nomUtilisateur = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNomUtilisateur());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int create(User user) {
        this.users.add(user);
        String query = "INSERT INTO user (nomUtilisateur, mdp, estAdmin) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNomUtilisateur());
            stmt.setString(2, user.getMdp());
            stmt.setBoolean(3, user.isEstAdmin());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
