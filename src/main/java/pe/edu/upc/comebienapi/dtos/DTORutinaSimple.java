package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTORutinaSimple {
    private Long id;
    private String nombre;
    private String objetivo;
}
