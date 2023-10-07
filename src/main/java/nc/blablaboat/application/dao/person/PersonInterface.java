package nc.blablaboat.application.dao.person;

import nc.blablaboat.application.model.Person;
import java.util.List;

public interface PersonEntity {
    void insert(Person person);

    void update(Person person);

    void delete(int id);

    Person findById(int id);

    List<Person> findAll();
}