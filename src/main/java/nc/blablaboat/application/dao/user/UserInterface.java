package nc.blablaboat.application.dao.user;

import nc.blablaboat.application.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserInterface {
    /**
     * Insérer un utilisateur dans la table user
     * @param user l'utilisateur à insérer
     * @throws SQLException
     */
    void insert(User user) throws SQLException;

    /**
     * Mettre à jour un utilisateur dans la table user
     * @param user l'utilisateur à mettre à jour
     * @throws SQLException
     */
    void update(User user) throws SQLException;

    /**
     * Supprimer un utilisateur dans la table user
     * @param id l'identifiant de l'utilisateur à supprimer
     * @throws SQLException
     */
    void delete(int id) throws SQLException;


    /**
     * Récupérer un utilisateur dans la table user via son id
     * @param id l'identifiant de l'utilisateur à récupérer
     * @return l'utilisateur souhaité
     * @throws SQLException
     */
    User getUserById(int id) throws SQLException;

    /**
     * Récupérer une liste d'utilisateur correspondant à une recherche
     * @param searchTerm le(s) mot(s) clé(s) de la recherche
     * @return la liste des utilisateurs correspondant à la recherche
     * @throws SQLException
     */
    List<User> getUserBySearchTerm(String searchTerm) throws SQLException;
}