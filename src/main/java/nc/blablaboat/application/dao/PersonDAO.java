package nc.blablaboat.application.dao;

import nc.blablaboat.application.model.Person;
import java.util.List;
import java.sql.SQLException;


public interface PersonDAO {
    void insert(Person person) throws SQLException;

    void update(Person person) throws SQLException;

    void delete(int id) throws SQLException;

    Person findById(int id) throws SQLException;

    List<Person> findAll() throws SQLException;
}