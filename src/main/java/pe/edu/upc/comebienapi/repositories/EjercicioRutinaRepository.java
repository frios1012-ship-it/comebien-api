package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.EjercicioRutina;

@Repository
public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, Long> {
}
