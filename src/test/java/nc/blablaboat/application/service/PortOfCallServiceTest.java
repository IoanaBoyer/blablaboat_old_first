package nc.blablaboat.application.service;

import nc.blablaboat.application.model.Arret;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PortOfCallServiceTest {
    @Test
    public void testArretIdGeneration() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilot signal";

        // When
        Arret arret = new Arret(name,longitude, latitude);

        // Then
        assertNotNull(arret.getId());
    }

    @Test
    public void testArretIdNotNull() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        UUID id = UUID.randomUUID();
        String name = "Ilôt maitre";

        // When
        Arret arret = new Arret(id, name, longitude, latitude);

        // Then
        assertEquals(id.toString(), arret.getId());
    }

    @Test
    public void testArretLongitudeAndLatitude() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilôt maitre";

        // When
        Arret arret = new Arret(name, longitude, latitude);

        // Then
        assertEquals(longitude, arret.getLongitude());
        assertEquals(latitude, arret.getLatitude());
    }

    @Test
    public void testArretSetters() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilôt maitre";
        Arret arret = new Arret(name, longitude, latitude);

        // When
        Double newLongitude = 50.789;
        Double newLatitude = -40.321;

        arret.setLongitude(newLongitude);
        arret.setLatitude(newLatitude);

        // Then
        assertEquals(newLongitude, arret.getLongitude());
        assertEquals(newLatitude, arret.getLatitude());
    }

    @Test
    public void testArretConstructorOverloads() {
        // Given
        String id = UUID.randomUUID().toString();
        Double longitude = 12.345;
        Double latitude = 67.890;
        String name = "Ilôt maitre";

        // When
        Arret arret1 = new Arret(UUID.fromString(id), name, longitude, latitude);
        Arret arret2 = new Arret(id, name, longitude, latitude);
        Arret arret3 = new Arret(name, longitude, latitude);

        // Then
        assertNotNull(arret1.getId());
        assertNotNull(arret2.getId());
        assertNotNull(arret3.getId());
        // Verify that arret1, arret2, and arret3 have the same attribute values.
        assertEquals(arret1.getLongitude(), longitude);
        assertEquals(arret1.getLongitude(), arret2.getLongitude());
        assertEquals(arret1.getLongitude(), arret3.getLongitude());

        assertEquals(arret1.getLatitude(), latitude);
        assertEquals(arret1.getLatitude(), arret2.getLatitude());
        assertEquals(arret1.getLatitude(), arret3.getLatitude());
        // You can continue to verify other attributes as needed.
    }
}
