package nc.blablaboat.application;

import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.UserService;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    // Créer un utilisateur client
    // Créer un utilisateur conducteur

    // utilisateur client - demander trajet
    // utilisateur conducteur - proposer trajet
    // ajouter utilisateur trajet



    // 1) ---- Cas utilisateur/conducteur
    // 1.  Utilisateur arrive sur la page de connexion
    // 2b. Créer login (opt)
    // 2a. Se connecter

    // 2a) ---- Cas utilisateur - créer
    // Affichage page de ses reservations
    // 3.  Récupération de ses réservations
    // 4a. utilisateur --- page acceuil -- réservations proposées.
    // 4b. utilisateur -- recherche -- réservation
    // 5.  utilisteur -- rejoindre réservation

    // 2a) ---- Cas utilisateur - supprimer
    // 1. utilisateur -- supprime -- reservation

    // 2b) ---- Cas conducteur - créer
    // Affichage page de ses reservations
    // 3.  Récupération de ses réservations
    // 5.  conducteur -- rejoindre réservation

    // 2b) ---- Cas conducteur - supprimer
    // 1. utilisateur -- supprime -- reservation


    // 1. User Service Functional Test:
    // Create a new user and insert it into the database.
    // Retrieve the newly created user by their ID.
    // Verify that the retrieved user's attributes match the expected values.
    // Update the user's age and driver status.
    // Retrieve the user again and ensure that the updated values are reflected.
    // Delete the user from the database.
    // Attempt to retrieve the user by ID and verify that it returns null, indicating the user is deleted.
    // Search for users with a specific keyword and ensure the results are as expected.

    // 2.Stop Service Functional Test:
    // Create a new Stop (bus stop) object.
    // Verify that the Stop object has a non-null ID after creation.
    // Update the longitude and latitude of the Stop.
    // Verify that the changes are correctly reflected in the Stop object.
    // Ensure that you can retrieve the Stop by its ID.
    // Verify that the retrieved Stop's attributes match the expected values.
    // Create another Stop without specifying an ID.
    // Ensure that the Stop is created successfully and has a non-null ID.
    // Create multiple Stop objects.
    // Ensure that the Stop objects have unique IDs.
    // Verify that you can retrieve all Arrets and that the list contains all created Stop objects.

    // 3.Reservation Service Functional Test:
    // Create a new Reservation and insert it into the database.
    // Retrieve the newly created Reservation by its ID.
    // Verify that the retrieved Reservation's attributes match the expected values.
    // Update the number of passengers and the tariff for the Reservation.
    // Retrieve the Reservation again and ensure that the updated values are reflected.
    // Delete the Reservation from the database.
    // Attempt to retrieve the Reservation by ID and verify that it returns null, indicating the Reservation is deleted.
    // Implement logic to update similar requests and ensure it works as expected.
    // Create multiple Reservations and verify that they are correctly stored and can be retrieved.

    // 4. Vaadin View Functional Test:
    // Test the EmptyView to ensure that it displays the "Let's do it" header and the BlaBlaBoat logo correctly.
    // Verify that the view is centered on the page.
    // Check if the "Yoyaa !!" paragraph is displayed as expected.
}
