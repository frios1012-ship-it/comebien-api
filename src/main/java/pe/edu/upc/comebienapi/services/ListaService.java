package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOLista;
import java.util.List;

public interface ListaService {
    DTOLista add(DTOLista dto);
    DTOLista update(Long id, DTOLista dto);
    void delete(Long id);
    List<DTOLista> listAll();
}
