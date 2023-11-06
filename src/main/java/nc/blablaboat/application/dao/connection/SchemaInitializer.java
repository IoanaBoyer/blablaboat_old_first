package nc.blablaboat.application.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class SchemaInitializer {

    private final Connection connection;

    SchemaInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {
        createSchema();
    }

    private void createSchema() throws SQLException {
        try (var statement = connection.createStatement()) {
            // Création de la table user
            statement.execute("CREATE TABLE IF NOT EXISTS user (" +
                    "id UUID PRIMARY KEY, " +
                    "nickname VARCHAR(255) UNIQUE NOT NULL, " +
                    "lastname VARCHAR(255) NOT NULL, " +
                    "firstname VARCHAR(255) NOT NULL, " +
                    "age INT NOT NULL CHECK (age >= 0), " +
                    "password VARCHAR(255) NOT NULL, " +
                    "is_driver BOOLEAN NOT NULL" +
                    ");");

            // Création de la table arret
            statement.execute("CREATE TABLE IF NOT EXISTS stop (" +
                    "id UUID PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "longitude DOUBLE PRECISION NOT NULL, " +
                    "latitude DOUBLE PRECISION NOT NULL" +
                    ");");

            // TODO Création de la table reservation
//            create table main.reservations
//                    (
//                            id                 integer,
//                            depart_id          integer,
//                            arrivee_id         integer,
//                            date_heure_depart  integer,
//                            date_heure_arrivee integer,
//                            nb_passager        integer,
//                            tarif_unitaire     integer,
//                            specifications     integer,
//                            conducteur_id      integer
//                    );

            // TODO Création de la table passager
        }
    }
}
