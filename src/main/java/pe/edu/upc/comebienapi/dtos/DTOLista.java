package pe.edu.upc.comebienapi.dtos;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOLista {
    private Long id;
    private String nombre;
    private LocalDate fechaCreacion;

    private Long clienteId;

    private DTOReceta receta;
    private DTORutina rutina;
    private List<DTOCalificacion> calificaciones;
    private List<DTOFavorito> favoritos;

}
