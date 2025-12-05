package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.comebienapi.dtos.DTOIngrediente;
import pe.edu.upc.comebienapi.entities.Ingrediente;
import pe.edu.upc.comebienapi.repositories.IngredienteRepository;
import pe.edu.upc.comebienapi.services.IngredienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public DTOIngrediente add(DTOIngrediente dto) {
        Ingrediente a = new Ingrediente(null, dto.getNombre(),
                dto.getCaloriasPor100g(), dto.getProteinasPor100g(),
                dto.getCarbohidratosPor100g(), dto.getGrasasPor100g());
        a = ingredienteRepository.save(a);
        dto.setId(a.getId());
        return dto;
    }

    @Override
    public DTOIngrediente update(Long id, DTOIngrediente dto) {
        Ingrediente a = ingredienteRepository.findById(id).orElse(null);
        if (a == null) return null;
        a.setNombre(dto.getNombre());
        a.setCaloriasPor100g(dto.getCaloriasPor100g());
        a.setProteinasPor100g(dto.getProteinasPor100g());
        a.setCarbohidratosPor100g(dto.getCarbohidratosPor100g());
        a.setGrasasPor100g(dto.getGrasasPor100g());
        ingredienteRepository.save(a);
        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        ingredienteRepository.deleteById(id);
    }

    @Override
    public List<DTOIngrediente> listAll() {
        return ingredienteRepository.findAll().stream()
                .map(a -> new DTOIngrediente(a.getId(), a.getNombre(),
                        a.getCaloriasPor100g(), a.getProteinasPor100g(),
                        a.getCarbohidratosPor100g(), a.getGrasasPor100g()))
                .collect(Collectors.toList());
    }
}
