package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.Rutina;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {
}
