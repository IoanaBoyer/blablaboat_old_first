package nc.blablaboat.application.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.service.ReservationService;

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
        for (Reservation reservation : reservations) {
            Div reservationDiv = new Div();
            reservationDiv.add(new H3("Reservation ID: " + reservation.getId()));
            reservationDiv.add(new Paragraph("Departure: " + reservation.getDepart().getName()));
            reservationDiv.add(new Paragraph("Arrival: " + reservation.getArrivee().getName()));
            reservationDiv.add(new Paragraph("Departure Date: " + reservation.getDateHeureDepart()));
            reservationDiv.add(new Paragraph("Arrival Date: " + reservation.getDateHeureArrivee()));
            reservationDiv.add(new Paragraph("Number of Passengers: " + reservation.getNbPassager()));
            reservationDiv.add(new Paragraph("Unit Price: " + reservation.getTarifUnitaire()));
            reservationDiv.add(new Paragraph("Specifications: " + reservation.getSpecifications()));

            reservationLayout.add(reservationDiv);
        }
    }
}