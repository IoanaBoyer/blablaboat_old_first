package nc.blablaboat.application.dao;

import nc.blablaboat.application.model.User;
import org.junit.jupiter.api.Test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


public class UserDAOTest {
    /**
     * Le DAO à tester
     */
    private final UserDAO userDAO = new UserDAO();

    @Test
    public void testInsert(){
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            userDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel utilisateur avec un ID unique
            User user = new User(uniqueID, "testNickname", "testLastname", "testFirstname",
                    25, "testPassword", true);

            // Insérer l'utilisateur dans la BDD
            userDAO.insert(user);

            // Vérifier que l'utilisateur est bien inséré dans la BDD
            User insertedUser = userDAO.getById(uniqueID);
            assertNotNull(insertedUser, "L'utilisateur n'a pas été trouvé dans la BDD.");

            // Vérifier que l'ID correspond à celui généré
            assertEquals(uniqueID, insertedUser.getId(), "L'ID de l'utilisateur inséré ne correspond pas à l'ID attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Annuler les opérations effectuées pendant ce test
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                userDAO.getCONNECTION().setAutoCommit(true);
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
            userDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel utilisateur avec cet ID
            User user = new User(uniqueID, "testNickname", "testLastname", "testFirstname",
                    25, "testPassword", true);

            // Insérer l'utilisateur dans la BDD
            userDAO.insert(user);

            // Modifier les attributs de l'utilisateur
            user.setNickname("updatedNickname");
            user.setLastname("updatedLastname");
            user.setFirstname("updatedFirstname");
            user.setAge(30);
            user.setPassword("updatedPassword");
            user.setDriver(false);

            // Mettre à jour l'utilisateur dans la BDD
            userDAO.update(user);

            // Vérifier que l'utilisateur est bien mis à jour dans la BDD
            User updatedUser = userDAO.getById(uniqueID);
            assertNotNull(updatedUser, "L'utilisateur mis à jour n'a pas été trouvé dans la BDD.");
            assertEquals("updatedNickname", updatedUser.getNickname(), "Le pseudo de l'utilisateur mis à jour ne correspond pas à l'attendu.");
            assertEquals("updatedLastname", updatedUser.getLastname(), "Le nom de famille de l'utilisateur mis à jour ne correspond pas à l'attendu.");
            assertEquals("updatedFirstname", updatedUser.getFirstname(), "Le prénom de l'utilisateur mis à jour ne correspond pas à l'attendu.");
            assertEquals(30, updatedUser.getAge(), "L'âge de l'utilisateur mis à jour ne correspond pas à l'attendu.");
            assertEquals("updatedPassword", updatedUser.getPassword(), "Le mot de passe de l'utilisateur mis à jour ne correspond pas à l'attendu.");
            assertFalse(updatedUser.getDriver(), "Le statut de conducteur de l'utilisateur mis à jour ne correspond pas à l'attendu.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                userDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testDelete() {
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            userDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel utilisateur avec cet ID
            User user = new User(uniqueID, "testUser", "testLastname", "testFirstname",
                    25, "testPassword", true);

            // Insérer l'utilisateur dans la BDD
            userDAO.insert(user);

            // Supprimer l'utilisateur dans la BDD
            userDAO.delete(uniqueID);

            // Tenter de récupérer l'utilisateur supprimé
            User deletedUser = userDAO.getById(uniqueID);

            // Vérifier que l'utilisateur a bien été supprimé et n'existe plus dans la BDD
            assertNull(deletedUser, "L'utilisateur n'a pas été correctement supprimé de la BDD.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                userDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetByIdUser() {
        // Générer un ID unique pour éviter les collisions dans les tests
        String uniqueID = UUID.randomUUID().toString();

        try {
            // Commencer une transaction
            userDAO.getCONNECTION().setAutoCommit(false);

            // Créer un nouvel utilisateur avec cet ID
            User user = new User(uniqueID, "testUser", "testLastname", "testFirstname",
                    25, "testPassword", true);

            // Insérer l'utilisateur dans la BDD
            userDAO.insert(user);

            // Récupérer l'utilisateur par ID
            User retrievedUser = userDAO.getById(uniqueID);

            // Vérifier que l'utilisateur récupéré n'est pas null
            assertNotNull(retrievedUser, "L'utilisateur récupéré ne devrait pas être null.");

            // Vérifier que l'ID de l'utilisateur récupéré correspond à l'ID attendu
            assertEquals(uniqueID, retrievedUser.getId(), "L'ID de l'utilisateur récupéré ne correspond pas à l'ID attendu.");

            // Tester la récupération avec un ID non existant
            User nonExistentUser = userDAO.getById(UUID.randomUUID().toString());

            // Vérifier que l'utilisateur récupéré est bien null
            assertNull(nonExistentUser, "Aucun utilisateur ne devrait être récupéré avec un ID non existant.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique si nécessaire
                userDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetBySearchTerm() {
        // Définir un terme de recherche
        String searchTerm = "TestUser";

        try {
            // Commencer une transaction
            userDAO.getCONNECTION().setAutoCommit(false);

            // Créer et insérer des utilisateurs avec et sans le terme de recherche dans leur nom
            User userWithSearchTermInNickname = new User(UUID.randomUUID(), searchTerm + "Nickname",
                    "Doe", "John", 25, "password123", false);
            User userWithSearchTermInLastName = new User(UUID.randomUUID(), "Jane", searchTerm + "Doe",
                    "Jane", 30, "password123", true);
            User userWithoutSearchTerm = new User(UUID.randomUUID(), "AnotherNickname", "Doe",
                    "John", 20, "password123", true);

            // Insérer les utilisateurs dans la base de données
            userDAO.insert(userWithSearchTermInNickname);
            userDAO.insert(userWithSearchTermInLastName);
            userDAO.insert(userWithoutSearchTerm);

            // Rechercher les utilisateurs avec le terme de recherche
            ArrayList<User> usersFound = userDAO.getBySearchTerm(searchTerm);

            // Vérifier que les utilisateurs avec le terme de recherche dans le nickname ou lastname sont retournés
            assertTrue(usersFound.stream().anyMatch(user -> user.getNickname().contains(searchTerm)),
                    "Le terme de recherche devrait correspondre au nickname.");
            assertTrue(usersFound.stream().anyMatch(user -> user.getLastname().contains(searchTerm)),
                    "Le terme de recherche devrait correspondre au lastname.");

            // Vérifier que l'utilisateur sans le terme de recherche n'est pas retourné
            assertFalse(usersFound.stream().anyMatch(user -> user.getId().equals(userWithoutSearchTerm.getId())),
                    "L'utilisateur sans le terme de recherche ne devrait pas être retourné.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Rollback la transaction pour annuler les opérations effectuées pendant ce test
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                userDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testGetAll() {
        // Générer des identifiants uniques pour éviter les collisions
        UUID uniqueID1 = UUID.randomUUID();
        UUID uniqueID2 = UUID.randomUUID();

        // Créer deux utilisateurs avec des identifiants uniques
        User user1 = new User(uniqueID1, "User1", "Lastname1", "Firstname1", 25,
                "pass1", false);
        User user2 = new User(uniqueID2, "User2", "Lastname2", "Firstname2", 30,
                "pass2", true);

        try {
            // Commencer une transaction pour éviter de persister les tests dans la BDD
            userDAO.getCONNECTION().setAutoCommit(false);

            // Insérer les utilisateurs dans la BDD
            userDAO.insert(user1);
            userDAO.insert(user2);

            // Utiliser getAll pour récupérer tous les utilisateurs
            ArrayList<User> users = userDAO.getAll();

            // Vérifier que nous avons récupéré au moins les deux utilisateurs que nous avons insérés
            assertTrue(users.size() >= 2, "Il devrait y avoir au moins deux utilisateurs récupérés.");

            // Vérifier que les utilisateurs insérés sont présents dans les résultats
            assertTrue(users.stream().anyMatch(u -> u.getId().equals(uniqueID1.toString())),
                    "Le premier utilisateur inséré devrait être présent dans les résultats.");
            assertTrue(users.stream().anyMatch(u -> u.getId().equals(uniqueID2.toString())),
                    "Le second utilisateur inséré devrait être présent dans les résultats.");

        } catch (SQLException e) {
            fail("Une exception SQL a été levée: " + e.getMessage());
        } finally {
            try {
                // Annuler toutes les modifications réalisées dans cette transaction
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                userDAO.getCONNECTION().setAutoCommit(true);
            } catch (SQLException e) {
                fail("Une exception SQL a été levée lors du rollback: " + e.getMessage());
            }
        }
    }

    @Test
    public void testCreateFromResultSet() {
        // Générer des identifiants uniques pour éviter les collisions
        UUID uniqueID1 = UUID.randomUUID();
        UUID uniqueID2 = UUID.randomUUID();

        // Créer deux objets User avec des identifiants uniques
        User user1 = new User(uniqueID1, "Nickname1", "Lastname1", "Firstname1", 25,
                "Password1", false);
        User user2 = new User(uniqueID2, "Nickname2", "Lastname2", "Firstname2", 30,
                "Password2", true);

        try {
            userDAO.getCONNECTION().setAutoCommit(false);

            // Insérer des données de test dans la base de données
            userDAO.insert(user1);
            userDAO.insert(user2);

            // Récupérer les données pour le test
            String query = "SELECT * FROM user WHERE id IN (?, ?) ORDER BY id";
            try (PreparedStatement preparedStatement = userDAO.getCONNECTION().prepareStatement(query)) {
                preparedStatement.setString(1, uniqueID1.toString());
                preparedStatement.setString(2, uniqueID2.toString());
                ResultSet resultSet = preparedStatement.executeQuery();

                // Supposer que le premier User inséré est le premier récupéré
                assertTrue(resultSet.next(), "Le premier utilisateur n'a pas été trouvé dans la BDD.");
                User userResult1 = userDAO.createFromResultSet(resultSet);
                assertEquals(uniqueID1.toString(), userResult1.getId(),
                        "L'ID du premier utilisateur ne correspond pas.");
                assertEquals("Nickname1", userResult1.getNickname(),
                        "Le nickname du premier utilisateur ne correspond pas.");

                // Supposer que le second User inséré est le second récupéré
                assertTrue(resultSet.next(), "Le second utilisateur n'a pas été trouvé dans la BDD.");
                User userResult2 = userDAO.createFromResultSet(resultSet);
                assertEquals(uniqueID2.toString(), userResult2.getId(),
                        "L'ID du second utilisateur ne correspond pas.");
                assertEquals("Nickname2", userResult2.getNickname(),
                        "Le nickname du second utilisateur ne correspond pas.");

                // Vérifier qu'il n'y a pas d'autres utilisateurs dans le ResultSet
                assertFalse(resultSet.next(), "Il ne devrait y avoir que deux utilisateurs dans la BDD.");

            } catch (SQLException e) {
                fail("Erreur lors de l'exécution de la requête: " + e.getMessage());
            } finally {
                // Rollback pour ne pas affecter les autres tests
                userDAO.getCONNECTION().rollback();
                // Remettre la connexion en mode de commit automatique
                userDAO.getCONNECTION().setAutoCommit(true);
            }
        } catch (SQLException e) {
            fail("Une erreur SQL est survenue: " + e.getMessage());
        }
    }
}
