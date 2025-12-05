package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOFavorito;
import java.util.List;

public interface FavoritoService {
    DTOFavorito add(DTOFavorito dto);
    DTOFavorito update(Long id, DTOFavorito dto);
    void delete(Long id);
    List<DTOFavorito> listAll();
}
