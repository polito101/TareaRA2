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
        try (InputStream input = ConexionBD.class
             .getClassLoader() 
             .getResourceAsStream("db.properties")) {
                 if (input == null) { 
                    throw new RuntimeException("No se encontró db.properties en resources");
                 } 
                 // Cargar propiedades 
                 properties.load(input); 
                 url=properties.getProperty("db.url");
                 user=properties.getProperty("db.user");
                 password=properties.getProperty("db.password");

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
        
    public static Connection getConnection() throws SQLException { 
        return DriverManager.getConnection( url, user, password ); }
}

