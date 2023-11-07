package nc.blablaboat.application.testfonctionnel;

import nc.blablaboat.application.dao.UserDAO;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;
import org.junit.jupiter.api.Test;

public class ConnexionTest {    private final UserDAO userDAO = new UserDAO();
    private final User defaultUser = new User();

    // 3.  Récupération de ses réservations
    @Test
    public void AfficherReservationsUtilisateur() {
        userDAO.insert(defaultUser);
        userDAO.getById(defaultUser.getId());

    }
}
