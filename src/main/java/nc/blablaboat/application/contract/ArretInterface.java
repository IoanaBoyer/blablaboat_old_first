package nc.blablaboat.application.contract;

import nc.blablaboat.application.model.Arret;

import java.util.ArrayList;

public interface ArretInterface {

    void insert(Arret arret);

    void update(Arret arret);

    void delete(String id);

    ArrayList<Arret> getById(String id);

    // Méthode pour récupérer tous les arrêts
    ArrayList<Arret> getAll();
}