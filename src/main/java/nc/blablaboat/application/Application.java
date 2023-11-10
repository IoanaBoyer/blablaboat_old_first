package nc.blablaboat.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

import nc.blablaboat.application.dao.UserDAO;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.Stop;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.ReservationService;
import nc.blablaboat.application.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        // USER - test
        if (false) {
            UserDAO userDAO = new UserDAO();

            User user = new User("AliceM", "Alice", "MERVEILLE", 30, "zebi", false);
            user.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            userDAO.insert(user);

            User user1dao = userDAO.getById(user.getId());
            System.out.println(user1dao);
        }
        // RESERVATION  - test
        User user = new User("AliceM", "Alice", "MERVEILLE", 30, "zebi", false);
        userService.insert(user);

        Stop depart = new Stop("depart", 0.0,0.0);
        Stop arret = new Stop("arret", 0.0,0.0);
        Date ddep = new Date();
        Date darr = new Date();
        ArrayList<User> passagers = new ArrayList<>();
        passagers.add(user);

        Reservation reservation = new Reservation(null, depart, arret, ddep, darr, 0, 1000,
                "", passagers,user);

        ReservationService reservationService = new ReservationService();
        reservationService.insert(reservation);

    }

}
