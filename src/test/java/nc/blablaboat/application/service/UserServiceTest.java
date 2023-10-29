package nc.blablaboat.application.service;

import nc.blablaboat.application.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
    private UserService userService;

    @Test
    public void testCreateUser() {
        // Given
        User user = new User();
        user.setNickname("john_doe");
        user.setLastname("Doe");
        user.setFirstname("John");
        user.setAge(35);
        user.setPassword("password123");

        // When
        userService.insert(user);

        // Then
        assertNotNull(user.getId());
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User();
        user.setNickname("jane_smith");
        user.setLastname("Smith");
        user.setFirstname("Jane");
        user.setAge(28);
        user.setPassword("securePassword");

        userService.insert(user);

        // When
        user.setAge(29);
        userService.update(user);

        // Then
        User updatedUser = userService.consultUserProfil(user.getId());
        assertEquals(29, updatedUser.getAge());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User();
        user.setNickname("test_user");
        user.setLastname("Test");
        user.setFirstname("User");
        user.setAge(30);
        user.setPassword("test123");

        userService.insert(user);

        // When
        userService.delete(user.getId());

        // Then
        User deletedUser = userService.consultUserProfil(user.getId());
        assertEquals(null, deletedUser);
    }
}
