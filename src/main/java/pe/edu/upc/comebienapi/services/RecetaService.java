package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOReceta;
import java.util.List;

public interface RecetaService {
    DTOReceta add(DTOReceta dto);
    DTOReceta update(Long id, DTOReceta dto);
    void delete(Long id);
    List<DTOReceta> listAll();
    DTOReceta findById(Long id);
}
