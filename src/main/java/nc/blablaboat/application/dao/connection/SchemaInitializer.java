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
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nickname TEXT NOT NULL,"
                + "lastname TEXT NOT NULL,"
                + "firstname TEXT NOT NULL,"
                + "age INTEGER NOT NULL,"
                + "password TEXT NOT NULL"
                + ")");

            // TODO Création de la table reservation
            /**
             * statement.execute("CREATE TABLE IF NOT EXISTS product ("
             *                     + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
             *                     + ...
             *                     + ")");
             */


            // TODO Création de la table demande
            /**
             * statement.execute("CREATE TABLE IF NOT EXISTS product ("
             *                     + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
             *                     + ...
             *                     + ")");
             */

        }
    }

    // TODO Ceci est un exemple on peut l'enlever ?
    private void insertTaux(String nom, double valeur) throws SQLException {
        try (var statement = connection.prepareStatement("INSERT INTO taux (ID, VALEUR) VALUES (?, ?)")) {
            statement.setString(1, nom);
            statement.setDouble(2, valeur);
            statement.execute();
        }
    }
}
