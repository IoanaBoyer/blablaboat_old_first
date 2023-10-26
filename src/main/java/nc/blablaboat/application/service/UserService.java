package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.List;
import nc.blablaboat.application.dao.user.UserDAO;
import nc.blablaboat.application.model.User;

/**
 * Méthodes de la classe User
 */
public class UserService {
    /**
     * Connexion à la table User
     */
    private UserDAO UserDAO;

    //TODO ajouter demandeService & reservationService

    /**
     * Constructeur par défaut
     */
    public UserService() {
        this.UserDAO = new UserDAO();
    }

    /**
     * TODO Se connecter à l'application (pseudo et mdp correct)
     */
    public void logApp(){

    }

    /**
     * TODO Consulter les trajets proposés
     */
    public void consultProposals(){

    }

    /**
     * TODO Consulter les trajets demandés
     */
    public void consultRequests(){

    }

    /**
     * Consulter le profil d'un utilisateur
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur recherché
     */
    public User consultUserProfil(int id){
        try {
            return UserDAO.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Rechercher un utilisateur avec un(des) mot(s) clé(s)
     * @param searchTerm la recherche
     * @return une liste d'utilisateur correspondant au(x) mot(s) clé(s)
     */
    public List<User> searchUser(String searchTerm){
        try {
            return UserDAO.getUserBySearchTerm(searchTerm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Créer un utilisateur dans la table user
     * @param user l'utilisateur à ajouter
     */
    public void createUser(User user) {
        try {
            UserDAO.insert(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mettre à jour un utilisateur dans la table user
     * @param user l'utilisateur à mettre à jour
     */
    public void updateUser(User user) {
        try {
            UserDAO.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Supprimer un utilisateur dans la table user
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    public void deleteUser(int id) {
        try {
            UserDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
