package nc.blablaboat.application.dao;
import nc.blablaboat.application.contract.ArretInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Arret;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ArretDAO implements ArretInterface {
    private final Connection CONNECTION = ConnectionHolder.INSTANCE.getConnection();

    // Méthode pour insérer un arrêt dans la base de données
    @Override
    public void insert(Arret arret) {
        String query = "INSERT INTO arrets (id, longitude, latitude) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, arret.getId());
            preparedStatement.setDouble(2, arret.getLongitude());
            preparedStatement.setDouble(3, arret.getLatitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }

    // Méthode pour mettre à jour un arrêt dans la base de données
    @Override
    public void update(Arret arret) {
        String query = "UPDATE arrets SET longitude = ?, latitude = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setDouble(1, arret.getLongitude());
            preparedStatement.setDouble(2, arret.getLatitude());
            preparedStatement.setString(3, arret.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }

    // Méthode pour supprimer un arrêt de la base de données
    @Override
    public void delete(String id) {
        String query = "DELETE FROM arrets WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }

    // Méthode pour récupérer un arrêt par son ID
    @Override
    public Arret getById(String id) {
        String query = "SELECT * FROM arrets WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
        return null;
    }

    // Méthode pour récupérer tous les arrêts
    @Override
    public ArrayList<Arret> getAll() {
        ArrayList<Arret> arrets = new ArrayList<>();
        String query = "SELECT * FROM arrets";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                arrets.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
        return arrets;
    }

    // Méthode utilitaire pour créer un objet Arret à partir d'un ResultSet
    private Arret createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id"));
            double longitude = resultSet.getDouble("longitude");
            double latitude = resultSet.getDouble("latitude");
            return new Arret(id, longitude, latitude);
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }
}
