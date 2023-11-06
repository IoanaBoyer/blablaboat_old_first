package nc.blablaboat.application.dao;

import nc.blablaboat.application.contract.PortOfCallDAOInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.PortOfCall;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class PortOfCallDAO implements PortOfCallDAOInterface {
    /**
     * La connexion à notre BDD SQLite
     */
    private final Connection CONNECTION;

    /**
     * Constructeur par défaut
     */
    public PortOfCallDAO() {
        this.CONNECTION = ConnectionHolder.INSTANCE.getConnection();
    }

    @Override
    public void insert(PortOfCall portOfCall) {
        String query = "INSERT INTO port_of_call (id, name, longitude, latitude) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, portOfCall.getId());
            preparedStatement.setString(2, portOfCall.getName());
            preparedStatement.setDouble(3, portOfCall.getLongitude());
            preparedStatement.setDouble(4, portOfCall.getLatitude());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PortOfCall portOfCall) {
        String query = "UPDATE port_of_call SET name = ?, longitude = ?, latitude = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, portOfCall.getId());
            preparedStatement.setString(2, portOfCall.getName());
            preparedStatement.setDouble(3, portOfCall.getLongitude());
            preparedStatement.setDouble(4, portOfCall.getLatitude());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM port_of_call WHERE id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PortOfCall getById(String id) {
        String query = "SELECT * FROM port_of_call WHERE id = ?";
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
    public ArrayList<PortOfCall> getAll() {
        ArrayList<PortOfCall> portOfCalls = new ArrayList<>();
        String query = "SELECT * FROM port_of_call";
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

    private PortOfCall createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            double longitude = resultSet.getDouble("longitude");
            double latitude = resultSet.getDouble("latitude");
            return new PortOfCall(id, name, longitude, latitude);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
