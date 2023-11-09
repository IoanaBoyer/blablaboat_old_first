package nc.blablaboat.application.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Définition de la classe réservation
 */
public class Reservation {
    /**
     * L'identifiant de la réservation
     */
    private UUID id;

    /**
     * Le point de départ de la réservation
     */
    private Stop departure;

    /**
     * Le point d'arrivée de la réservation
     */
    private Stop arrival;

    /**
     * La date et l'heure de départ de la réservation
     */
    private Date departureDateTime;

    /**
     * La date et l'heure d'arrivée de la réservation
     */
    private Date arrivalDateTime;

    /**
     * Le nombre de passagers de la réservation
     */
    private Integer numberOfPassengers; //TODO: deplacer en tant que méthode

    /**
     * Le tarif unitaire par passager
     */
    private Integer unitFare;

    /**
     * Les spécifications additionnelles pour la réservation
     */
    private String specifications;

    /**
     * La liste des passagers de la réservation
     */
    private ArrayList<User> passengerList;

    /**
     * Indicateur si l'utilisateur est conducteur pour cette réservation
     */
    private User isDriver;

    /**
     * Constructeur avec initialisation des attributs
     * @param id l'identifiant de la réservation de type UUID
     * @param departure le point de départ de la réservation
     * @param arrival le point d'arrivée de la réservation
     * @param departureDateTime la date et l'heure de départ
     * @param arrivalDateTime la date et l'heure d'arrivée
     * @param numberOfPassengers le nombre de passagers
     * @param unitFare le tarif unitaire par passager
     * @param specifications les spécifications additionnelles pour la réservation
     * @param passengerList la liste des passagers de la réservation
     * @param isDriver indique si l'utilisateur est conducteur pour cette réservation
     */
    public Reservation(UUID id, Stop departure, Stop arrival, Date departureDateTime, Date arrivalDateTime,
                       Integer numberOfPassengers, Integer unitFare, String specifications, ArrayList<User>
                               passengerList, User isDriver) {
        setId(id);
        this.departure = departure;
        this.arrival = arrival;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.numberOfPassengers = numberOfPassengers;
        this.unitFare = unitFare;
        this.specifications = specifications;
        this.passengerList = passengerList;
        this.isDriver = isDriver;
    }
    /**
     * Récupère l'identifiant de la réservation
     * @return id sous forme de chaîne de caractères
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Modifie l'identifiant de la réservation
     * @param id le nouvel identifiant de la réservation
     */
    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    /**
     * Récupère le point de départ de la réservation
     * @return departure le point de départ
     */
    public Stop getDeparture() {
        return departure;
    }

    /**
     * Modifie le point de départ de la réservation
     * @param departure le nouveau point de départ
     */
    public void setDeparture(Stop departure) {
        this.departure = departure;
    }

    /**
     * Récupère le point d'arrivée de la réservation
     * @return arrival le point d'arrivée
     */
    public Stop getArrival() {
        return arrival;
    }

    /**
     * Modifie le point d'arrivée de la réservation
     * @param arrival le nouveau point d'arrivée
     */
    public void setArrival(Stop arrival) {
        this.arrival = arrival;
    }

    /**
     * Récupère la date et l'heure de départ de la réservation
     * @return departureDateTime la date et l'heure de départ
     */
    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    /**
     * Modifie la date et l'heure de départ de la réservation
     * @param departureDateTime la nouvelle date et l'heure de départ
     */
    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    /**
     * Récupère la date et l'heure d'arrivée de la réservation
     * @return arrivalDateTime la date et l'heure d'arrivée
     */
    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    /**
     * Modifie la date et l'heure d'arrivée de la réservation
     * @param arrivalDateTime la nouvelle date et l'heure d'arrivée
     */
    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    /**
     * Récupère le nombre de passagers de la réservation
     * @return numberOfPassengers le nombre de passagers
     */
    public Integer getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * Modifie le nombre de passagers de la réservation
     * @param numberOfPassengers le nouveau nombre de passagers
     */
    public void setNumberOfPassengers(Integer numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    /**
     * Récupère le tarif unitaire par passager de la réservation
     * @return unitFare le tarif unitaire
     */
    public Integer getUnitFare() {
        return unitFare;
    }

    /**
     * Modifie le tarif unitaire par passager de la réservation
     * @param unitFare le nouveau tarif unitaire
     */
    public void setUnitFare(Integer unitFare) {
        this.unitFare = unitFare;
    }

    /**
     * Récupère les spécifications additionnelles de la réservation
     * @return specifications les spécifications
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * Modifie les spécifications additionnelles de la réservation
     * @param specifications les nouvelles spécifications
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * Récupère la liste des passagers de la réservation
     * @return passengerList la liste des passagers
     */
    public ArrayList<User> getPassengerList() {
        return passengerList;
    }

    /**
     * Modifie la liste des passagers de la réservation
     * @param passengerList la nouvelle liste des passagers
     */
    public void setPassengerList(ArrayList<User> passengerList) {
        this.passengerList = passengerList;
    }

    /**
     * Vérifie si l'utilisateur est conducteur pour cette réservation
     * @return isDriver si l'utilisateur est conducteur
     */
    public User getIsDriver() {
        return isDriver;
    }

    /**
     * Modifie l'indication si l'utilisateur est conducteur pour cette réservation
     * @param isDriver le nouveau statut de conducteur
     */
    public void setIsDriver(User isDriver) {
        this.isDriver = isDriver;
    }
}