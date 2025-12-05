package pe.edu.upc.comebienapi.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCalificacionSimple {
    private Long id;
    private int puntaje;
    private String comentario;
}
