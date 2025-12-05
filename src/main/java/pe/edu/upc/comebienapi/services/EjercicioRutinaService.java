package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOEjercicioRutina;
import java.util.List;

public interface EjercicioRutinaService {
    DTOEjercicioRutina add(DTOEjercicioRutina dto);
    DTOEjercicioRutina update(Long id, DTOEjercicioRutina dto);
    void delete(Long id);
    List<DTOEjercicioRutina> listAll();
}
