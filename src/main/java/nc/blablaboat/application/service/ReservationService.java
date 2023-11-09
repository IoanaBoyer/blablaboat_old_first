package nc.blablaboat.application.service;

import java.util.ArrayList;

import nc.blablaboat.application.dao.ReservationDAO;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void majDemandesSimilaires() {
        // Implémentez la logique pour mettre à jour les demandes similaires ici
    }

    public void addPassenger(Reservation reservation, User passager) {
        // Vérifier si l'utilisateur n'est pas déjà un passager
        boolean isPassenger = reservation.getListePassagers().stream()
                .anyMatch(p -> p.getId().equals(passager.getId()));

        if (!isPassenger) {
            // Ajouter l'utilisateur
            reservation.getListePassagers().add(passager);
            reservationDAO.update(reservation);
        } else {
            // L'utilisateur est déjà un passager de cette réservation
            throw new RuntimeException("User is already a passenger in this reservation");
        }
    }

    public void insert(Reservation reservation){
        reservationDAO.insert(reservation);
    }

    public ArrayList<Reservation> getAll() {
        return reservationDAO.getAll();
    }

    public ArrayList<Reservation> getBySearchTerm(String searchTerm) {
        return reservationDAO.getBySearchTerm(searchTerm);
    }
}