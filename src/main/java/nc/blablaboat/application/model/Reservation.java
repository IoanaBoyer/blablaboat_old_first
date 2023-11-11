package nc.blablaboat.application.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Stop depart;
    private Stop arrivee;
    private Date date_heure_depart;
    private Date date_heure_arrivee;
    private Integer nb_passager; //TODO: deplacer en tant que méthode
    private Integer tarif_unitaire;
    private String specifications;
    private ArrayList<User> listePassagers;
    private User conducteur;

    public Reservation(UUID id, Stop depart, Stop arrivee, Date date_heure_depart, Date date_heure_arrivee,
                       Integer nb_passager, Integer tarif_unitaire, String specifications, ArrayList<User> listePassagers,
                       User conducteur) {
        setId(id);
        this.depart = depart;
        this.arrivee = arrivee;
        this.date_heure_depart = date_heure_depart;
        this.date_heure_arrivee = date_heure_arrivee;
        this.nb_passager = nb_passager;
        this.tarif_unitaire = tarif_unitaire;
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

    public Stop getDeparture() {
        return depart;
    }

    public void setDeparture(Stop depart) {
        this.depart = depart;
    }

    public Stop getArrival() {
        return arrivee;
    }

    public void setArrival(Stop arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDateHeureDepart() {
        return date_heure_depart;
    }

    public void setDateHeureDepart(Date date_heure_depart) {
        this.date_heure_depart = date_heure_depart;
    }

    public Date getDateHeureArrivee() {
        return date_heure_arrivee;
    }

    public void setDateHeureArrivee(Date date_heure_arrivee) {
        this.date_heure_arrivee = date_heure_arrivee;
    }

    public Integer getNbPassager() {
        return nb_passager;
    }

    public void setNbPassager(Integer nb_passager) {
        this.nb_passager = nb_passager;
    }

    public Integer getTarifUnitaire() {
        return tarif_unitaire;
    }

    public void setTarifUnitaire(Integer tarif_unitaire) {
        this.tarif_unitaire = tarif_unitaire;
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