import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MysqlConnectionParameters {

    private static final String PROPERTIES_FILE_NAME = "/biblioteca.properties";
    private Properties properties;
    private String dbHost;
    private String dbPort;
    private String dbUsername;
    private String dbPassword;
    private String dbName;

    public MysqlConnectionParameters() throws IOException {
        loadConfigurationFrom(PROPERTIES_FILE_NAME); // załaduj dane do połączenia z bazą danych z pliku .properties...

        // ... do pól klasy przypisz wartości z pliku .properties
        this.dbHost = loadParameter("jdbc.database.host");
        this.dbPort = loadParameter("jdbc.database.port");
        this.dbUsername = loadParameter("jdbc.username");
        this.dbPassword = loadParameter("jdbc.password");
        this.dbName = loadParameter("jdbc.database.name");

    }

    private String loadParameter(String propertyName) {
        return properties.getProperty(propertyName);
        //wyekstraktowano tę metodę aby nie "brudzić" konstuktora
    }

    public void loadConfigurationFrom(String resourceName) throws IOException {
        properties = new Properties(); //wywołując tę metodę w konstruktorze jednocześnie przypisujemy instancji tej klasy wartośc pola properties

        InputStream propertiesStream = this.getClass().getResourceAsStream(resourceName); // odczytaj plik .properties i zwróc go jako stream danych

        // powyższa metoda, a raczej łanuch metod, może zwrócić null - warto to obsłużyć
        if (propertiesStream == null) {
            System.err.println("Resource file with parameters to access the database can't be loaded.");
            throw new FileNotFoundException("File not found.");
        }

        properties.load(propertiesStream); // załaduj dane do pola properties

    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbName() {
        return dbName;
    }
}
