package nc.blablaboat.application.service;


import java.util.ArrayList;
import nc.blablaboat.application.dao.UserDAO;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;

/**
 * Méthodes de la classe User
 */
public class UserService {
    /**
     * Connexion à la table User
     */
    private final UserDAO userDAO;

    /**
     * Utilisation de ReservationService
     */
    private final ReservationService reservationService;

    /**
     * Constructeur par défaut
     */
    public UserService() {
        this.userDAO = new UserDAO();
        this.reservationService = new ReservationService();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * TODO Se connecter à l'application (pseudo et mdp correct) -> à mettre dans la classe Application non ?
     */
    public void logApp() {}

    /**
     * Consulter la liste des réservations proposées
     * @return liste des réservations en cours
     */
    public ArrayList<Reservation> consultReservations() {
        // TODO ajouter un filtre pour voir les réservation dont la date de départ est supérieure à la date actuelle
        return reservationService.getAll();
    }

    /**
     * Consulter le profil d'un utilisateur
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur recherché
     */
    public User consultUserProfil(String id) {
        return userDAO.getById(id);
    }

    public User getById(String id) {
        return userDAO.getById(id);
    }

    public void insert(User user) {
        userDAO.insert(user);
    }

    /**
     * Rechercher un utilisateur avec un(des) mot(s) clé(s)
     * @param searchTerm la recherche
     * @return une liste d'utilisateur correspondant au(x) mot(s) clé(s)
     */
    public ArrayList<User> searchUser(String searchTerm){
        return userDAO.getBySearchTerm(searchTerm);
    }

    /**
     * Consulter la liste des utilisateurs
     * @return la liste des utilisateurs
     */
    public ArrayList<User> consultUsers() {
        return userDAO.getAll();
    }
}
