package pe.edu.upc.comebienapi.dtos;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTORutina {
    private Long id;
    private String nombre;
    private String objetivo;
    private String nivel;
    private double duracionTotal;
    private String imagen;
    private LocalDate fechaCreacion;

    private Long listaId;

    private List<DTOEjercicioRutina> ejercicios; // lista de ejercicios asociados
}
