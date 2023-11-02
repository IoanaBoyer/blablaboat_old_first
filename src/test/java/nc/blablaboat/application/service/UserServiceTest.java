package nc.blablaboat.application.service;

import nc.blablaboat.application.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    public void testCreateUser() {
        // When
        User user = new User(UUID.randomUUID(), "john_doe", "Doe", "John", 35, "password123", true);
        userService.insert(user);

        // Then
        assertNotNull(user.getId());
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User(UUID.randomUUID(), "jane_smith", "Smith", "Jane", 28, "securePassword", false);
        userService.insert(user);

        // When
        user = new User(user.getId(), "jane_smith", "Smith", "Jane", 29, "securePassword", true);
        userService.update(user);

        // Then
        User updatedUser = userService.consultUserProfil(user.getId());
        assertEquals(29, updatedUser.getAge());
        assertEquals(true, updatedUser.getDriver());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User(UUID.randomUUID(), "test_user", "Test", "User", 30, "test123", true);
        userService.insert(user);

        // When
        userService.delete(user.getId());

        // Then
        User deletedUser = userService.consultUserProfil(user.getId());
        assertNull(deletedUser);
    }

    @Test
    public void testGetUserById() {
        // Given
        User user = new User(UUID.randomUUID(), "test_user", "Test", "User", 30, "test123", true);
        userService.insert(user);

        // When
        User retrievedUser = userService.getById(user.getId());

        // Then
        assertNotNull(retrievedUser);
        assertEquals(user.getId(), retrievedUser.getId());
        assertEquals(user.getNickname(), retrievedUser.getNickname());
        // Assurez-vous de vérifier d'autres propriétés pertinentes ici.
    }

    //TODO: attention la base de donnée gardes les anciennes entrée, "falsifie" dans le test
    @Test
    public void testGetUserBySearchTerm() {
        // Given
        // Créez un ou plusieurs utilisateurs correspondant au terme de recherche
        User user1 = new User(UUID.randomUUID(), "search_user1", "Search", "User1", 25, "search123", true);
        User user2 = new User(UUID.randomUUID(), "search_user2", "Search", "User2", 30, "search456", false);
        userService.insert(user1);
        userService.insert(user2);

        // When
        ArrayList<User> searchResults = userService.getBySearchTerm("Search");

        // Then
        assertNotNull(searchResults);
        assertEquals(2, searchResults.size()); // Vérifiez le nombre d'utilisateurs correspondant au terme de recherche.
        // Assurez-vous de vérifier d'autres propriétés pertinentes des utilisateurs dans les résultats.
    }

    @Test
    public void testUserAttributes() {
        // Given
        UUID id = UUID.randomUUID();
        String nickname = "test_user";
        String lastname = "Test";
        String firstname = "User";
        int age = 30;
        String password = "test123";
        boolean isDriver = true;

        // When
        User userOriginal = new User(id, nickname, lastname, firstname, age, password, isDriver);
        userService.insert(userOriginal);
        User user = userService.getById(userOriginal.getId());

        // Then
        assertNotNull(user.getId());
        assertEquals(id.toString(), user.getId());
        assertEquals(nickname, user.getNickname());
        assertEquals(lastname, user.getLastname());
        assertEquals(firstname, user.getFirstname());
        assertEquals(age, user.getAge());
        assertEquals(password, user.getPassword());
        assertEquals(isDriver, user.getDriver());
    }

    @Test
    public void testUserConstructorOverloads() {
        // Given
        String id = UUID.randomUUID().toString();
        String nickname = "test_user";
        String lastname = "Test";
        String firstname = "User";
        int age = 30;
        String password = "test123";
        boolean isDriver = true;

        // When
        User user1 = new User(UUID.fromString(id), nickname, lastname, firstname, age, password, isDriver);
        User user2 = new User(id, nickname, lastname, firstname, age, password, isDriver);
        User user3 = new User(nickname, lastname, firstname, age, password, isDriver);

        // Then
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
        assertNotNull(user3.getId());
        // Verify that user1, user2, and user3 have the same attribute values.
        assertEquals(user1.getNickname(), user2.getNickname(), user3.getNickname());
        assertEquals(user1.getAge(), user2.getAge(), user3.getAge());
        // You can continue to verify other attributes as needed.
    }
}
