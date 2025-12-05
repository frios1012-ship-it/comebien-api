// ListaServiceImpl.java - CORREGIDO
package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.*;
import pe.edu.upc.comebienapi.entities.*;
import pe.edu.upc.comebienapi.repositories.*;
import pe.edu.upc.comebienapi.services.ListaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaServiceImpl implements ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // <CHANGE> Agregar repositorios para asignar relaciones
    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Override
    @Transactional
    public DTOLista add(DTOLista dto) {
        Lista lista = new Lista();
        lista.setNombre(dto.getNombre());
        lista.setFechaCreacion(dto.getFechaCreacion() != null ? dto.getFechaCreacion() : LocalDate.now());

        // Asignar cliente
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
            lista.setCliente(cliente);
        }

        // <CHANGE> Asignar receta si viene en el DTO
        if (dto.getReceta() != null && dto.getReceta().getId() != null) {
            Receta receta = recetaRepository.findById(dto.getReceta().getId()).orElse(null);
            lista.setReceta(receta);
        }

        // <CHANGE> Asignar rutina si viene en el DTO
        if (dto.getRutina() != null && dto.getRutina().getId() != null) {
            Rutina rutina = rutinaRepository.findById(dto.getRutina().getId()).orElse(null);
            lista.setRutina(rutina);
        }

        lista = listaRepository.save(lista);
        return mapToDTO(lista);
    }

    @Override
    @Transactional
    public DTOLista update(Long id, DTOLista dto) {
        Lista lista = listaRepository.findById(id).orElse(null);
        if (lista == null) return null;

        lista.setNombre(dto.getNombre());

        // Actualizar cliente
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
            lista.setCliente(cliente);
        }

        // <CHANGE> Actualizar receta
        if (dto.getReceta() != null && dto.getReceta().getId() != null) {
            Receta receta = recetaRepository.findById(dto.getReceta().getId()).orElse(null);
            lista.setReceta(receta);
        } else {
            lista.setReceta(null);
        }

        // <CHANGE> Actualizar rutina
        if (dto.getRutina() != null && dto.getRutina().getId() != null) {
            Rutina rutina = rutinaRepository.findById(dto.getRutina().getId()).orElse(null);
            lista.setRutina(rutina);
        } else {
            lista.setRutina(null);
        }

        listaRepository.save(lista);
        return mapToDTO(lista);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        listaRepository.deleteById(id);
    }

    @Override
    public List<DTOLista> listAll() {
        return listaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DTOLista mapToDTO(Lista lista) {
        DTOLista dto = new DTOLista();
        dto.setId(lista.getId());
        dto.setNombre(lista.getNombre());
        dto.setFechaCreacion(lista.getFechaCreacion());
        dto.setClienteId(lista.getCliente() != null ? lista.getCliente().getId() : null);

        // <CHANGE> Mapear receta completa o null
        if (lista.getReceta() != null) {
            DTOReceta dtoReceta = new DTOReceta();
            dtoReceta.setId(lista.getReceta().getId());
            dtoReceta.setNombre(lista.getReceta().getNombre());
            dto.setReceta(dtoReceta);
        }

        // <CHANGE> Mapear rutina completa o null
        if (lista.getRutina() != null) {
            DTORutina dtoRutina = new DTORutina();
            dtoRutina.setId(lista.getRutina().getId());
            dtoRutina.setNombre(lista.getRutina().getNombre());
            dto.setRutina(dtoRutina);
        }

        return dto;
    }
}