package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.List;

import nc.blablaboat.application.dao.DBPersonDAO;
import nc.blablaboat.application.dao.PersonDAO;
import nc.blablaboat.application.model.Person;

public class PersonService {
    private PersonDAO personDAO;

    public PersonService() {
        this.personDAO = new DBPersonDAO();
    }

    public void createPerson(Person person) {
        try {
            personDAO.insert(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person findPersonById(int id) throws SQLException {
        return personDAO.findById(id);
    }

    public void updatePerson(Person retrievedPerson) {
    }

    public List<Person> getAllPeople() {
        return null;
    }

    public void deletePerson(int i) {
    }
}
