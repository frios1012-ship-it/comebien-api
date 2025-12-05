package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.comebienapi.entities.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Buscar cliente por coincidencia parcial en nombre
    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Cliente> buscarPorNombre(@Param("nombre") String nombre);

    // Clientes con más listas (otro reporte)
    @Query(value = """
        SELECT c.name AS nombreCliente, COUNT(l.id) AS totalListas
        FROM clientes c
        LEFT JOIN listas l ON l.clientes_id = c.id
        GROUP BY c.name
        ORDER BY COUNT(l.id) DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Object[]> clientesConMasListas();
}
