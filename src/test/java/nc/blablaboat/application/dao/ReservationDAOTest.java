package nc.blablaboat.application.dao;

import nc.blablaboat.application.model.Stop;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.model.Reservation;
import org.junit.jupiter.api.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        // Créer des objets fictifs pour Stop et User
        Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
        Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
        User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                "driver@example.com", "password");
        reservationDAO.getStopDAO().insert(depart);
        reservationDAO.getStopDAO().insert(arrivee);
        reservationDAO.getUserDAO().insert(conducteur);

        // Créer une nouvelle réservation avec l'ID unique et les objets fictifs
        Reservation reservation = new Reservation(
                reservationId,
                depart,
                arrivee,
                new Date(),
                new Date(),
                1,
                100,
                "Pas de spécifications",
                new ArrayList<>(),
                conducteur
        );

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Insérer les objets Stop fictifs dans la base de données
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);

            // Insérer l'objet User fictif dans la base de données
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
        UUID reservationId = UUID.randomUUID();
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Créer des objets fictifs pour Stop et User
            Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
            Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
            User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                    "driver@example.com", "password");

            // Supposons que stopDAO et userDAO sont les DAOs pour Stop et User
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);
            reservationDAO.getUserDAO().insert(conducteur);

            // Créer une nouvelle réservation avec cet ID (les détails sont fictifs)
            Reservation reservation = new Reservation(
                    reservationId,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            // Insérer la réservation dans la BDD
            reservationDAO.insert(reservation);

            // Modifier les attributs de la réservation
            reservation.setDepart(arrivee);
            reservation.setArrivee(depart);
            reservation.setTarifUnitaire(150);
            reservation.setNbPassager(2);

            // Mettre à jour la réservation dans la BDD
            reservationDAO.update(reservation);

            // Vérifier que la réservation est bien mise à jour dans la BDD
            Reservation updatedReservation = reservationDAO.getById(reservationId.toString());
            assertNotNull(updatedReservation, "La réservation mise à jour n'a pas été trouvée dans la BDD.");
            assertEquals(arrivee.getId(), updatedReservation.getDepart().getId(), "L'ID du départ de la" +
                    " réservation mise à jour ne correspond pas à l'attendu.");
            assertEquals(depart.getId(), updatedReservation.getArrivee().getId(), "L'ID de l'arrivée de" +
                    " la réservation mise à jour ne correspond pas à l'attendu.");
            assertEquals(150, updatedReservation.getTarifUnitaire(), "Le tarif de la réservation" +
                    " mise à jour ne correspond pas à l'attendu.");
            assertEquals(2, updatedReservation.getNbPassager(), "Le nombre de passagers de la" +
                    " réservation mise à jour ne correspond pas à l'attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                reservationDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testDelete(){
        // Générer un ID unique pour éviter les collisions dans les tests
        UUID reservationId = UUID.randomUUID();
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);
            // Créer des objets fictifs pour Stop et User
            Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
            Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
            User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                    "driver@example.com", "password");
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);
            reservationDAO.getUserDAO().insert(conducteur);

            // Créer une nouvelle réservation avec cet ID (les détails sont fictifs)
            Reservation reservation = new Reservation(
                    reservationId,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            // Insérer la réservation dans la BDD
            reservationDAO.insert(reservation);

            // Supprimer la réservation dans la BDD
            reservationDAO.delete(reservationId.toString());

            // Tenter de récupérer la réservation supprimée
            Reservation deletedReservation = reservationDAO.getById(reservationId.toString());

            // Vérifier que la réservation a bien été supprimée et n'existe plus dans la BDD
            assertNull(deletedReservation, "La réservation n'a pas été correctement supprimée de la BDD.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                reservationDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetById(){
        // Générer un ID unique pour éviter les collisions dans les tests
        UUID reservationId = UUID.randomUUID();
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);
            // Créer des objets fictifs pour Stop et User
            Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
            Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
            User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                    "driver@example.com", "password");
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);
            reservationDAO.getUserDAO().insert(conducteur);

            // Créer une nouvelle réservation avec cet ID (les détails sont fictifs)
            Reservation reservation = new Reservation(
                    reservationId,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            // Insérer la réservation dans la BDD
            reservationDAO.insert(reservation);

            // Récupérer la réservation par son ID
            Reservation retrievedReservation = reservationDAO.getById(reservationId.toString());

            // Vérifier que la réservation récupérée n'est pas nulle
            assertNotNull(retrievedReservation, "La réservation n'a pas été trouvée par son ID.");

            // Vérifier que l'ID de la réservation récupérée correspond à l'ID attendu
            assertEquals(reservationId.toString(), retrievedReservation.getId(), "L'ID de" +
                    " la réservation récupérée ne correspond pas à l'attendu.");


        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                reservationDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetAll() {
        // Générer un ID unique pour éviter les collisions dans les tests
        UUID reservation1Id = UUID.randomUUID();
        UUID reservation2Id = UUID.randomUUID();
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);
            // Créer des objets fictifs pour Stop et User
            Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
            Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
            User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                    "driver@example.com", "password");
            reservationDAO.getStopDAO().insert(depart);
            reservationDAO.getStopDAO().insert(arrivee);
            reservationDAO.getUserDAO().insert(conducteur);

            // Créer 2 nouvelles réservations
            Reservation reservation1 = new Reservation(
                    reservation1Id,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );
            Reservation reservation2= new Reservation(
                    reservation2Id,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            // Insérer les deux réservations dans la base de données
            reservationDAO.insert(reservation1);
            reservationDAO.insert(reservation2);

            // Récupérer toutes les réservations et vérifier qu'elles sont correctement récupérées
            ArrayList<Reservation> allReservations = reservationDAO.getAll();
            assertNotNull(allReservations, "La liste des réservations ne devrait pas être null.");
            assertTrue(allReservations.size() >= 2, "Il devrait y avoir au moins deux réservations dans la liste.");

            // Vérifiez la présence des deux réservations que vous avez insérées par leur ID
            assertTrue(allReservations.stream().anyMatch(r -> r.getId().equals(reservation1Id.toString())),
                    "La première réservation n'a pas été trouvée dans la liste.");
            assertTrue(allReservations.stream().anyMatch(r -> r.getId().equals(reservation2Id.toString())),
                    "La seconde réservation n'a pas été trouvée dans la liste.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour ne pas conserver les données de test
                reservationDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetReservationsBySearchTerm() {
        // Définir un terme de recherche qui sera utilisé pour le nom de l'arrêt
        String searchTerm = "TestArrivee";

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Créer et insérer des arrêts avec et sans le terme de recherche dans leur nom
            Stop stopWithSearchTerm = new Stop(UUID.randomUUID(), searchTerm + " Station",
                    0.0, 0.0);
            Stop stopWithoutSearchTerm = new Stop(UUID.randomUUID(), "Another Station",
                    0.0, 0.0);
            reservationDAO.getStopDAO().insert(stopWithSearchTerm);
            reservationDAO.getStopDAO().insert(stopWithoutSearchTerm);

            // Créer un utilisateur fictif pour être le conducteur
            User conducteur = new User(UUID.randomUUID(), "TestConducteur", "Driver",
                    "driver@example.com", "password");
            reservationDAO.getUserDAO().insert(conducteur);

            // Créer des réservations avec les arrêts ci-dessus
            Reservation reservationWithSearchTerm = new Reservation(
                    UUID.randomUUID(),
                    new Stop(),
                    stopWithSearchTerm,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );
            Reservation reservationWithoutSearchTerm = new Reservation(
                    UUID.randomUUID(),
                    new Stop(),
                    stopWithoutSearchTerm,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            reservationDAO.insert(reservationWithSearchTerm);
            reservationDAO.insert(reservationWithoutSearchTerm);

            // Rechercher les réservations avec le terme de recherche
            ArrayList<Reservation> reservationsFound = reservationDAO.getBySearchTerm(searchTerm);

            // Vérifier que la réservation avec le terme de recherche dans le nom de l'arrêt d'arrivée est retournée
            assertTrue(reservationsFound.stream().anyMatch(r -> r.getArrivee().getName().contains(searchTerm)),
                    "Le terme de recherche devrait correspondre au nom de l'arrêt d'arrivée.");

            // Vérifier que la réservation sans le terme de recherche n'est pas retournée
            assertFalse(reservationsFound.stream().anyMatch(r -> r.getId().equals(reservationWithoutSearchTerm.getId())),
                    "La réservation sans le terme de recherche ne devrait pas être retournée.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour ne pas conserver les données de test
                reservationDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                reservationDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testCreateFromResultSet() {
        // Générer des ID uniques pour éviter les collisions dans les tests
        UUID reservationId = UUID.randomUUID();
        UUID departId = UUID.randomUUID();
        UUID arriveeId = UUID.randomUUID();
        UUID conducteurId = UUID.randomUUID();

        try {
            // Commencer une transaction
            reservationDAO.getCONNECTION().setAutoCommit(false);

            // Créer des objets fictifs pour Stop et User
            Stop depart = new Stop(departId.toString(), "TestDepart", 0.0, 0.0);
            Stop arrivee = new Stop(arriveeId.toString(), "TestArrivee", 0.0, 0.0);
            User conducteur = new User(conducteurId, "TestConducteur", "Driver",
                    "driver@example.com", "password");

            // Créer une nouvelle réservation avec cet ID (les détails sont fictifs)
            Reservation reservation = new Reservation(
                    reservationId,
                    depart,
                    arrivee,
                    new Date(),
                    new Date(),
                    1,
                    100,
                    "Pas de spécifications",
                    new ArrayList<>(),
                    conducteur
            );

            // Insérer la réservation dans la base de données
            reservationDAO.insert(reservation);

            // Récupérer la réservation pour le test
            String query = "SELECT * FROM reservation WHERE id = ?";
            try (PreparedStatement preparedStatement = reservationDAO.getCONNECTION().prepareStatement(query)) {
                preparedStatement.setString(1, reservationId.toString());
                ResultSet resultSet = preparedStatement.executeQuery();

                // Vérifier que la réservation est récupérée
                assertTrue(resultSet.next(), "La réservation n'a pas été trouvée dans la BDD.");
                Reservation reservationResult = reservationDAO.createFromResultSet(resultSet);
                assertEquals(reservationId.toString(), reservationResult.getId());
                // Vérifiez ici les autres attributs de reservationResult

                // Vérifier qu'il n'y a pas d'autres réservations dans le ResultSet
                assertFalse(resultSet.next(), "Il ne devrait y avoir qu'une seule réservation dans le ResultSet.");

            } catch (SQLException e) {
                fail("Erreur lors de l'exécution de la requête: " + e.getMessage());
            } finally {
                // Rollback pour ne pas affecter les autres tests
                reservationDAO.getCONNECTION().rollback();
                reservationDAO.getCONNECTION().setAutoCommit(true);
            }
        } catch (SQLException e) {
            fail("Une erreur SQL est survenue: " + e.getMessage());
        }
    }


}
