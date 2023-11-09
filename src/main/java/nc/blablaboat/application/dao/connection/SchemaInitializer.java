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
        connection.setAutoCommit(false);
        try {
            createTableUser();
            createTableReservation();
            createTableStop();
            createTablePassenger();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void createTableUser() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user ("
                    + "id TEXT PRIMARY KEY,"
                    + "nickname TEXT NOT NULL,"
                    + "lastname TEXT NOT NULL,"
                    + "firstname TEXT NOT NULL,"
                    + "age INTEGER NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "is_driver BOOLEAN NOT NULL"
                    + ")");
        }
    }

    private void createTableReservation() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS reservation ("
                    + "id TEXT PRIMARY KEY,"
                    + "departure_id TEXT,"
                    + "arrival_id TEXT,"
                    + "departure_date_time TEXT,"
                    + "arrival_date_time TEXT,"
                    + "number_of_passengers TEXT,"
                    + "unit_fare INTEGER,"
                    + "specifications TEXT,"
                    //TODO Enlevé + "passagers_id VARCHAR(36),"
                    + "conducteur_id TEXT,"
                    + "FOREIGN KEY (departure_id) REFERENCES stop (id),"
                    + "FOREIGN KEY (arrival_id) REFERENCES stop (id),"
                    //+ "FOREIGN KEY (passagers_id) REFERENCES passager (reservation_id),"
                    + "FOREIGN KEY (conducteur_id) REFERENCES user (id)"
                    + ")");
        }
    }

    private void createTableStop() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS stop ("
                    + "id TEXT PRIMARY KEY,"
                    + "name TEXT,"
                    + "longitude DOUBLE PRECISION NOT NULL,"
                    + "latitude DOUBLE PRECISION NOT NULL"
                    + ")");
        }
    }

    private void createTablePassenger() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS passenger ("
                    + "reservation_id TEXT NOT NULL,"
                    + "passenger_id TEXT NOT NULL,"
                    + "PRIMARY KEY (reservation_id, passenger_id),"
                    + "FOREIGN KEY (reservation_id) REFERENCES reservation (id),"
                    + "FOREIGN KEY (passenger_id) REFERENCES user (id)"
                    + ")");
        }
    }

}
