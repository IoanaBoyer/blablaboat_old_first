package nc.blablaboat.application.dao.user;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.User;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Méthodes de la table User
 */
public class UserDAO implements UserInterface {
    /**
     * La connexion à notre BDD SQLite
     */
    private Connection connection;

    /**
     * Constructeur par défaut
     */
    public UserDAO() {
        this.connection = ConnectionHolder.INSTANCE.getConnection();
    }

    @Override
    public void insert(User user) throws SQLException {
        String query = "INSERT INTO user (nickname, lastname, firstname, age, password) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE user SET nickname=?, lastname=?, firstname=?, age=?, password=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM user WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nickname = resultSet.getString("nickname");
                    String lastname = resultSet.getString("lastname");
                    String firstname = resultSet.getString("firstname"); // Notez la correction ici
                    int age = resultSet.getInt("age");
                    String password = resultSet.getString("password");

                    return new User(id, nickname, lastname, firstname, age, password);
                } else {
                    // Aucun utilisateur trouvé avec cet id
                    return null;
                }
            }
        }
    }

    @Override
    public List<User> getUserBySearchTerm(String searchTerm) throws SQLException {
        List<User> matchingUsers = new ArrayList<>();

        String query = "SELECT * FROM user WHERE nickname LIKE ? OR lastname LIKE ? OR firstname LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");
            preparedStatement.setString(3, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nickname = resultSet.getString("nickname");
                    String lastname = resultSet.getString("lastname");
                    String firstname = resultSet.getString("firstname");
                    int age = resultSet.getInt("age");
                    String password = resultSet.getString("password");

                    // Crée un objet User et l'ajoute à la liste
                    User user = new User(id, nickname, lastname, firstname, age, password);
                    matchingUsers.add(user);
                }
            }
        }
        return matchingUsers;
    }
}
