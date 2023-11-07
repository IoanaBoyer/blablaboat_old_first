package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Stop;
import java.util.ArrayList;

/**
 * Interface de la table stop
 */
public interface StopDAOInterface {

    /**
     * Insérer un arrêt dans la table stop
     * @param stop l'arrêt à insérer
     */
    void insert(Stop stop);

    /**
     * Mettre à jour un arrêt dans la table stop
     * @param stop l'arrêt à mettre à jour
     */
    void update(Stop stop);

    /**
     * Supprimer un arrêt dans la table stop
     * @param id l'identifiant de l'arrêt à supprimer
     */
    void delete(String id);

    /**
     * Récupérer un arrêt dans la table stop via son id
     * @param id l'identifiant de l'arrêt à récupérer
     * @return l'arrêt souhaité
     */
    Stop getById(String id);

    /**
     * Récupérer une liste d'arrêts correspondant à une recherche
     * @param searchTerm le(s) mot(s) clé(s) de la recherche
     * @return la liste des arrêts correspondant à la recherche
     */
    ArrayList<Stop> getBySearchTerm(String searchTerm);

    /**
     * Récupérer la liste complète des arrêts
     * @return la liste des arrêts
     */
    ArrayList<Stop> getAll();
}