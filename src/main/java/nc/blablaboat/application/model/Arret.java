package nc.blablaboat.application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Arret {
    private UUID id ;
    private Double longitude;
    private Double latitude;

    public Arret(UUID id, Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id.toString();
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
