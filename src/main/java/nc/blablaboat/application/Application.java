package nc.blablaboat.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;

import java.sql.SQLException;

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
        UserService UserService = new UserService();

        // Exemple d'utilisation du service
        User User1 = new User();
        User1.setLastname("Alice");
        User1.setAge(30);

        // User User2 = new User();
        // User2.setName("Bob");
        // User2.setAge(25);

        UserService.createUser(User1);
 
        // UserService.createUser(User2);

        // // Récupérer une Userne par son ID
        // User retrievedUser = UserService.findUserById(1);
        // System.out.println("Retrieved User: " + retrievedUser.getName());

        // // Mettre à jour une Userne
        // retrievedUser.setAge(31);
        // UserService.updateUser(retrievedUser);

        // // Récupérer toutes les Usernes
        // List<User> people = UserService.getAllPeople();
        // for (User User : people) {
        //     System.out.println("User: " + User.getName() + ", Age: " + User.getAge());
        // }

        // // Supprimer une Userne
        // UserService.deleteUser(2);
    }

}
