package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.Receta;
import java.util.List;

@Repository
public interface ReporteRecetaRepository extends JpaRepository<Receta, Long> {

    // 🍲 1️⃣ Native: Top 5 recetas mejor calificadas
    @Query(value = """
        SELECT r.nombre, ROUND(AVG(c.puntaje), 2) AS promedio, COUNT(c.id)
        FROM calificaciones c
        JOIN recetas r ON c.recetas_id = r.id
        GROUP BY r.nombre
        ORDER BY promedio DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Object[]> top5Recetas();


    // JPQL: Promedio de calorías por cliente
    @Query("SELECT r.cliente.nombre, AVG(r.caloriasTotales) FROM Receta r GROUP BY r.cliente.nombre")
    List<Object[]> promedioCaloriasPorCliente();

    // Derived: Buscar recetas por nombre
    List<Receta> findByNombreContainingIgnoreCase(String nombre);

    // Native: Total recetas por cliente
    @Query(value = """
        SELECT c.name AS cliente, COUNT(r.id) AS totalRecetas
        FROM recetas r
        JOIN clientes c ON r.clientes_id = c.id
        GROUP BY c.name
        ORDER BY COUNT(r.id) DESC
    """, nativeQuery = true)
    List<Object[]> totalRecetasPorCliente();

}
