package pe.edu.upc.comebienapi.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOFavorito {
    private Long id;
    private LocalDate fechaGuardado;
    private String notas;

    private Long clienteId;
    private String clienteNombre;
    private Long listaId;
    private String listaNombre;
}
