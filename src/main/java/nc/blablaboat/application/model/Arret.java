package nc.blablaboat.application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Arret {
    private UUID id ;
    private String name;
    private Double longitude;
    private Double latitude;

    public Arret(UUID id, String name, Double longitude, Double latitude) {
        setId(id);
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Arret(String id, String name, Double longitude, Double latitude) {
        this(UUID.fromString(id), name, longitude, latitude);
    }

    public Arret(String name, Double longitude, Double latitude) {
        this(UUID.randomUUID(), name, longitude, latitude);
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
