package nc.blablaboat.application.dao;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassagersDAO {

    private final Connection CONNECTION = ConnectionHolder.INSTANCE.getConnection();

    public void insert(String reservationId, List<User> passagers) {
        String query = "INSERT INTO passager (reservation_id, user_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            for (User passager : passagers) {
                preparedStatement.setString(1, reservationId);
                preparedStatement.setString(2, passager.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getById(String reservationId) {
        ArrayList<User> passagers = new ArrayList<>();
        String query = "SELECT * FROM passager WHERE reservation_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservationId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String userId = resultSet.getString("user_id");
                    User passager = new UserDAO().getById(userId);
                    passagers.add(passager);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passagers;
    }

    public void removePassagersFromReservation(String reservationId, List<User> passagers) {
        String query = "DELETE FROM passager WHERE reservation_id = ? AND user_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            for (User passager : passagers) {
                preparedStatement.setString(1, reservationId.toString());
                preparedStatement.setString(2, passager.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAllPassagersFromReservation(String reservationId) {
        String query = "DELETE FROM passager WHERE reservation_id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservationId.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
