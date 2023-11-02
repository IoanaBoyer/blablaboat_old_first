package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserInterface {
    /**
     * Insérer un utilisateur dans la table user
     * @param user l'utilisateur à insérer
     */
    void insert(User user);

    /**
     * Mettre à jour un utilisateur dans la table user
     * @param user l'utilisateur à mettre à jour
     */
    void update(User user);

    /**
     * Supprimer un utilisateur dans la table user
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    void delete(String id);


    /**
     * Récupérer un utilisateur dans la table user via son id
     * @param id l'identifiant de l'utilisateur à récupérer
     * @return l'utilisateur souhaité
     */
    User getById(String id);

    /**
     * Récupérer une liste d'utilisateur correspondant à une recherche
     * @param searchTerm le(s) mot(s) clé(s) de la recherche
     * @return la liste des utilisateurs correspondant à la recherche
     */
    ArrayList<User> getBySearchTerm(String searchTerm);
}