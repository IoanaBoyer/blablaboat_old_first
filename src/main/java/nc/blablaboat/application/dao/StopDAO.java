package nc.blablaboat.application.dao;

import nc.blablaboat.application.contract.StopDAOInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Stop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class StopDAO implements StopDAOInterface {
    /**
     * La connexion à notre BDD SQLite
     */
    private final Connection CONNECTION;

    /**
     * Constructeur par défaut
     */
    public StopDAO() {
        this.CONNECTION = ConnectionHolder.INSTANCE.getConnection();
    }

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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Stop stop) {
        String query = "UPDATE stop SET name = ?, longitude = ?, latitude = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, stop.getId());
            preparedStatement.setString(2, stop.getName());
            preparedStatement.setDouble(3, stop.getLongitude());
            preparedStatement.setDouble(4, stop.getLatitude());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM stop WHERE id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Stop getById(String id) {
        String query = "SELECT * FROM stop WHERE id = ?";
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

    @Override
    public ArrayList<Stop> getAll() {
        ArrayList<Stop> portOfCalls = new ArrayList<>();
        String query = "SELECT * FROM stop";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                portOfCalls.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return portOfCalls;
    }

    private Stop createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            double longitude = resultSet.getDouble("longitude");
            double latitude = resultSet.getDouble("latitude");
            return new Stop(id, name, longitude, latitude);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
