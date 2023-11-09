package nc.blablaboat.application.dao;

import nc.blablaboat.application.contract.ReservationDAOInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Stop;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ReservationDAO implements ReservationDAOInterface {

    private final Connection CONNECTION;
    private final StopDAO stopDAO;
    private final UserDAO userDAO;
    private final PassagersDAO passagersDAO;

    public ReservationDAO() {
        this.CONNECTION = ConnectionHolder.INSTANCE.getConnection();
        this.stopDAO = new StopDAO();
        this.userDAO = new UserDAO();
        this.passagersDAO = new PassagersDAO();
    }

    public Connection getCONNECTION() {
        return CONNECTION;
    }

    //TODO: private final String tableName = "reservation";

    /**
     * Insérer une réservation dans la table reservation
     * @param reservation la réservation à insérer
     */
    @Override
    public void insert(Reservation reservation) {
        String query = "INSERT INTO reservation (id, depart_id, arrivee_id, date_heure_depart, date_heure_arrivee, nb_passager, tarif_unitaire, specifications, conducteur_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getId());
            preparedStatement.setString(2, reservation.getDeparture().getId());
            preparedStatement.setString(3, reservation.getArrival().getId());
            preparedStatement.setTimestamp(4, new java.sql.Timestamp(reservation.getDateHeureDepart().getTime()));
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(reservation.getDateHeureArrivee().getTime()));
            preparedStatement.setInt(6, reservation.getNbPassager());
            preparedStatement.setInt(7, reservation.getTarifUnitaire());
            preparedStatement.setString(8, reservation.getSpecifications());
            preparedStatement.setString(9, reservation.getConducteur().getId());
            preparedStatement.executeUpdate();

            stopDAO.insert(reservation.getDeparture());
            stopDAO.insert(reservation.getArrival());
            passagersDAO.insert(reservation.getId(), reservation.getListePassagers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mettre à jour une réservation dans la table reservation
     * @param reservation la réservation à mettre à jour
     */
    @Override
    public void update(Reservation reservation) {
        String query = "UPDATE reservation SET depart_id = ?, arrivee_id = ?, date_heure_depart = ?, date_heure_arrivee = ?, nb_passager = ?, " +
                "tarif_unitaire = ?, specifications = ?, conducteur_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, reservation.getDeparture().getId());
            preparedStatement.setString(2, reservation.getArrival().getId());
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

    /**
     * Supprimer une réservation dans la table reservation
     * @param id l'identifiant de la réservation à supprimer
     */
    @Override
    public void delete(String id) { // Modifiez le paramètre pour prendre en charge UUID
        String query = "DELETE FROM reservation WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupérer une réservation dans la table reservation via son id
     * @param id l'identifiant de la réservation à récupérer
     * @return la réservation souhaitée
     */
    @Override
    public Reservation getById(String id) { // Modifiez le paramètre pour prendre en charge UUID
        String query = "SELECT * FROM reservation WHERE id = ?";
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

    /**
     * Récupérer la liste complète des réservations
     * @return la liste des réservations
     */
    @Override
    public ArrayList<Reservation> getAll() {
        ArrayList<Reservation> reservation = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                reservation.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }

    @Override
    //La recherche porte uniquement sur l'arrêt d'arrivée
    // TODO Définir si on fait aussi une recherche sur l'arrêt de départ
    public ArrayList<Reservation> getBySearchTerm(String searchTerm) {
        ArrayList<Reservation> matchingReservations = new ArrayList<>();

        // La requête ne vérifie que les arrêts d'arrivée
        String reservationQuery = "SELECT r.* FROM reservation r " +
                "JOIN stop s ON r.arrivee_id = s.id " +
                "WHERE s.name LIKE ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(reservationQuery)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = createFromResultSet(resultSet);
                    matchingReservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return matchingReservations;
    }

    /**
     * Créer une réservation à partir d'un ResultSet
     * @param resultSet le ResultSet à utiliser
     * @return La réservation créée
     */
    public Reservation createFromResultSet(ResultSet resultSet) {
        try {
            UUID id = UUID.fromString(resultSet.getString("id"));
            Stop depart = stopDAO.getById(resultSet.getString("depart_id"));
            Stop arrivee = stopDAO.getById(resultSet.getString("arrivee_id"));
            Date date_heure_depart = resultSet.getTimestamp("date_heure_depart");
            Date date_heure_arrivee = resultSet.getTimestamp("date_heure_arrivee");
            int nb_passager = resultSet.getInt("nb_passager");
            int tarif_unitaire = resultSet.getInt("tarif_unitaire");
            String specifications = resultSet.getString("specifications");
            User conducteur = userDAO.getById(resultSet.getString("conducteur_id"));
            ArrayList<User> passagers = passagersDAO.getById(resultSet.getString("passagers_id"));

            return new Reservation(id, depart, arrivee, date_heure_depart, date_heure_arrivee, nb_passager, tarif_unitaire, specifications, passagers, conducteur);
        } catch (SQLException e) {
            // Gérer l'exception ou la propager
            throw new RuntimeException(e);
        }
    }
}
