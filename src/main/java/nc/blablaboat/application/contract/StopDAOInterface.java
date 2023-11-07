package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Stop;

import java.util.ArrayList;

/**
 * Interface de la table port_of_call
 */
public interface StopDAOInterface {

    /**
     * Insérer un stop dans la table stop
     * @param stop l'arrêt à insérer
     */
    void insert(Stop stop);

    /**
     * Mettre à jour un stop dans la table stop
     * @param stop l'arrêt à mettre à jour
     */
    void update(Stop stop);

    /**
     * Supprimer un arrêt dans la table stop
     * @param id l'identifiant de l'stop à supprimer
     */
    void delete(String id);

    /**
     * Récupérer un stop dans la table stop via son id
     * @param id l'identifiant de l'stop à récupérer
     * @return l'stop souhaité
     */
    Stop getById(String id);

    /**
     * Récupérer la liste complète des arrêts
     * @return la liste des arrêts
     */
    ArrayList<Stop> getAll();
}