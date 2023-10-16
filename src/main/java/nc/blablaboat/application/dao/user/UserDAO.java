package nc.blablaboat.application.dao.user;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Implémentation du DAO pour SQLite
public class UserDAO implements UserInterface {
    private Connection connection;

    public UserDAO() {
        // Établir la connexion à la base de données SQLite
        this.connection = ConnectionHolder.INSTANCE.getConnection();
    }

    @Override
    public void insert(User User) throws SQLException {
        String sql = "INSERT INTO people (name, age) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, User.getName());
        statement.setInt(2, User.getAge());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void update(User User) throws SQLException {
        String sql = "UPDATE people SET name = ?, age = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, User.getName());
        statement.setInt(2, User.getAge());
        statement.setInt(3, User.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM people WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM people WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User User = new User();
            User.setId(resultSet.getInt("id"));
            User.setName(resultSet.getString("name"));
            User.setAge(resultSet.getInt("age"));
            resultSet.close();
            statement.close();
            return User;
        } else {
            resultSet.close();
            statement.close();
            return null;
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM people";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<User> people = new ArrayList<>();

        while (resultSet.next()) {
            User User = new User();
            User.setId(resultSet.getInt("id"));
            User.setName(resultSet.getString("name"));
            User.setAge(resultSet.getInt("age"));
            people.add(User);
        }

        resultSet.close();
        statement.close();
        return people;
    }
}
