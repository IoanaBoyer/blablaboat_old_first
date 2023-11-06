package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.PortOfCall;

import java.util.ArrayList;

/**
 * Interface de la table port_of_call
 */
public interface PortOfCallDAOInterface {

    /**
     * Insérer un arret dans la table arret
     * @param portOfCall l'arrêt à insérer
     */
    void insert(PortOfCall portOfCall);

    /**
     * Mettre à jour un arret dans la table arret
     * @param portOfCall l'arrêt à mettre à jour
     */
    void update(PortOfCall portOfCall);

    /**
     * Supprimer un arret dans la table arret
     * @param id l'identifiant de l'arret à supprimer
     */
    void delete(String id);

    /**
     * Récupérer un arret dans la table arret via son id
     * @param id l'identifiant de l'arret à récupérer
     * @return l'arret souhaité
     */
    PortOfCall getById(String id);

    /**
     * Récupérer la liste complète des arrêts
     * @return la liste des arrêts
     */
    ArrayList<PortOfCall> getAll();
}