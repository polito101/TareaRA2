package es.dam.ad.dao;
import es.dam.ad.modelo.Cliente;
import java.util.List;

public interface ClienteDAO {

    List<Cliente> findAll() throws Exception;
    Cliente findById(int id) throws Exception;
    void insert(Cliente cliente) throws Exception;
    void update(Cliente cliente) throws Exception;
    void delete(int id) throws Exception;
}


