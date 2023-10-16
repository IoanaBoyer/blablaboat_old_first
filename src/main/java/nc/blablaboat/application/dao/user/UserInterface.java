package nc.blablaboat.application.dao.user;

import nc.blablaboat.application.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserInterface {
    void insert(User User) throws SQLException;

    void update(User User) throws SQLException;

    void delete(int id) throws SQLException;

    User findById(int id) throws SQLException;

    List<User> findAll() throws SQLException;
}