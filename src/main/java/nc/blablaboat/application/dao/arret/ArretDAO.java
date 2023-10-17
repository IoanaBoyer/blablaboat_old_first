package nc.blablaboat.application.dao.arret;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import nc.blablaboat.application.model.Arret;


public class ArretDAO {
    private Connection connection;

    public ArretDAO(Connection connection) {
        this.connection = connection;
    }

    public Arret getById(int id) {
        Arret arret = null;
        String query = "SELECT * FROM arrets WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                arret = new Arret();
                // Remplissez les propriétés de l'objet Arret à partir du ResultSet.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arret;
    }

    public ArrayList<Arret> getAll() {
        ArrayList<Arret> arrets = new ArrayList<>();
        String query = "SELECT * FROM arrets";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Arret arret = new Arret();
                // Remplissez les propriétés de l'objet Arret à partir du ResultSet.
                arrets.add(arret);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrets;
    }

    public void save(Arret arret) {
        String query = "INSERT INTO arrets (depart, arrivee, dateDepart, dateArrivee) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Définissez les paramètres de la requête en utilisant les propriétés de l'objet Arret.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Arret arret) {
        String query = "UPDATE arrets SET depart = ?, arrivee = ?, dateDepart = ?, dateArrivee = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Définissez les paramètres de la requête en utilisant les propriétés de l'objet Arret.
            stmt.setInt(5, arret.getId()); // Assurez-vous de disposer de la propriété id dans la classe Arret.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Arret arret) {
        String query = "DELETE FROM arrets WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, arret.getId()); // Assurez-vous de disposer de la propriété id dans la classe Arret.
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
