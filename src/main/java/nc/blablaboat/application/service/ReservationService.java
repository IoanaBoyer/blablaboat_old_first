package nc.blablaboat.application.service;

import java.util.ArrayList;

import nc.blablaboat.application.dao.ReservationDAO;
import nc.blablaboat.application.model.Reservation;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void majDemandesSimilaires() {
        // Implémentez la logique pour mettre à jour les demandes similaires ici
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