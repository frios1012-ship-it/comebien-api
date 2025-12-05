package pe.edu.upc.comebienapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOPromedioCalificacionCliente {
    private String cliente;
    private Double promedioPuntaje;
}
