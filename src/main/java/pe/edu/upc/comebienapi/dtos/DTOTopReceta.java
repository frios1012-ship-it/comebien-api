package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOTopReceta {
    private String nombreReceta;
    private Double promedio;
    private Long totalCalificaciones;
}
