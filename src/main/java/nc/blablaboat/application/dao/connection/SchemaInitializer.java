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
            statement.execute("CREATE TABLE IF NOT EXISTS user ("
                + "id VARCHAR(36) PRIMARY KEY,"
                + "nickname TEXT NOT NULL,"
                + "lastname TEXT NOT NULL,"
                + "firstname TEXT NOT NULL,"
                + "age INTEGER NOT NULL,"
                + "password TEXT NOT NULL,"
                + "isdriver BOOLEAN NOT NULL"
                + ")");

            // Création de la table arret
            statement.execute("CREATE TABLE IF NOT EXISTS arret ("
                    + "id VARCHAR(36) PRIMARY KEY,"
                    + "name TEXT NOT NULL,"
                    + "longitude DOUBLE NOT NULL,"
                    + "latitude DOUBLE NOT NULL"
                    + ")");

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
