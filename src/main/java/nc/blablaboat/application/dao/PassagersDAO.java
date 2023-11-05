package nc.blablaboat.application.dao;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Arret;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PassagersDAO {

    private final Connection CONNECTION = ConnectionHolder.INSTANCE.getConnection();
    private final UserService userService = new UserService();

    // Méthode pour insérer une réservation dans la base de données
    public void insert(Reservation reservation) {
        String query = "INSERT INTO reservation (id, depart_id, arrivee_id, date_heure_depart, date_heure_arrivee, nb_passager, tarif_unitaire, specifications, conducteur_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getId());
            preparedStatement.setString(2, reservation.getDepart().getId());
            preparedStatement.setString(3, reservation.getArrivee().getId());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(reservation.getDateHeureDepart().getTime()));
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(reservation.getDateHeureArrivee().getTime()));
            preparedStatement.setInt(6, reservation.getNbPassager());
            preparedStatement.setInt(7, reservation.getTarifUnitaire());
            preparedStatement.setString(8, reservation.getSpecifications());
            preparedStatement.setString(9, reservation.getConducteur().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Reservation reservation) {
        String query = "UPDATE reservation SET depart_id = ?, arrivee_id = ?, date_heure_depart = ?, date_heure_arrivee = ?, nb_passager = ?, " +
                "tarif_unitaire = ?, specifications = ?, conducteur_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getDepart().getId());
            preparedStatement.setString(2, reservation.getArrivee().getId());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(reservation.getDateHeureDepart().getTime()));
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(reservation.getDateHeureArrivee().getTime()));
            preparedStatement.setInt(5, reservation.getNbPassager());
            preparedStatement.setInt(6, reservation.getTarifUnitaire());
            preparedStatement.setString(7, reservation.getSpecifications());
            preparedStatement.setString(8, reservation.getConducteur().getId());
            preparedStatement.setString(9, reservation.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) { // Modifiez le paramètre pour prendre en charge UUID
        String query = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getByIdReservation(String id) {
        ArrayList<User> passagers = new ArrayList<>();
        String query = "SELECT * FROM passager WHERE reservation_id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
             preparedStatement.setString(1, id);
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                passagers.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passagers;
    }

    public List<User> getAll() {
        List<User> reservation = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
             ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }
    private User createFromResultSet(ResultSet resultSet) {
        try {
            return userService.getById(resultSet.getString("user_id"));
        } catch (Exception e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException("Erreur: recupération des passager de la réservation",e);
        }
    }
}