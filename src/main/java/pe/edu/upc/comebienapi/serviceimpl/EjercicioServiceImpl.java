package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.DTOEjercicio;
import pe.edu.upc.comebienapi.entities.Ejercicio;
import pe.edu.upc.comebienapi.repositories.EjercicioRepository;
import pe.edu.upc.comebienapi.services.EjercicioService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioServiceImpl implements EjercicioService {

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Override
    @Transactional
    public DTOEjercicio add(DTOEjercicio dto) {

        Ejercicio e = new Ejercicio();
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setTipo(dto.getTipo());
        e.setGrupoMuscular(dto.getGrupoMuscular());
        e.setMetricaBase(dto.getMetricaBase());
        e.setImagen(dto.getImagen());

        // 👇 Siempre asignar fecha al crear
        e.setFechaCreacion(LocalDate.now());

        e = ejercicioRepository.save(e);
        return mapToDTO(e);
    }

    @Override
    @Transactional
    public DTOEjercicio update(Long id, DTOEjercicio dto) {

        Ejercicio e = ejercicioRepository.findById(id).orElse(null);
        if (e == null) return null;

        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setTipo(dto.getTipo());
        e.setGrupoMuscular(dto.getGrupoMuscular());
        e.setMetricaBase(dto.getMetricaBase());
        e.setImagen(dto.getImagen());

        // 👇 JAMÁS cambiar fechaCreacion al editar
        // e.setFechaCreacion(e.getFechaCreacion());

        ejercicioRepository.save(e);
        return mapToDTO(e);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ejercicioRepository.deleteById(id);
    }

    @Override
    public List<DTOEjercicio> listAll() {
        return ejercicioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DTOEjercicio mapToDTO(Ejercicio e) {
        DTOEjercicio dto = new DTOEjercicio();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setDescripcion(e.getDescripcion());
        dto.setTipo(e.getTipo());
        dto.setGrupoMuscular(e.getGrupoMuscular());
        dto.setMetricaBase(e.getMetricaBase());
        dto.setImagen(e.getImagen());
        dto.setFechaCreacion(e.getFechaCreacion());
        return dto;
    }
}

