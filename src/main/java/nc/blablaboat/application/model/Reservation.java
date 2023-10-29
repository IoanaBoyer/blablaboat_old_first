package nc.blablaboat.application.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Reservation {
    private UUID id;
    private Arret depart;
    private Arret arrivee;
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private Integer nbPassager;
    private Integer tarifUnitaire;
    private String specifications;
    private ArrayList<User> listePassagers;
    private User conducteur;

    public Reservation(UUID id, Arret depart, Arret arrivee, Date dateHeureDepart, Date dateHeureArrivee,
                       Integer nbPassager, Integer tarifUnitaire, String specifications, ArrayList<User> listePassagers,
                       User conducteur) {
        setId(id);
        this.depart = depart;
        this.arrivee = arrivee;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.nbPassager = nbPassager;
        this.tarifUnitaire = tarifUnitaire;
        this.specifications = specifications;
        this.listePassagers = listePassagers;
        this.conducteur = conducteur;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
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

    public Integer getNbPassager() {
        return nbPassager;
    }

    public void setNbPassager(Integer nbPassager) {
        this.nbPassager = nbPassager;
    }

    public Integer getTarifUnitaire() {
        return tarifUnitaire;
    }

    public void setTarifUnitaire(Integer tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public ArrayList<User> getListePassagers() {
        return listePassagers;
    }

    public void setListePassagers(ArrayList<User> listePassagers) {
        this.listePassagers = listePassagers;
    }

    public User getConducteur() {
        return conducteur;
    }

    public void setConducteur(User conducteur) {
        this.conducteur = conducteur;
    }
}