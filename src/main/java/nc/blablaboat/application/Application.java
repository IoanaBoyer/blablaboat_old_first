package nc.blablaboat.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import nc.blablaboat.application.dao.connection.ConnectionHolder;
import nc.blablaboat.application.model.Person;
import nc.blablaboat.application.service.PersonService;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "todo")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // ajout: IB
        PersonService personService = new PersonService();

        // Exemple d'utilisation du service
        Person person1 = new Person();
        person1.setName("Alice");
        person1.setAge(30);

        // Person person2 = new Person();
        // person2.setName("Bob");
        // person2.setAge(25);

        personService.createPerson(person1);
 
        // personService.createPerson(person2);
        try {
            System.out.println("Retrieved Person: " + personService.findPersonById(17).toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // // Récupérer une personne par son ID
        // Person retrievedPerson = personService.findPersonById(1);
        // System.out.println("Retrieved Person: " + retrievedPerson.getName());

        // // Mettre à jour une personne
        // retrievedPerson.setAge(31);
        // personService.updatePerson(retrievedPerson);

        // // Récupérer toutes les personnes
        // List<Person> people = personService.getAllPeople();
        // for (Person person : people) {
        //     System.out.println("Person: " + person.getName() + ", Age: " + person.getAge());
        // }

        // // Supprimer une personne
        // personService.deletePerson(2);
    }

}
