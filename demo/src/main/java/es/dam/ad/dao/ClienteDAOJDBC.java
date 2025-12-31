package es.dam.ad.dao;

import es.dam.ad.bd.ConexionBD;
import es.dam.ad.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class ClienteDAOJDBC implements ClienteDAO {

    private static final Logger logger = Logger.getLogger(ClienteDAO.class.getName());

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
            //GESTION DE ERRORES
            System.err.println("Error con la consulta");
            logger.severe("Error listando clientes "+ e.getMessage()); 
            throw new Exception("No se ha podido realizar la consulta", e);
        }

        return clientes;
    }

    @Override
    public Cliente findById(int buscaId) throws Exception {
        // BUSCAR CLIENTE POR ID
        String sql = "SELECT id, nombre, email, saldo FROM clientes WHERE id = ? ";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);)
             {ps.setInt(1, buscaId); 
             
             try (ResultSet rs = ps.executeQuery()) {
                              
                if (rs.next()){
                    Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getDouble("saldo"));
                    return c;
                }
                return null;
            } catch (SQLException e) {
            System.err.println("Error al conectar o realizar la consulta");
            logger.severe("Error buscando al cliente con ID " + buscaId + ": " + e.getMessage()); 
            throw new Exception("No se ha encontrado el cliente", e);    
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar o realizar la consulta");
            logger.severe("Error buscando al cliente con ID " + buscaId + ": " + e.getMessage()); 
            throw new Exception("No se ha encontrado el cliente", e);
        }                
    }


    @Override
    public void insert(Cliente cliente) throws Exception {
        // IMPLEMENTACIÓN DE INSERT CON preparedStatement
        String cnombre=cliente.getNombre();
        String cemail=cliente.getEmail();
        Double csaldo=cliente.getSaldo();
        
        String sql = "INSERT INTO CLIENTES (nombre, email, saldo) VALUES (?,?,?) ";
                
        try 
            (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql) ) 
            {
            ps.setString(1,cnombre);
            ps.setString(2,cemail);
            ps.setDouble(3,csaldo);
            ps.executeUpdate();
           
            
        } catch (SQLException e) {
            System.err.println("Error, no se ha podido insertar cliente");
            logger.severe("Error, no se pudo insertar cliente" + e.getMessage()); 
            throw new Exception("No ha podido insertar el cliente", e);
            
        }
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        int cid=cliente.getId();
        String cnombre=cliente.getNombre();
        String cemail=cliente.getEmail();
        Double csaldo=cliente.getSaldo();
        String sql="UPDATE CLIENTES SET NOMBRE = ?, EMAIL = ?, SALDO = ? WHERE ID = ?";

        try (
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps=con.prepareStatement(sql))
            {
            ps.setString(1,cnombre);
            ps.setString(2,cemail);
            ps.setDouble(3,csaldo);
            ps.setInt(4, cid);
            ps.executeUpdate();
           
        }catch (SQLException e){
            System.err.println("No se ha podido actualizar cliente");
             logger.severe("Error, no se ha podido actualizar cliente con ID " + cid + ": " + e.getMessage()); 
            throw new Exception("No ha sido posible actualizar la bd", e);
            
        }
    
    }
    
    @Override
    public void delete(int cid) throws Exception {
        // IMPLEMENTACIÓN DE DELETE CON preparedStatement
        String sql="DELETE FROM CLIENTES WHERE ID = ?";

        try (
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps=con.prepareStatement(sql))
            {
            ps.setInt(1, cid);
            ps.executeUpdate();

        }catch (SQLException e){
            System.err.println("No se ha podido eliminar cliente");
             logger.severe("Error, no se ha podido eliminar cliente con ID " + cid + ": " + e.getMessage()); 
            throw new Exception("No se ha podido eliminar cliente", e);
        }

    }
}