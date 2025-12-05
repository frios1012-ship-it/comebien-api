package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.comebienapi.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {}
