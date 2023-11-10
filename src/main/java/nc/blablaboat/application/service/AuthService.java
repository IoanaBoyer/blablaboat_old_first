package nc.blablaboat.application.service;

import nc.blablaboat.application.dao.UserDAO;
import nc.blablaboat.application.model.User;

public class AuthService {
    private UserDAO userDAO = new UserDAO(); // Injection de dépendance

    public User authenticate(String username, String password) {
        // Logique d'authentification
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
}