package nc.blablaboat.application.dao;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Arret;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReservationPassagersDAO {

    private final Connection CONNECTION = ConnectionHolder.INSTANCE.getConnection();

    // Méthode pour insérer une réservation dans la base de données
    public void insert(Reservation reservation) {
        String query = "INSERT INTO reservations (id, depart_id, arrivee_id, date_heure_depart, date_heure_arrivee, nb_passager, tarif_unitaire, specifications, conducteur_id) " +
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
        String query = "UPDATE reservations SET depart_id = ?, arrivee_id = ?, date_heure_depart = ?, date_heure_arrivee = ?, nb_passager = ?, " +
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
        String query = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation getById(String id) { // Modifiez le paramètre pour prendre en charge UUID
        String query = "SELECT * FROM reservations WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reservations.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }

    private Reservation createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id")); // Récupérez UUID
            ArretDAO arretDAO = new ArretDAO();
            Arret depart = arretDAO.getById(resultSet.getString("depart_id"));
            Arret arrivee = arretDAO.getById(resultSet.getString("arrivee_id"));
            Date dateHeureDepart = resultSet.getTimestamp("date_heure_depart");
            Date dateHeureArrivee = resultSet.getTimestamp("date_heure_arrivee");
            int nbPassager = resultSet.getInt("nb_passager");
            int tarifUnitaire = resultSet.getInt("tarif_unitaire");
            String specifications = resultSet.getString("specifications");
            UserDAO userDAO = new UserDAO();
            User conducteur = userDAO.getById(resultSet.getString("conducteur_id"));
            return new Reservation(id, depart, arrivee, dateHeureDepart, dateHeureArrivee, nbPassager, tarifUnitaire, specifications, new ArrayList<>(), conducteur);
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }
}