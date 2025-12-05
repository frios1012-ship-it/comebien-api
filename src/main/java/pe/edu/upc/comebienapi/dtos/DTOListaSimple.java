package pe.edu.upc.comebienapi.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOListaSimple {
    private Long id;
    private String nombre;
    private LocalDate fechaCreacion;
}
