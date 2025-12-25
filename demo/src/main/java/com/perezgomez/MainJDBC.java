package com.perezgomez;

import es.dam.ad.dao.ClienteDAO;
import es.dam.ad.dao.ClienteDAOJDBC;
import es.dam.ad.modelo.Cliente;

import java.util.List;
import java.util.Scanner;

public class MainJDBC {
public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAOJDBC();
        Scanner sc = new Scanner(System.in);
        int opcion;

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
                        // TODO: mostrar lista por consola
                    }
                    case 2 -> {
                        // TODO: pedir datos por teclado y llamar a dao.insert(...)
                    }
                    case 3 -> {
                        // TODO: pedir id, nuevos datos y llamar a dao.update(...)
                    }
                    case 4 -> {
                        // TODO: pedir id y llamar a dao.delete(...)
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                // TODO: mostrar mensaje de error al usuario según las buenas prácticas vistas en teoría
            }

        } while (opcion != 0);

        sc.close();
    }
}


