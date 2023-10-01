package nc.blablaboat.application.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {
    private Connection connection;

    public DatabaseInitializer() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initializeDatabase() {
        initializePeopleTable();
        // ajouter tous les initialiseurs ici
    }

    private void initializePeopleTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS people ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "age INTEGER"
                + ")";
            PreparedStatement statement = connection.prepareStatement(createTableSQL);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajoutez d'autres méthodes pour initialiser d'autres tables ici si nécessaire
}