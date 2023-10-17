package nc.blablaboat.application.service;

import java.util.ArrayList;

public class Reservation {
    private Arret depart;
    private Arret arrivee;
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private Integer nbPassager;
    private Integer tarifUnitaire;
    private String specifications;
    private ArrayList<Passager> listePassagers;
    private Conducteur conducteur;

    public void majDemandesSimilaires() {
        // Implémentez la logique pour mettre à jour les demandes similaires ici
    }
}