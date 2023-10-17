package nc.blablaboat.application.model;

import java.util.Date;

public class Arret {
    private Arret depart;
    private Arret arrivee;
    private Date dateDepart;
    private Date dateArrivee;
    private ArrayList<Reservation> listeReservationsSimilaires;
}
