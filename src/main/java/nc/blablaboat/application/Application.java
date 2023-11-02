package nc.blablaboat.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;

import java.sql.SQLException;
import java.util.UUID;

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

        UserService userService = new UserService();

        // Exemple d'utilisation du service
        User user1 = new User("AliceM","Alice", "MERVEILLE", 50, "zebi", false);
        user1.setLastname("Alice");
        user1.setAge(30);

        userService.insert(user1);

        User user1dao = userService.getById(user1.getId());
        System.out.println(user1dao);


        // Trajet TableTrajet = new TrajetService()
        // personService.consulterTrajet(TableTrajet())
 
        // UserService.insert(User2);

        // // Récupérer une Userne par son ID
        // User retrievedUser = UserService.findUserById(1);
        // System.out.println("Retrieved User: " + retrievedUser.getName());

        // // Mettre à jour une Userne
        // retrievedUser.setAge(31);
        // UserService.update(retrievedUser);

        // // Récupérer toutes les Usernes
        // List<User> people = UserService.getAllPeople();
        // for (User User : people) {
        //     System.out.println("User: " + User.getName() + ", Age: " + User.getAge());
        // }

        // // Supprimer une Userne
        // UserService.delete(2);
    }

}
