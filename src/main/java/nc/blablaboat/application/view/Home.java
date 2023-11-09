package nc.blablaboat.application.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;
import nc.blablaboat.application.service.ReservationService;
import nc.blablaboat.application.service.UserService;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Home Blablaboat")
@Route("/")
public class Home extends BaseLayout {

    private final ReservationService reservationService = new ReservationService();
    private VerticalLayout reservationLayout = new VerticalLayout(); // Declare the reservationLayout here

    public Home() {
        showReservations();
    }

    private void showReservations() {
        TextField searchField = new TextField("Search Reservations");
        searchField.setPlaceholder("Enter a search term");
        searchField.addValueChangeListener(event -> filterReservations(event.getValue()));

        List<Reservation> allReservations = reservationService.getAll();
        showFilteredReservations(allReservations);

        setContent(new H2("Welcome to the Home Page"), searchField, reservationLayout);
    }

    private void filterReservations(String searchTerm) {
        List<Reservation> filteredReservations = reservationService.getBySearchTerm(searchTerm);
        showFilteredReservations(filteredReservations);
    }

    private void showFilteredReservations(List<Reservation> reservations) {
        reservationLayout.removeAll(); // Clear the previous content
        // TODO: enlever 3 lignes
        UserService userService = new UserService();
        String userId = "00000000-0000-0000-0000-000000000000";
        User user = userService.getById(userId);

        for (Reservation reservation : reservations) {
            Div reservationDiv = new Div();
            reservationDiv.getStyle().set("border", "1px solid #ddd");
            reservationDiv.getStyle().set("padding", "10px");
            reservationDiv.getStyle().set("margin", "10px");

            reservationDiv.add(new H3("Reservation ID: " + reservation.getId()));
            reservationDiv.add(new Paragraph("Departure: " + reservation.getDeparture().getName()));
            reservationDiv.add(new Paragraph("Arrival: " + reservation.getArrival().getName()));
            reservationDiv.add(new Paragraph("Departure Date: " + reservation.getDateHeureDepart()));
            reservationDiv.add(new Paragraph("Arrival Date: " + reservation.getDateHeureArrivee()));
            reservationDiv.add(new Paragraph("Number of Passengers: " + reservation.getNbPassager()));
            reservationDiv.add(new Paragraph("Unit Price: " + reservation.getTarifUnitaire()));
            reservationDiv.add(new Paragraph("Specifications: " + reservation.getSpecifications()));

            Button joinButton = new Button("Join");
            joinButton.addClickListener(event -> joinReservation(reservation, user)); // Define the joinReservation method
            reservationDiv.add(joinButton);

            reservationLayout.add(reservationDiv);
        }
    }


    private void joinReservation(Reservation reservation, User user) {
        // Implement logic to add the current user to the list of passengers for the selected reservation
        // You can use the UserService or ReservationService for this operation
        // For demonstration purposes, let's assume a UserService method addUserToReservation
        // userService.addUserToReservation(currentUser.getId(), reservation.getId());
        try {
            reservationService.addPassenger(reservation, user);
            Notification.show("You joined the reservation!");
        }
        catch (Exception e) {
            Notification.show("You have already joined this reservation!");
        }
    }
}