package nc.blablaboat.application.service;

import nc.blablaboat.application.model.Stop;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StopServiceTest {
    /*
    @Test
    public void testArretIdGeneration() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilot signal";

        // When
        Stop stop = new Stop(name,longitude, latitude);

        // Then
        assertNotNull(stop.getId());
    }

    @Test
    public void testArretIdNotNull() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        UUID id = UUID.randomUUID();
        String name = "Ilôt maitre";

        // When
        Stop stop = new Stop(id, name, longitude, latitude);

        // Then
        assertEquals(id.toString(), stop.getId());
    }

    @Test
    public void testArretLongitudeAndLatitude() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilôt maitre";

        // When
        Stop stop = new Stop(name, longitude, latitude);

        // Then
        assertEquals(longitude, stop.getLongitude());
        assertEquals(latitude, stop.getLatitude());
    }

    @Test
    public void testArretSetters() {
        // Given
        Double longitude = 45.123;
        Double latitude = -30.456;
        String name = "Ilôt maitre";
        Stop stop = new Stop(name, longitude, latitude);

        // When
        Double newLongitude = 50.789;
        Double newLatitude = -40.321;

        stop.setLongitude(newLongitude);
        stop.setLatitude(newLatitude);

        // Then
        assertEquals(newLongitude, stop.getLongitude());
        assertEquals(newLatitude, stop.getLatitude());
    }

    @Test
    public void testArretConstructorOverloads() {
        // Given
        String id = UUID.randomUUID().toString();
        Double longitude = 12.345;
        Double latitude = 67.890;
        String name = "Ilôt maitre";

        // When
        Stop arret1 = new Stop(UUID.fromString(id), name, longitude, latitude);
        Stop arret2 = new Stop(id, name, longitude, latitude);
        Stop arret3 = new Stop(name, longitude, latitude);

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
    */
}
