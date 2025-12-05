package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOCalificacion;
import java.util.List;

public interface CalificacionService {
    DTOCalificacion add(DTOCalificacion dto);
    DTOCalificacion update(Long id, DTOCalificacion dto);
    void delete(Long id);
    List<DTOCalificacion> listAll();
}
