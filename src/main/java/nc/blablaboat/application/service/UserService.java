package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.List;

import nc.blablaboat.application.dao.user.UserDAO;
import nc.blablaboat.application.dao.user.UserInterface;
import nc.blablaboat.application.model.User;

public class UserService {
    private UserInterface UserDAO;

    public UserService() {
        this.UserDAO = new UserDAO();
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
