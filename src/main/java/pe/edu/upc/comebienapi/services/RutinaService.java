package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTORutina;
import java.util.List;

public interface RutinaService {
    DTORutina add(DTORutina dto);
    DTORutina update(Long id, DTORutina dto);
    void delete(Long id);
    List<DTORutina> listAll();
}
