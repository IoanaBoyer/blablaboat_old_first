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
            statement.execute("CREATE TABLE IF NOT EXISTS people ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "age INTEGER"
                + ")");
        }
    }

    private void insertTaux(String nom, double valeur) throws SQLException {
        try (var statement = connection.prepareStatement("INSERT INTO taux (ID, VALEUR) VALUES (?, ?)")) {
            statement.setString(1, nom);
            statement.setDouble(2, valeur);
            statement.execute();
        }
    }
}
