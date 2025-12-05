package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.comebienapi.entities.Receta;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    @Query("""
        SELECT DISTINCT r FROM Receta r
        LEFT JOIN FETCH r.ingredientes ir
        LEFT JOIN FETCH ir.ingrediente ing
    """)
    List<Receta> findAllWithIngredientes();

    @Query("""
        SELECT DISTINCT r FROM Receta r
        LEFT JOIN FETCH r.ingredientes ir
        LEFT JOIN FETCH ir.ingrediente ing
        WHERE r.id = :id
    """)
    Receta findByIdWithIngredientes(@Param("id") Long id);
}
