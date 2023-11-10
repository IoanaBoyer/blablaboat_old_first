package nc.blablaboat.application.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import jakarta.servlet.http.Cookie;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;
import nc.blablaboat.application.view.BaseLayout;

@Route("/account")
public class UserAccountView extends BaseLayout implements BeforeEnterObserver {

    private final UserService userService = new UserService();

    public UserAccountView() {
        showProfile();
    }

    private void showProfile() {
//        String userId = "00000000-0000-0000-0000-000000000000";
        String userId = getCookieValue();
        System.out.println(userId);
        User u = userService.consultUserProfil(userId); // Replace "user_id" with the actual u's ID
        VerticalLayout profileLayout = new VerticalLayout(
                new H2("User Profile"),
                new Paragraph("User ID: " + u.getId()),
                new Paragraph("Nickname: " + u.getNickname()),
                new Paragraph("Last Name: " + u.getLastname()),
                new Paragraph("First Name: " + u.getFirstname()),
                new Paragraph("Age: " + u.getAge()),
                new Paragraph("Password: " + u.getPassword()),
                new Paragraph("Driver: " + u.getDriver())
        );
        setContent(profileLayout);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // Vérifiez si l'utilisateur a un cookie "userId" actif avant d'accéder à cette vue
        boolean hasUserIdCookie = hasUserIdCookie();
        if (!hasUserIdCookie) {
            // Redirigez vers la page de connexion s'il n'a pas de cookie "userId"
            beforeEnterEvent.rerouteTo("login");
        }
    }

    private boolean hasUserIdCookie() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getCookieValue() {
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
