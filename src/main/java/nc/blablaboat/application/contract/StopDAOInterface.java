package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Stop;

import java.util.ArrayList;

/**
 * Interface de la table port_of_call
 */
public interface StopDAOInterface {

    /**
     * Insérer un arret dans la table arret
     * @param stop l'arrêt à insérer
     */
    void insert(Stop stop);

    /**
     * Mettre à jour un arret dans la table arret
     * @param stop l'arrêt à mettre à jour
     */
    void update(Stop stop);

    /**
     * Supprimer un arrêt dans la table arret
     * @param id l'identifiant de l'arret à supprimer
     */
    void delete(String id);

    /**
     * Récupérer un arret dans la table arret via son id
     * @param id l'identifiant de l'arret à récupérer
     * @return l'arret souhaité
     */
    Stop getById(String id);

    /**
     * Récupérer la liste complète des arrêts
     * @return la liste des arrêts
     */
    ArrayList<Stop> getAll();
}