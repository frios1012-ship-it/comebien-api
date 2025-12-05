package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.comebienapi.entities.IngredienteReceta;

import java.util.List;

public interface IngredienteRecetaRepository extends JpaRepository<IngredienteReceta, Long> {
    List<IngredienteReceta> findByRecetaId(Long recetaId);
}
