// RutinaServiceImpl.java - FECHA AUTO GENERADA
package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.DTORutina;
import pe.edu.upc.comebienapi.entities.Lista;
import pe.edu.upc.comebienapi.entities.Rutina;
import pe.edu.upc.comebienapi.repositories.ListaRepository;
import pe.edu.upc.comebienapi.repositories.RutinaRepository;
import pe.edu.upc.comebienapi.services.RutinaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutinaServiceImpl implements RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Override
    @Transactional
    public DTORutina add(DTORutina dto) {

        Rutina r = new Rutina();
        r.setNombre(dto.getNombre());
        r.setObjetivo(dto.getObjetivo());
        r.setNivel(dto.getNivel());
        r.setDuracionTotal(dto.getDuracionTotal());
        r.setImagen(dto.getImagen());

        // 🟢 FECHA CREADA AUTOMÁTICAMENTE (HOY)
        r.setFechaCreacion(LocalDate.now());

        // Lista opcional
        if (dto.getListaId() != null) {
            Lista lista = listaRepository.findById(dto.getListaId()).orElse(null);
            r.setLista(lista);
        }

        r = rutinaRepository.save(r);
        return mapToDTO(r);
    }

    @Override
    @Transactional
    public DTORutina update(Long id, DTORutina dto) {

        Rutina r = rutinaRepository.findById(id).orElse(null);
        if (r == null) return null;

        r.setNombre(dto.getNombre());
        r.setObjetivo(dto.getObjetivo());
        r.setNivel(dto.getNivel());
        r.setDuracionTotal(dto.getDuracionTotal());
        r.setImagen(dto.getImagen());

        // 🟢 FECHA ACTUALIZADA AUTOMÁTICAMENTE (HOY)
        r.setFechaCreacion(LocalDate.now());

        // Lista opcional
        if (dto.getListaId() != null) {
            Lista lista = listaRepository.findById(dto.getListaId()).orElse(null);
            r.setLista(lista);
        } else {
            r.setLista(null);
        }

        rutinaRepository.save(r);
        return mapToDTO(r);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rutinaRepository.deleteById(id);
    }

    @Override
    public List<DTORutina> listAll() {
        return rutinaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DTORutina mapToDTO(Rutina r) {
        DTORutina dto = new DTORutina();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setObjetivo(r.getObjetivo());
        dto.setNivel(r.getNivel());
        dto.setDuracionTotal(r.getDuracionTotal());
        dto.setImagen(r.getImagen());
        dto.setFechaCreacion(r.getFechaCreacion());
        dto.setListaId(r.getLista() != null ? r.getLista().getId() : null);
        return dto;
    }
}
