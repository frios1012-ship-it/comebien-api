package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.comebienapi.dtos.DTOCliente;
import pe.edu.upc.comebienapi.entities.Cliente;
import pe.edu.upc.comebienapi.entities.User;
import pe.edu.upc.comebienapi.repositories.ClienteRepository;
import pe.edu.upc.comebienapi.repositories.UserRepository;
import pe.edu.upc.comebienapi.services.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DTOCliente add(DTOCliente dto) {
        User user = userRepository.findById(dto.getUserId()).orElse(null);

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setPesoObjetivo(dto.getPesoObjetivo());
        cliente.setTalla(dto.getTalla());
        cliente.setUser(user);

        cliente = clienteRepository.save(cliente);

        dto.setId(cliente.getId());
        return dto;
    }

    @Override
    public DTOCliente update(Long id, DTOCliente dto) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) return null;

        cliente.setNombre(dto.getNombre());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setPesoObjetivo(dto.getPesoObjetivo());
        cliente.setTalla(dto.getTalla());

        clienteRepository.save(cliente);

        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<DTOCliente> listAll() {
        return clienteRepository.findAll().stream().map(c ->
                new DTOCliente(
                        c.getId(),
                        c.getNombre(),
                        c.getFechaNacimiento(),
                        c.getPesoObjetivo(),
                        c.getTalla(),
                        c.getUser() != null ? c.getUser().getId() : null
                )
        ).collect(Collectors.toList());
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
