package nc.blablaboat.application.dao;
import nc.blablaboat.application.contract.ArretInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Stop;

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
    public void insert(Stop stop) {
        String query = "INSERT INTO stop (id, name, longitude, latitude) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, stop.getId());
            preparedStatement.setString(2, stop.getName());
            preparedStatement.setDouble(3, stop.getLongitude());
            preparedStatement.setDouble(4, stop.getLatitude());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }

    // Méthode pour mettre à jour un arrêt dans la base de données
    @Override
    public void update(Stop stop) {
        String query = "UPDATE stop SET name = ?, longitude = ?, latitude = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, stop.getName());
            preparedStatement.setDouble(2, stop.getLongitude());
            preparedStatement.setDouble(3, stop.getLatitude());
            preparedStatement.setString(4, stop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }

    // Méthode pour supprimer un arrêt de la base de données
    @Override
    public void delete(String id) {
        String query = "DELETE FROM stop WHERE id = ?";
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
    public ArrayList<Stop> getById(String id) {
        ArrayList<Stop> arrets = new ArrayList<>();
        String query = "SELECT * FROM stop WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
             preparedStatement.setString(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                arrets.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
        return arrets;
    }

    // Méthode pour récupérer tous les arrêts
    @Override
    public ArrayList<Stop> getAll() {
        ArrayList<Stop> arrets = new ArrayList<>();
        String query = "SELECT * FROM stop";
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

    // Méthode utilitaire pour créer un objet Stop à partir d'un ResultSet
    private Stop createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            double longitude = resultSet.getDouble("longitude");
            double latitude = resultSet.getDouble("latitude");
            return new Stop(id, name, longitude, latitude);
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }
}
