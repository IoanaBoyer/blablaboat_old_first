package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Stop;

import java.util.ArrayList;

public interface ArretInterface {

    void insert(Stop stop);

    void update(Stop stop);

    void delete(String id);

    ArrayList<Stop> getById(String id);

    // Méthode pour récupérer tous les arrêts
    ArrayList<Stop> getAll();
}