package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Reservation;

import java.util.ArrayList;

public interface ReservationInterface {

    void insert(Reservation reservation);

    void update(Reservation reservation);

    void delete(String id);

    Reservation getById(String id);

    // Méthode pour récupérer tous les arrêts
    ArrayList<Reservation> getAll();
}