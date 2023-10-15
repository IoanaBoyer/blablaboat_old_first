package nc.blablaboat.application.dao.person;

import nc.blablaboat.application.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonInterface {
    void insert(Person person) throws SQLException;

    void update(Person person) throws SQLException;

    void delete(int id) throws SQLException;

    Person findById(int id) throws SQLException;

    List<Person> findAll() throws SQLException;
}