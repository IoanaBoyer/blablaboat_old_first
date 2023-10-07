package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.List;

import nc.blablaboat.application.dao.person.PersonDAO;
import nc.blablaboat.application.dao.person.PersonEntity;
import nc.blablaboat.application.model.Person;

public class PersonService implements PersonEntity {
    private PersonEntity personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public void createPerson(Person person) {
        try {
            personDAO.insert(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person findPersonById(int id) {
        try {
            return personDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePerson(Person retrievedPerson) {
    }

    public List<Person> getAllPeople() {
        return null;
    }

    public void deletePerson(int i) {
    }

    @Override
    public void insert(Person person) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Person person) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Person findById(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Person> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
}
