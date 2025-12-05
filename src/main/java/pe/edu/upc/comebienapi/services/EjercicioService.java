package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOEjercicio;
import java.util.List;

public interface EjercicioService {
    DTOEjercicio add(DTOEjercicio dto);
    DTOEjercicio update(Long id, DTOEjercicio dto);
    void delete(Long id);
    List<DTOEjercicio> listAll();
}
