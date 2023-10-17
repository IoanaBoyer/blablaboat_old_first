package nc.blablaboat.application.service;

import nc.blablaboat.application.dao.arret.ArretDAO;
import nc.blablaboat.application.model.Arret;

import java.util.ArrayList;

public class ArretService {
    private ArretDAO arretDAO;

    public ArretService() {
        this.arretDAO = new ArretDAO();
    }

    public Arret getArretById(int id) {
        return arretDAO.getById(id);
    }

    public ArrayList<Arret> getAllArrets() {
        return arretDAO.getAll();
    }

    public void saveArret(Arret arret) {
        arretDAO.save(arret);
    }

    public void updateArret(Arret arret) {
        arretDAO.update(arret);
    }

    public void deleteArret(Arret arret) {
        arretDAO.delete(arret);
    }
}
