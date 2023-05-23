import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {

    private MysqlDataSource dataSource;

    public MysqlConnection() throws IOException {
        initialize();
    }

    private void initialize() throws IOException {
        MysqlConnectionParameters parameters = new MysqlConnectionParameters(); //wczytuje parametry połączenia z bazą danych z pliku .properties.

        // dane z pliku .properties przypisywane są do obiektu dataSource, który odpowiada za połączenie z bazą danych
        dataSource = new MysqlDataSource();
        dataSource.setServerName(parameters.getDbHost());
        dataSource.setPort(Integer.parseInt(parameters.getDbPort()));
        dataSource.setUser(parameters.getDbUsername());
        dataSource.setPassword(parameters.getDbPassword());
        dataSource.setDatabaseName(parameters.getDbName());

        try {
            dataSource.setServerTimezone("Europe/Warsaw");
            dataSource.setUseSSL(false);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
