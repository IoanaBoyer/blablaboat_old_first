package nc.blablaboat.application.dao;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PassengerDAO {

    /**
     * La connexion à notre BDD SQLite
     */
    private final Connection CONNECTION;

    /**
     * Constructeur par défaut
     */
    public PassengerDAO() {
        this.CONNECTION = ConnectionHolder.INSTANCE.getConnection();
    }

    /**
     * Insérer un passager dans la table passenger
     * @param reservationId l'id de la réservation
     * @param passengers la liste des passagers
     */
    public void insert(String reservationId, List<User> passengers) {
        String query = "INSERT INTO passenger (reservation_id, passenger_id) VALUES (?, ?)";
        for (User passenger : passengers) {
            try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
                preparedStatement.setString(1, reservationId);
                preparedStatement.setString(2, passenger.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Met à jour le passager d'une réservation spécifique en remplaçant un passager existant par un nouveau
     * @param reservationId L'identifiant de la réservation concernée.
     * @param oldPassengerId L'identifiant du passager à retirer de la réservation.
     * @param newPassengerId L'identifiant du nouveau passager à ajouter à la réservation.
     */
    public void update(String reservationId, String oldPassengerId, String newPassengerId) {
        String query = "UPDATE passenger SET passenger_id = ? WHERE reservation_id = ? AND passenger_id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, newPassengerId);
            preparedStatement.setString(2, reservationId);
            preparedStatement.setString(3, oldPassengerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprime un passager d'une réservation spécifique
     * @param id L'identifiant de la réservation concernée
     */
    public void delete(UUID id) {
        String query = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère une liste de passager d'une réservation spécifique
     * @param id L'identifiant de la réservation concernée
     * @return La liste de passager souhaitée
     */
    public ArrayList<User> getById(String id) {
        ArrayList<User> passagers = new ArrayList<>();
        String query = "SELECT * FROM passager WHERE reservation_id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    passagers.add(createFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passagers;
    }

    /**
     * Récupère la liste complète des passagers
     * @return La liste des passagers
     */
    public List<User> getAll() {
        List<User> passagers = new ArrayList<>();
        String query = "SELECT * FROM passager";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                passagers.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passagers;
    }

    /**
     * Crée un passager à partir d'un ResultSet
     * @param resultSet le ResultSet à partir duquel créer le passager
     * @return le passager créé
     */
    private User createFromResultSet(ResultSet resultSet) {
        try {
            return new User(
                    UUID.fromString(resultSet.getString("id")),
                    resultSet.getString("nickname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("firstname"),
                    resultSet.getInt("age"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_driver")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}