package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.DTOEjercicioRutina;
import pe.edu.upc.comebienapi.entities.EjercicioRutina;
import pe.edu.upc.comebienapi.repositories.EjercicioRepository;
import pe.edu.upc.comebienapi.repositories.RutinaRepository;
import pe.edu.upc.comebienapi.repositories.EjercicioRutinaRepository;
import pe.edu.upc.comebienapi.services.EjercicioRutinaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EjercicioRutinaServiceImpl implements EjercicioRutinaService {

    @Autowired
    private EjercicioRutinaRepository ejercicioRutinaRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    @Autowired
    private RutinaRepository rutinaRepository;

    @Override
    @Transactional
    public DTOEjercicioRutina add(DTOEjercicioRutina dto) {
        EjercicioRutina e = new EjercicioRutina();
        e.setOrden(dto.getOrden());
        e.setSeries(dto.getSeries());
        e.setRepeticiones(dto.getRepeticiones());
        e.setDuracionSeg(dto.getDuracionSeg());
        e.setIntensidad(dto.getIntensidad());
        e.setTiempo(dto.getTiempo());
        e.setEjercicio(ejercicioRepository.findById(dto.getEjercicioId()).orElse(null));
        e.setRutina(rutinaRepository.findById(dto.getRutinaId()).orElse(null));
        e = ejercicioRutinaRepository.save(e);
        return mapToDTO(e);
    }

    @Override
    @Transactional
    public DTOEjercicioRutina update(Long id, DTOEjercicioRutina dto) {
        EjercicioRutina e = ejercicioRutinaRepository.findById(id).orElse(null);
        if (e == null) return null;

        e.setOrden(dto.getOrden());
        e.setSeries(dto.getSeries());
        e.setRepeticiones(dto.getRepeticiones());
        e.setDuracionSeg(dto.getDuracionSeg());
        e.setIntensidad(dto.getIntensidad());
        e.setTiempo(dto.getTiempo());
        e.setEjercicio(ejercicioRepository.findById(dto.getEjercicioId()).orElse(null));
        e.setRutina(rutinaRepository.findById(dto.getRutinaId()).orElse(null));
        ejercicioRutinaRepository.save(e);

        return mapToDTO(e);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ejercicioRutinaRepository.deleteById(id);
    }

    @Override
    public List<DTOEjercicioRutina> listAll() {
        return ejercicioRutinaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private DTOEjercicioRutina mapToDTO(EjercicioRutina e) {
        DTOEjercicioRutina dto = new DTOEjercicioRutina();
        dto.setId(e.getId());
        dto.setOrden(e.getOrden());
        dto.setSeries(e.getSeries());
        dto.setRepeticiones(e.getRepeticiones());
        dto.setDuracionSeg(e.getDuracionSeg());
        dto.setIntensidad(e.getIntensidad());
        dto.setTiempo(e.getTiempo());
        dto.setEjercicioId(e.getEjercicio() != null ? e.getEjercicio().getId() : null);
        dto.setRutinaId(e.getRutina() != null ? e.getRutina().getId() : null);
        dto.setEjercicioNombre(e.getEjercicio() != null ? e.getEjercicio().getNombre() : null);
        return dto;
    }
}
