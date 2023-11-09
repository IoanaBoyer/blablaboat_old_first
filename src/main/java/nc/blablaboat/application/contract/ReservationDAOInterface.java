package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Reservation;
import java.util.ArrayList;

/**
 * Interface de la table reservation
 */
public interface ReservationDAOInterface {

    /**
     * Insérer une réservation dans la table reservation
     * @param reservation la réservation à insérer
     */
    void insert(Reservation reservation);

    /**
     * Mettre à jour une réservation dans la table stop
     * @param reservation la réservation à mettre à jour
     */
    void update(Reservation reservation);

    /**
     * Supprimer une réservation dans la table reservation
     * @param id l'identifiant de la réservation à supprimer
     */
    void delete(String id);

    /**
     * Récupérer une réservation dans la table reservation via son id
     * @param id l'identifiant de la réservation à récupérer
     * @return la réservation souhaitée
     */
    Reservation getById(String id);

    /**
     * Récupérer la liste complète des réservations
     * @return la liste des réservations
     */
    ArrayList<Reservation> getAll();

    /**
     * Récupérer une liste d'arrêts correspondant à une recherche sur l'arrêt d'arrivée)
     * @param searchTerm le(s) mot(s) clé(s) de la recherche
     * @return la liste des arrêts correspondant à la recherche
     */
    ArrayList<Reservation> getBySearchTerm(String searchTerm);
}