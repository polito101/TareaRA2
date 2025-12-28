package es.dam.ad.app;

import es.dam.ad.dao.ClienteDAO;
import es.dam.ad.dao.ClienteDAOJDBC;
import es.dam.ad.modelo.Cliente;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainJDBC {
    private static final Logger logger = Logger.getLogger(MainJDBC.class.getName());
   
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAOJDBC();
        Scanner sc = new Scanner(System.in);
        int opcion;
        int id;
        String nombre;
        String email;
        Double saldo;
        
        do {
            System.out.println("=== Gestión de clientes (JDBC) ===");
            System.out.println("1. Listar clientes");
            System.out.println("2. Insertar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());
            

            try {
                switch (opcion) {
                    case 1 -> {
                        List<Cliente> lista = dao.findAll();
                        for(Cliente cli: lista) {
                            System.out.println(cli.toString());
                        }
                        // Hecho: mostrar lista por consola

                    }
                    case 2 -> {
                        sc = new Scanner(System.in); 
                        System.out.print("Introduce el nombre: "); 
                        nombre = sc.nextLine(); 
                        System.out.print("Introduce el email: "); 
                        email = sc.nextLine(); 
                        System.out.print("Introduce el saldo: "); 
                        saldo = sc.nextDouble(); 
                        sc.nextLine();
                        Cliente nuevoCliente= new Cliente(nombre, email, saldo); 
                        // utilizamos el constructor sin id para insertar en la BD
                        dao.insert(nuevoCliente);
                        // Hecho: pedir datos por teclado y llamar a dao.insert(...)
                    }
                    case 3 -> {
                        sc = new Scanner(System.in); 
                        System.out.print("Introduce el ID: "); 
                        id = Integer.parseInt(sc.nextLine());
                        //sc.nextLine(); // limpiar salto de línea 
                        Cliente c=dao.findById(id);
                        if (c!=null){
                           System.out.println(c.toString());                     
                            System.out.print("Introduce nuevo nombre: "); 
                            nombre = sc.nextLine(); 
                            System.out.print("Introduce nuevo email: "); 
                            email = sc.nextLine(); 
                            System.out.print("Introduce nuevo saldo: "); 
                            saldo = sc.nextDouble(); 
                            sc.nextLine(); //para limpiar el buffer
                            c.setEmail(email);
                            c.setNombre(nombre);
                            c.setSaldo(saldo);
                            dao.update(c);
                           }
                        // Hecho: pedir id, mostramos los datos existentes,
                        // solicitamos nuevos datos y llamar a dao.update(...)
                    }
                    case 4 -> {
                        sc = new Scanner(System.in); 
                        System.out.print("Introduce el ID: "); 
                        id = sc.nextInt();
                        sc.nextLine(); 
                        Cliente c=dao.findById(id);
                        if (c!=null){
                            System.out.println(c.toString());
                            System.out.print("¿Eliminar cliente? (S/N) "); 
                            String sino = sc.nextLine();
                            if (sino.equalsIgnoreCase("S")) {
                               dao.delete(id); 
                            }
                                                               
                        }
                        // Hecho: pedir id y llamar a dao.delete(...)
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IOException e) {
                logger.severe("Error procesando la solicitud " + e.getMessage());
                    // Hecho: mostrar mensaje de error al usuario según las buenas prácticas vistas en teoría
            } catch(NumberFormatException e){
                logger.severe("Error en formato de número "+ e.getMessage());

            } catch(Exception e){
                logger.severe("Error "+e.getMessage());
            }
                   
            

        } while (opcion != 0);

        sc.close();
        
    }
}



