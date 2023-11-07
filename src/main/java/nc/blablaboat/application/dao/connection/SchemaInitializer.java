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
            createTablePassager();

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
                    + "id VARCHAR(36) PRIMARY KEY,"
                    + "nickname TEXT NOT NULL,"
                    + "lastname TEXT NOT NULL,"
                    + "firstname TEXT NOT NULL,"
                    + "age INTEGER NOT NULL,"
                    + "password TEXT NOT NULL,"
                    + "isdriver BOOLEAN NOT NULL"
                    + ")");
        }
    }

    private void createTableReservation() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS reservation ("
                    + "id VARCHAR(36) PRIMARY KEY,"
                    + "depart_id VARCHAR(36),"
                    + "arrivee_id VARCHAR(36),"
                    + "dateHeureDepart DATETIME,"
                    + "dateHeureArrivee DATETIME,"
                    + "nbPassager INTEGER,"
                    + "tarifUnitaire INTEGER,"
                    + "specifications TEXT,"
                    + "passagers_id VARCHAR(36),"
                    + "conducteur_id VARCHAR(36),"
                    + "FOREIGN KEY (depart_id) REFERENCES stop (id),"
                    + "FOREIGN KEY (arrivee_id) REFERENCES stop (id),"
                    + "FOREIGN KEY (passagers_id) REFERENCES passager (reservation_id),"
                    + "FOREIGN KEY (conducteur_id) REFERENCES user (id)"
                    + ")");
        }
    }

    private void createTableStop() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS stop ("
                    + "id VARCHAR(36) PRIMARY KEY,"
                    + "name TEXT,"
                    + "longitude DOUBLE PRECISION NOT NULL,"
                    + "latitude DOUBLE PRECISION NOT NULL"
                    + ")");
        }
    }

    private void createTablePassager() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS passager ("
                    + "reservation_id VARCHAR(36) NOT NULL,"
                    + "user_id VARCHAR(36) NOT NULL,"
                    + "PRIMARY KEY (reservation_id, user_id),"
                    + "FOREIGN KEY (reservation_id) REFERENCES reservation (id),"
                    + "FOREIGN KEY (user_id) REFERENCES user (id)"
                    + ")");
        }
    }

}
