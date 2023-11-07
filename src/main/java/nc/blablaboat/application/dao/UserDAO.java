package nc.blablaboat.application.dao;

import nc.blablaboat.application.contract.UserDAOInterface;
import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.User;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Méthodes de la table user
 */
public class UserDAO implements UserDAOInterface {
    /**
     * La connexion à notre BDD SQLite
     */
    private final Connection CONNECTION;

    /**
     * Constructeur par défaut
     */
    public UserDAO() {
        this.CONNECTION = ConnectionHolder.INSTANCE.getConnection();
    }


    @Override
    public void insert(User user) {
        String query = "INSERT INTO user (id, nickname, lastname, firstname, age, password, isdriver) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getFirstname());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setBoolean(7, user.getDriver());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE user SET nickname=?, lastname=?, firstname=?, age=?, password=?, is_driver=? WHERE id=?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getFirstname());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setBoolean(7, user.getDriver());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) { // Modifiez le paramètre pour prendre en charge UUID
        String query = "DELETE FROM user WHERE id=?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(String id) { // TODO Modifier le paramètre pour prendre en charge UUID
        String query = "SELECT * FROM user WHERE id = ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nickname = resultSet.getString("nickname");
                    String lastname = resultSet.getString("lastname");
                    String firstname = resultSet.getString("firstname");
                    int age = resultSet.getInt("age");
                    String password = resultSet.getString("password");
                    Boolean isDriver = resultSet.getBoolean("isdriver");

                    return new User(UUID.fromString(id), nickname, lastname, firstname, age, password, isDriver);
                } else {
                    // Aucun utilisateur trouvé avec cet id
                    return null;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> getBySearchTerm(String searchTerm) {
        ArrayList<User> matchingUsers = new ArrayList<>();

        String query = "SELECT * FROM user WHERE nickname LIKE ? OR lastname LIKE ? OR firstname LIKE ?";

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchTerm + "%");
            preparedStatement.setString(2, "%" + searchTerm + "%");
            preparedStatement.setString(3, "%" + searchTerm + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UUID id = UUID.fromString(resultSet.getString("id")); // Récupérez UUID
                    String nickname = resultSet.getString("nickname");
                    String lastname = resultSet.getString("lastname");
                    String firstname = resultSet.getString("firstname");
                    int age = resultSet.getInt("age");
                    String password = resultSet.getString("password");
                    Boolean isDriver = resultSet.getBoolean("isdriver");

                    User user = new User(id, nickname, lastname, firstname, age, password, isDriver);
                    matchingUsers.add(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matchingUsers;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(createFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    private User createFromResultSet(ResultSet resultSet) throws SQLException {
        try{
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nickname = resultSet.getString("nickname");
            String lastname = resultSet.getString("lastname");
            String firstname = resultSet.getString("firstname");
            int age = resultSet.getInt("age");
            String password = resultSet.getString("password");
            Boolean isDriver = resultSet.getBoolean("isdriver");
            return new User(id, nickname, lastname, firstname, age, password, isDriver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}