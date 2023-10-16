package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nc.blablaboat.application.dao.user.UserDAO;
import nc.blablaboat.application.dao.user.UserInterface;
import nc.blablaboat.application.model.User;

public class UserService {
    /**
     * Connexion à la table User
     */
    private UserDAO UserDAO;

    //TODO ajouter trajetService

    /**
     * Constructeur
     */
    public UserService() {
        this.UserDAO = new UserDAO();
    }

    public User consultUser(int id){
        try {
            return UserDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> searchUsers(String search){
        try {
            return UserDAO.findByString(search);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(User User) {
        try {
            UserDAO.insert(User);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUserById(int id) {
        try {
            return UserDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User retrievedUser) {
    }

    public List<User> getAllPeople() {
        return null;
    }

    public void deleteUser(int i) {
    }
}
