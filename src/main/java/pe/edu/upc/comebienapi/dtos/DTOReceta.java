package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOReceta {
    private Long id;
    private String nombre;
    private String instrucciones;
    private Integer tiempoPreparacion;
    private Double caloriasTotales;
    private String imagen;
    private Long clienteId;
    private List<DTOIngredienteReceta> ingredientes;
}
