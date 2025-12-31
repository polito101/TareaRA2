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
                 // CARGA DE PROPIEDADES 
                 properties.load(input); 
                 url=properties.getProperty("db.url");
                 user=properties.getProperty("db.user");
                 password=properties.getProperty("db.password");

                 // Inicializar driver JDBC (INDICADO EN UD03) 
                 String driver = properties.getProperty("db.driver"); 
                 Class.forName(driver); 
                 // AQUÍ SE INCIALIZARIA POSIBLE POOL DE CONEXIONES
                 } catch (Exception e) { 
                    throw new RuntimeException("Error al cargar configuración de BD", e);
                 }
    }
        
    public static Connection getConnection() throws SQLException { 
        return DriverManager.getConnection( url, user, password ); }
}

