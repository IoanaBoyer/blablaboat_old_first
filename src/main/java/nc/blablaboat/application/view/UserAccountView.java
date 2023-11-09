package nc.blablaboat.application.view;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;

@Route("/account")
public class UserAccountView extends BaseLayout {

    private final UserService userService = new UserService();

    public UserAccountView() {
        showProfile();
    }

    private void showProfile() {
        String userId = "00000000-0000-0000-0000-000000000000";
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
}
