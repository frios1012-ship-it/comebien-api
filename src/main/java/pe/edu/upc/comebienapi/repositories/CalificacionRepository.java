package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.comebienapi.entities.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
}
