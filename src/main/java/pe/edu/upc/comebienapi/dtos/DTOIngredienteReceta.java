package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOIngredienteReceta {
    private Long id;
    private Double cantidad;
    private String unidad;
    private Long ingredienteId;
    private String ingredienteNombre;
}
