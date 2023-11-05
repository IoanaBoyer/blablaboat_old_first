package nc.blablaboat.application.testfonctionnel;

import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.ReservationService;
import nc.blablaboat.application.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UtilisateurTest {
    // 2a) ---- Cas utilisateur - créer
    // Affichage page de ses reservations
    // 3.  Récupération de ses réservations
    // 4a. utilisateur --- page acceuil -- réservations proposées.
    // 4b. utilisateur -- recherche -- réservation
    // 5.  utilisteur -- rejoindre réservation

    // 2a) ---- Cas utilisateur - supprimer
    // 1. utilisateur -- supprime -- reservation
    // ----


    private final UserService userService = new UserService();
    private final User defaultUser = new User("Maurice", "Search", "User2", 30, "p@ssw0rd", false);
    private final ReservationService reservationService = new ReservationService();


    // 3.  Récupération de ses réservations
    @Test
    public void AfficherReservationsUtilisateur() {
        userService.insert(defaultUser);
        userService.getById(defaultUser.getId());  //TODO: a voir

//        ArrayList<Reservation> reservations = reservationService.getAllById();

//        System.out.println(reservations);
    }

    // 4a. utilisateur --- page acceuil -- réservations proposées.
    @Test
    public void AfficherPageAccueil() {
        userService.insert(defaultUser);
        userService.getById(defaultUser.getId()); // on est censé déjà avoir l'utilisateur une fois logé (je crois).

        ArrayList<Reservation> reservations = reservationService.getAll();

        System.out.println(reservations);
    }

    // 4b. utilisateur -- recherche -- réservation
    @Test
    public void PageAccueilRechercheReservation() {
        userService.insert(defaultUser);
        userService.getById(defaultUser.getId());

        reservationService.getBySearchTerm("2");
    }

    // 5.  utilisteur -- rejoindre réservation
    @Test
    public void UtilisateurRejoindreReservation() {
        System.out.println("toto");

    }

    // 2a) ---- Cas utilisateur - supprimer
    // 1. utilisateur -- supprime -- reservation
    @Test
    public void UtilisateurSupprimerDeReservation() {
        System.out.println("toto");

    }

}
