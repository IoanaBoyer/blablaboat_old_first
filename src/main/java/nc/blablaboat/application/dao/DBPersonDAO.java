package nc.blablaboat.application.dao;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Implémentation du DAO pour SQLite
public class DBPersonDAO implements PersonDAO {
    private Connection connection;

    public DBPersonDAO() {
        // Établir la connexion à la base de données SQLite
        this.connection = ConnectionHolder.INSTANCE.getConnection();
    }

    @Override
    public void insert(Person person) throws SQLException {
        String sql = "INSERT INTO people (name, age) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getAge());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void update(Person person) throws SQLException {
        String sql = "UPDATE people SET name = ?, age = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getName());
        statement.setInt(2, person.getAge());
        statement.setInt(3, person.getId());
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
    public Person findById(int id) throws SQLException {
        String sql = "SELECT * FROM people WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            resultSet.close();
            statement.close();
            return person;
        } else {
            resultSet.close();
            statement.close();
            return null;
        }
    }

    @Override
    public List<Person> findAll() throws SQLException {
        String sql = "SELECT * FROM people";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Person> people = new ArrayList<>();

        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            people.add(person);
        }

        resultSet.close();
        statement.close();
        return people;
    }
}
