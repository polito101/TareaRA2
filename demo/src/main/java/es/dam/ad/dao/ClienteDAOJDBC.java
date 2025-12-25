package es.dam.ad.dao;

import es.dam.ad.bd.ConexionBD;
import es.dam.ad.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJDBC implements ClienteDAO {

    @Override
    public List<Cliente> findAll() throws Exception {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nombre, email, saldo FROM clientes";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getDouble("saldo")
                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            // TODO: tratar excepción como se indica en la teoría UD03 (log, mensaje, relanzar, etc.)
            throw e;
        }

        return clientes;
    }

    @Override
    public Cliente findById(int id) throws Exception {
        // TODO: implementar SELECT ... WHERE id = ?
        return null;
    }

    @Override
    public void insert(Cliente cliente) throws Exception {
        // TODO: implementar INSERT con PreparedStatement
        // IMPORTANTE: revisar en la teoría el uso de parámetros (?) y evitar SQL Injection
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        // TODO: implementar UPDATE por id
    }

    @Override
    public void delete(int id) throws Exception {
        // TODO: implementar DELETE por id
    }
}