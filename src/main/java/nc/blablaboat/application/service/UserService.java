package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nc.blablaboat.application.contract.UserInterface;
import nc.blablaboat.application.dao.UserDAO;
import nc.blablaboat.application.model.User;

/**
 * Méthodes de la classe User
 */
public class UserService implements UserInterface {
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
    public User consultUserProfil(String id){
        return getById(id);
    }

    /**
     * Rechercher un utilisateur avec un(des) mot(s) clé(s)
     * @param searchTerm la recherche
     * @return une liste d'utilisateur correspondant au(x) mot(s) clé(s)
     */
    public ArrayList<User> searchUser(String searchTerm){
        return getUserBySearchTerm(searchTerm);
    }

    /**
     * Créer un utilisateur dans la table user
     * @param user l'utilisateur à ajouter
     */
    @Override
    public void insert(User user) {
        UserDAO.insert(user);
    }
    /**
     * Mettre à jour un utilisateur dans la table user
     * @param user l'utilisateur à mettre à jour
     */
    @Override
    public void update(User user) {
            UserDAO.update(user);
    }

    /**
     * Supprimer un utilisateur dans la table user
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    @Override
    public void delete(String id) {
        UserDAO.delete(id);
    }

    @Override
    public User getById(String id){
        return UserDAO.getById(id);
    }

    @Override
    public ArrayList<User> getUserBySearchTerm(String searchTerm) {
        return null;
    }
}
