package pe.edu.upc.comebienapi.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOEjercicio {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String grupoMuscular;
    private String metricaBase;
    private String imagen;
    private LocalDate fechaCreacion;
}
