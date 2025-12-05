package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.Rutina;
import java.util.List;

@Repository
public interface ReporteRutinaRepository extends JpaRepository<Rutina, Long> {

    // JPQL: Rutinas por nivel
    @Query("SELECT r.nivel, COUNT(r) FROM Rutina r GROUP BY r.nivel ORDER BY COUNT(r) DESC")
    List<Object[]> rutinasPorNivel();

    // JPQL: Promedio duración por objetivo
    @Query("SELECT r.objetivo, AVG(r.duracionTotal) FROM Rutina r GROUP BY r.objetivo")
    List<Object[]> promedioDuracionPorObjetivo();

    // Derived: Buscar rutinas por objetivo
    List<Rutina> findByObjetivoContainingIgnoreCase(String objetivo);

    // Derived: Rutinas con duración menor a X minutos
    List<Rutina> findByDuracionTotalLessThan(Double minutos);
}
