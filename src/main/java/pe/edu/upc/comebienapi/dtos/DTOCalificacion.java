package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCalificacion {
    private Long id;
    private int puntaje;
    private String comentario;
    private Long clienteId;
    private Long recetaId;
    private Long listaId;
}
