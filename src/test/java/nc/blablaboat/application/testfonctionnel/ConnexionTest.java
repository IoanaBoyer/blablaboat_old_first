package nc.blablaboat.application.testfonctionnel;

import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;
import org.junit.jupiter.api.Test;

public class ConnexionTest {    private final UserService userService = new UserService();
    private final User defaultUser = new User();

    // 3.  Récupération de ses réservations
    @Test
    public void AfficherReservationsUtilisateur() {
        userService.insert(defaultUser);
        userService.getById(defaultUser.getId());

    }
}
