package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOCliente;
import pe.edu.upc.comebienapi.entities.Cliente;

import java.util.List;

public interface ClienteService {

    public DTOCliente add(DTOCliente dtoCliente);
    public DTOCliente update(Long id, DTOCliente dtoCliente);
    public void delete(Long id);
    public List<DTOCliente> listAll();
    public Cliente findById(Long id);
}
