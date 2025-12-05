package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.Calificacion;
import java.util.List;

@Repository
public interface ReporteGeneralRepository extends JpaRepository<Calificacion, Long> {

    // Native: Top recetas más agregadas a favoritos
    @Query(value = """
        SELECT l.nombre AS nombreLista, COUNT(f.id) AS totalFavoritos
        FROM favoritos f
        JOIN listas l ON f.listas_id = l.id
        GROUP BY l.nombre
        ORDER BY COUNT(f.id) DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Object[]> topListasFavoritas();

    // JPQL: Promedio de calificación por cliente
    @Query("SELECT c.cliente.nombre, AVG(c.puntaje) FROM Calificacion c GROUP BY c.cliente.nombre")
    List<Object[]> promedioCalificacionPorCliente();
}
