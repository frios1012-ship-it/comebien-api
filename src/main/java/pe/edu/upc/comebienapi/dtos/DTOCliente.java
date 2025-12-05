package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCliente {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private Double pesoObjetivo;
    private Double talla;
    private Long userId;
}
