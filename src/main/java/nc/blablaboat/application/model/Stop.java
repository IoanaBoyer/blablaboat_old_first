package nc.blablaboat.application.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Définition de la classe arrêt
 */
public class Stop {
    /**
     * L'identifiant de l'arrêt
     */
    private UUID id ;

    /**
     * Le nom de l'arrêt
     */
    private String name;

    /**
     * La longitude de l'arrêt
     */
    private Double longitude;

    /**
     * La latitude de l'arrêt
     */
    private Double latitude;

    /**
     * Constructeur vide
     */
    public Stop(){
    }

    /**
     * Constructeur avec initialisation des attributs
     * @param id l'identifiant de l'arrêt de type UUID
     * @param name le nom de l'arrêt
     * @param longitude la longitude de l'arrêt
     * @param latitude la latitude de l'arrêt
     */
    public Stop(UUID id, String name, Double longitude, Double latitude) {
        setId(id);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Constructeur avec initialisation des attributs
     * @param id l'identifiant de l'arrêt de type String
     * @param name le nom de l'arrêt
     * @param longitude la longitude de l'arrêt
     * @param latitude la latitude de l'arrêt
     */
    public Stop(String id, String name, Double longitude, Double latitude) {
        this(UUID.fromString(id), name, longitude, latitude);
    }

    /**
     * Constructeur avec initialisation des attributs sans fournir l'id
     * @param name le nom de l'arrêt
     * @param longitude la longitude de l'arrêt
     * @param latitude la latitude de l'arrêt
     */
    public Stop(String name, Double longitude, Double latitude) {
        this(UUID.randomUUID(), name, longitude, latitude);
    }

    /**
     * Recupère l'identifiant de l'arrêt
     * @return id
     */
    public String getId() {
        return id.toString();
    }

    /**
     * Modifie l'identifiant de l'arrêt
     * @param id le nouvel identifiant de l'arrêt
     */
    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    /**
     * Recupère le nom de l'arrêt
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de l'arrêt
     * @param name le nouveau nom de l'arrêt
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Recupère la longitude de l'arrêt
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Modifie la longitude de l'arrêt
     * @param longitude la nouvelle longitude de l'arrêt
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Recupère la latitude de l'arrêt
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Modifier la latitude de l'arrêt
     * @param latitude la nouvelle latitude de l'arrêt
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
