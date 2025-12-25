package es.dam.ad.bd;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
private static String url;
    private static String user;
    private static String password;
    private static Properties properties = new Properties(); 
    static { 
        try (InputStream input = ConnectionFactory.class
             .getClassLoader() 
             .getResourceAsStream("db.properties")) {
                 if (input == null) { 
                    throw new RuntimeException("No se encontró db.properties en resources");
                 } 
                 // Cargar propiedades 
                 properties.load(input); 
                 // Inicializar driver JDBC (UD03 lo indica) 
                 String driver = properties.getProperty("db.driver"); 
                 Class.forName(driver); 
                 // Si quisieras inicializar un pool de conexiones, aquí iría 
                 // // Ejemplo (HikariCP): // HikariConfig config = new HikariConfig(); 
                 // // config.setJdbcUrl(properties.getProperty("db.url")); 
                 // // config.setUsername(properties.getProperty("db.user")); 
                 // // config.setPassword(properties.getProperty("db.password")); 
                 // // dataSource = new HikariDataSource(config); 
                 } catch (Exception e) { 
                    throw new RuntimeException("Error al cargar configuración de BD", e);
                 }
    }
    public static String getUrl() { 
        return properties.getProperty("db.url"); } 
    public static String getUser() { 
        return properties.getProperty("db.user"); }
    public static String getPassword() { 
        return properties.getProperty("db.password"); }

    public static Connection getConnection() throws SQLException { 
        return DriverManager.getConnection( getUrl(), getUser(), getPassword() ); }
}

