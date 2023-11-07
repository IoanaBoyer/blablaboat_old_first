package nc.blablaboat.application.dao;

import nc.blablaboat.application.model.Stop;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.model.Reservation;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class ReservationDAOTest {
    /**
     * Le DAO à tester
     */
    private final ReservationDAO reservationDAO = new ReservationDAO();

    @Test
    public void testInsert() {
        // Générer un ID unique pour éviter les collisions lors des tests
        UUID reservationId = UUID.randomUUID();
        UUID departId = UUID.randomUUID(); // Assumer que vous avez un ID valide d'arrêt pour le test
        UUID arriveeId = UUID.randomUUID(); // Assumer que vous avez un ID valide d'arrêt pour le test
        UUID conducteurId = UUID.randomUUID(); // Assumer que vous avez un ID valide d'utilisateur pour le test

        // Créer des objets fictifs pour Stop et User
        Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
        Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
        User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                "driver@example.com", "password");

        // Créer une nouvelle réservation avec l'ID unique et les objets fictifs
        Reservation reservation = new Reservation(
                reservationId,
                depart,
                arrivee,
                new Date(), // Utiliser la date actuelle pour le départ
                new Date(), // Utiliser la date actuelle pour l'arrivée
                1, // Nombre de passagers
                100, // Tarif par passager
                "Pas de spécifications", // Spécifications
                new ArrayList<>(), // Passagers
                conducteur // Conducteur
        );

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Insérer les objets Stop fictifs dans la base de données (présumé être une partie de l'installation du test)
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);

            // Insérer l'objet User fictif dans la base de données (présumé être une partie de l'installation du test)
            reservationDAO.getUserDAO().insert(conducteur);

            // Insérer la réservation dans la base de données
            reservationDAO.insert(reservation);

            // Vérifier que la réservation est correctement insérée dans la base de données
            Reservation insertedReservation = reservationDAO.getById(reservationId.toString());
            assertNotNull(insertedReservation, "La réservation n'a pas été trouvée dans la base de données.");

            // Vérifier que les détails correspondent à ceux qui ont été insérés
            assertEquals(reservationId.toString(), insertedReservation.getId(),
                    "L'ID de la réservation insérée ne correspond pas à l'ID attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée : " + e.getMessage());
        } finally {
            try {
                // Annuler la transaction pour annuler les opérations réalisées pendant ce test
                reservationDAO.getCONNECTION().rollback();
                // Réinitialiser la connexion au mode de commit automatique si nécessaire
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback : " + e.getMessage());
            }
        }
    }

    @Test
    public void testUpdate(){
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel arrêt avec cet ID
            Stop stop = new Stop(uniqueID, "testUpdate", 0.0, 0.0);

            // Insérer l'arrêt dans la BDD
            reservationDAO.getStopDAO().insert(stop);

            // Modifier les attributs de l'arrêt
            stop.setName("testUpdated");
            stop.setLongitude(1.0);
            stop.setLatitude(1.0);

            // Mettre à jour l'arrêt dans la BDD
            reservationDAO.getStopDAO().update(stop);

            // Vérifier que l'arrêt est bien mis à jour dans la BDD
            Stop updatedStop = reservationDAO.getStopDAO().getById(uniqueID);
            assertNotNull(updatedStop, "L'arrêt mis à jour n'a pas été trouvé dans la BDD.");
            assertEquals("testUpdated", updatedStop.getName(), "Le nom de l'arrêt mis à jour ne correspond pas à l'attendu.");
            assertEquals(1.0, updatedStop.getLongitude(), "La longitude de l'arrêt mis à jour ne correspond pas à l'attendu.");
            assertEquals(1.0, updatedStop.getLatitude(), "La latitude de l'arrêt mis à jour ne correspond pas à l'attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                reservationDAO.getStopDAO().getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                reservationDAO.getStopDAO().getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

}
