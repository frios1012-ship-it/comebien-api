package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.DTOCalificacion;
import pe.edu.upc.comebienapi.entities.Calificacion;
import pe.edu.upc.comebienapi.repositories.CalificacionRepository;
import pe.edu.upc.comebienapi.repositories.ClienteRepository;
import pe.edu.upc.comebienapi.repositories.RecetaRepository;
import pe.edu.upc.comebienapi.repositories.ListaRepository; // 🔹 Import nuevo
import pe.edu.upc.comebienapi.services.CalificacionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private ListaRepository listaRepository; // 🔹 Nuevo

    @Override
    @Transactional
    public DTOCalificacion add(DTOCalificacion dto) {
        Calificacion cal = new Calificacion();
        cal.setPuntaje(dto.getPuntaje());
        cal.setComentario(dto.getComentario());
        cal.setCliente(clienteRepository.findById(dto.getClienteId()).orElse(null));
        cal.setReceta(recetaRepository.findById(dto.getRecetaId()).orElse(null));
        if (dto.getListaId() != null) {
            cal.setLista(listaRepository.findById(dto.getListaId()).orElse(null)); // 🔹 Asignamos lista
        }
        calificacionRepository.save(cal);
        return mapToDTO(cal);
    }

    @Override
    @Transactional
    public DTOCalificacion update(Long id, DTOCalificacion dto) {
        Calificacion cal = calificacionRepository.findById(id).orElse(null);
        if (cal == null) return null;

        cal.setPuntaje(dto.getPuntaje());
        cal.setComentario(dto.getComentario());
        if (dto.getListaId() != null) {
            cal.setLista(listaRepository.findById(dto.getListaId()).orElse(null));
        }
        calificacionRepository.save(cal);
        return mapToDTO(cal);
    }

    @Override
    public void delete(Long id) {
        calificacionRepository.deleteById(id);
    }

    @Override
    public List<DTOCalificacion> listAll() {
        return calificacionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DTOCalificacion mapToDTO(Calificacion c) {
        if (c == null) return null;
        return new DTOCalificacion(
                c.getId(),
                c.getPuntaje(),
                c.getComentario(),
                (c.getCliente() != null ? c.getCliente().getId() : null),
                (c.getReceta() != null ? c.getReceta().getId() : null),
                (c.getLista() != null ? c.getLista().getId() : null) // 🔹 Mapeo del campo lista
        );
    }
}
