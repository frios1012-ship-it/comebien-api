package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOIngredienteReceta;
import java.util.List;

public interface IngredienteRecetaService {
    DTOIngredienteReceta add(DTOIngredienteReceta dto, Long recetaId);
    DTOIngredienteReceta update(Long id, DTOIngredienteReceta dto);
    void delete(Long id);
    List<DTOIngredienteReceta> listByReceta(Long recetaId);
}
