package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOIngrediente {
    private Long id;
    private String nombre;
    private Double caloriasPor100g;
    private Double proteinasPor100g;
    private Double carbohidratosPor100g;
    private Double grasasPor100g;
}
