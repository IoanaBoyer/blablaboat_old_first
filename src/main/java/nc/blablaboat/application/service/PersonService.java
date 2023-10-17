package nc.blablaboat.application.service;

import java.sql.SQLException;
import java.util.List;

import nc.blablaboat.application.dao.person.PersonDAO;
import nc.blablaboat.application.dao.person.PersonInterface;
import nc.blablaboat.application.model.Person;

public class PersonService {
    private PersonInterface personDAO;

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
}
