package nc.blablaboat.application.service;

import java.sql.Date;
import java.util.ArrayList;

import nc.blablaboat.application.contract.ReservationInterface;
import nc.blablaboat.application.dao.ArretDAO;
import nc.blablaboat.application.dao.ReservationDAO;
import nc.blablaboat.application.model.Reservation;
import nc.blablaboat.application.model.User;

public class ReservationService implements ReservationInterface {

    private final ReservationDAO RESERVATIONDAO = new ReservationDAO();

    public void majDemandesSimilaires() {
        // Implémentez la logique pour mettre à jour les demandes similaires ici
    }

    @Override
    public void insert(Reservation reservation) {
        RESERVATIONDAO.insert(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        RESERVATIONDAO.update(reservation);
    }

    @Override
    public void delete(String id) {
        RESERVATIONDAO.delete(id);
    }

    @Override
    public Reservation getById(String id) {
        return RESERVATIONDAO.getById(id);
    }

    @Override
    public ArrayList<Reservation> getAll() {
        return RESERVATIONDAO.getAll();
    }

    @Override
    public ArrayList<Reservation> getBySearchTerm(String searchTerm) {
        return RESERVATIONDAO.getBySearchTerm(searchTerm);
    }
}