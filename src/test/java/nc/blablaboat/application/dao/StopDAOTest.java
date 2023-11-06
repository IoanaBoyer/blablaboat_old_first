package nc.blablaboat.application.dao;

import nc.blablaboat.application.model.Stop;
import org.junit.jupiter.api.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;

public class StopDAOTest {
    /**
     * Le DAO à tester
     */
    private final StopDAO stopDAO = new StopDAO();

    @Test
    public void testInsert(){
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel arrêt avec cet ID
            Stop stop = new Stop(uniqueID, "testInsert", 0.0, 0.0);

            // Insérer l'arrêt dans la BDD
            stopDAO.insert(stop);

            // Vérifier que l'arrêt est bien inséré dans la BDD
            Stop insertedStop = stopDAO.getById(uniqueID);
            assertNotNull(insertedStop, "L'arrêt n'a pas été trouvé dans la BDD.");

            // Vérifier que l'ID correspond à celui créé
            assertEquals(uniqueID, insertedStop.getId(), "L'ID de l'arrêt inséré ne correspond pas à l'ID attendu.");


        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                stopDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testUpdate(){
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel arrêt avec cet ID
            Stop stop = new Stop(uniqueID, "testUpdate", 0.0, 0.0);

            // Insérer l'arrêt dans la BDD
            stopDAO.insert(stop);

            // Modifier les attributs de l'arrêt
            stop.setName("testUpdated");
            stop.setLongitude(1.0);
            stop.setLatitude(1.0);

            // Mettre à jour l'arrêt dans la BDD
            stopDAO.update(stop);

            // Vérifier que l'arrêt est bien mis à jour dans la BDD
            Stop updatedStop = stopDAO.getById(uniqueID);
            assertNotNull(updatedStop, "L'arrêt mis à jour n'a pas été trouvé dans la BDD.");
            assertEquals("testUpdated", updatedStop.getName(), "Le nom de l'arrêt mis à jour ne correspond pas à l'attendu.");
            assertEquals(1.0, updatedStop.getLongitude(), "La longitude de l'arrêt mis à jour ne correspond pas à l'attendu.");
            assertEquals(1.0, updatedStop.getLatitude(), "La latitude de l'arrêt mis à jour ne correspond pas à l'attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                stopDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testDelete(){
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel arrêt avec cet ID
            Stop stop = new Stop(uniqueID, "testDelete", 0.0, 0.0);

            // Insérer l'arrêt dans la BDD
            stopDAO.insert(stop);

            // Supprimer l'arrêt dans la BDD
            stopDAO.delete(uniqueID);

            // Tenter de récupérer l'arrêt supprimé
            Stop deletedStop = stopDAO.getById(uniqueID);

            // Vérifier que l'arrêt a bien été supprimé et n'existe plus dans la BDD
            assertNull(deletedStop, "L'arrêt n'a pas été correctement supprimé de la BDD.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                stopDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetById(){
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel arrêt avec cet ID
            Stop stop = new Stop(uniqueID, "testGetById", 0.0, 0.0);

            // Insérer l'arrêt dans la BDD
            stopDAO.insert(stop);

            // Récupérer l'arrêt par ID
            Stop retrievedStop = stopDAO.getById(uniqueID);

            // Vérifier que l'arrêt récupéré n'est pas null
            assertNotNull(retrievedStop, "L'arrêt récupéré ne devrait pas être null.");

            // Vérifier que l'ID de l'arrêt récupéré correspond à l'ID attendu
            assertEquals(uniqueID, retrievedStop.getId(), "L'ID de l'arrêt récupéré ne correspond pas à l'ID attendu.");

            // Tester la récupération avec un ID non existant
            Stop nonExistentStop = stopDAO.getById(UUID.randomUUID().toString());

            // Vérifier que l'arrêt récupéré est bien null
            assertNull(nonExistentStop, "Aucun arrêt ne devrait être récupéré avec un ID non existant.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                stopDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetAll() {
        // Générer des identifiants uniques pour éviter les collisions
        String uniqueID1 = UUID.randomUUID().toString();
        String uniqueID2 = UUID.randomUUID().toString();

        Stop stop1 = new Stop(uniqueID1, "Test Stop 1", -123.456, 45.678);
        Stop stop2 = new Stop(uniqueID2, "Test Stop 2", -65.432, 10.111);

        try {
            // Commencer une transaction pour éviter de persister les tests dans la BDD
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Insérer les arrêts dans la BDD
            stopDAO.insert(stop1);
            stopDAO.insert(stop2);

            // Utiliser getAll pour récupérer tous les arrêts
            ArrayList<Stop> stops = stopDAO.getAll();

            // Vérifier que nous avons récupéré au moins les deux arrêts que nous avons insérés
            assertTrue(stops.size() >= 2, "Il devrait y avoir au moins deux arrêts récupérés.");

            // Vérifier que les arrêts insérés sont présents dans les résultats
            assertTrue(stops.stream().anyMatch(s -> s.getId().equals(uniqueID1)), "Le premier arrêt inséré devrait être présent dans les résultats.");
            assertTrue(stops.stream().anyMatch(s -> s.getId().equals(uniqueID2)), "Le second arrêt inséré devrait être présent dans les résultats.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Annuler toutes les modifications réalisées dans cette transaction
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                stopDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testCreateFromResultSet() {
        String uniqueID1 = UUID.randomUUID().toString();
        String uniqueID2 = UUID.randomUUID().toString();

        Stop stop1 = new Stop(uniqueID1, "Test Stop 1", -123.456, 45.678);
        Stop stop2 = new Stop(uniqueID2, "Test Stop 2", -65.432, 10.111);

        try {
            stopDAO.getCONNECTION().setAutoCommit(false);

            // Insérer des données de test dans la base de données
            stopDAO.insert(stop1);
            stopDAO.insert(stop2);

            // Récupérer les données pour le test
            String query = "SELECT * FROM stop WHERE id IN (?, ?) ORDER BY id";
            try (PreparedStatement preparedStatement = stopDAO.getCONNECTION().prepareStatement(query)) {
                preparedStatement.setString(1, uniqueID1);
                preparedStatement.setString(2, uniqueID2);
                ResultSet resultSet = preparedStatement.executeQuery();

                // Supposer que le premier Stop inséré est le premier récupéré
                assertTrue(resultSet.next(), "Le premier arrêt n'a pas été trouvé dans la BDD.");
                Stop stopResult1 = stopDAO.createFromResultSet(resultSet);
                assertEquals(uniqueID1, stopResult1.getId());
                assertEquals("Test Stop 1", stopResult1.getName());
                assertEquals(-123.456, stopResult1.getLongitude());
                assertEquals(45.678, stopResult1.getLatitude());

                // Supposer que le second Stop inséré est le second récupéré
                assertTrue(resultSet.next(), "Le second arrêt n'a pas été trouvé dans la BDD.");
                Stop stopResult2 = stopDAO.createFromResultSet(resultSet);
                assertEquals(uniqueID2, stopResult2.getId());
                assertEquals("Test Stop 2", stopResult2.getName());
                assertEquals(-65.432, stopResult2.getLongitude());
                assertEquals(10.111, stopResult2.getLatitude());

                // Vérifier qu'il n'y a pas d'autres arrêts dans le ResultSet
                assertFalse(resultSet.next(), "Il ne devrait y avoir que deux arrêts dans la BDD.");

            } catch (SQLException e) {
                fail("Erreur lors de l'exécution de la requête: " + e.getMessage());
            } finally {
                // Rollback pour ne pas affecter les autres tests
                stopDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                stopDAO.getCONNECTION().setAutoCommit(true);
            }
        } catch (SQLException e) {
            fail("Une erreur SQL est survenue: " + e.getMessage());
        }
    }

}
