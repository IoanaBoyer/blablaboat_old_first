package nc.blablaboat.application.model;

import java.sql.Date;
import java.util.ArrayList;

public class Demande {
    private Integer id;
    private Arret depart;
    private Arret arrivee;
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private Integer tarifUnitaire;
    private ArrayList<Reservation> listeReservationsSimilaires;

    public Demande(Integer id, Arret depart, Arret arrivee, Date dateHeureDepart, Date dateHeureArrivee, Integer tarifUnitaire, ArrayList<Reservation> listeReservationsSimilaires) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.tarifUnitaire = tarifUnitaire;
        this.listeReservationsSimilaires = listeReservationsSimilaires;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Arret getDepart() {
        return depart;
    }

    public void setDepart(Arret depart) {
        this.depart = depart;
    }

    public Arret getArrivee() {
        return arrivee;
    }

    public void setArrivee(Arret arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(Date dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public Date getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public void setDateHeureArrivee(Date dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
    }

    public Integer getTarifUnitaire() {
        return tarifUnitaire;
    }

    public void setTarifUnitaire(Integer tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    public ArrayList<Reservation> getListeReservationsSimilaires() {
        return listeReservationsSimilaires;
    }

    public void setListeReservationsSimilaires(ArrayList<Reservation> listeReservationsSimilaires) {
        this.listeReservationsSimilaires = listeReservationsSimilaires;
    }
}