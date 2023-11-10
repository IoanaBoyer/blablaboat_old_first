package nc.blablaboat.application.view.login;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import jakarta.servlet.http.Cookie;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.AuthService;
import nc.blablaboat.application.view.BaseLayout;

@Route("login")
public class LoginView extends BaseLayout {

    public LoginView() {
        TextField usernameField = new TextField("Nom d'utilisateur");
        PasswordField passwordField = new PasswordField("Mot de passe");
        Button loginButton = new Button("Se connecter", e -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            // Appelez le service d'authentification
            AuthService authService = new AuthService();
            User user = authService.authenticate(username, password);
            if (user != null) {
                // Authentification réussie
                storeUserIdInCookie(user.getId());
                getUI().ifPresent(ui -> ui.navigate("account"));
            } else {
                // Authentification échouée, afficher un message d'erreur
                Notification.show("Authentification échouée", 3000, Notification.Position.TOP_CENTER);
            }
        });

        // Utilisez la méthode setContent de votre BaseLayout pour définir le contenu
        setContent(new Text("Bienvenue, veuillez vous connecter."), usernameField, passwordField, loginButton);
    }

    private void storeUserIdInCookie(String userId) {
        Cookie userIdCookie = new Cookie("userId", userId);
        userIdCookie.setPath("/");
        userIdCookie.setMaxAge(30); // La durée de vie du cookie en secondes
        VaadinService.getCurrentResponse().addCookie(userIdCookie);
    }
}
