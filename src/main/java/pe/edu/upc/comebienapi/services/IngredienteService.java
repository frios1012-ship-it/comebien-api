package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOIngrediente;
import java.util.List;

public interface IngredienteService {
    DTOIngrediente add(DTOIngrediente dto);
    DTOIngrediente update(Long id, DTOIngrediente dto);
    void delete(Long id);
    List<DTOIngrediente> listAll();
}
