package pe.edu.upc.comebienapi.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOEjercicioRutina {
    private Long id;
    private int orden;
    private int series;
    private int repeticiones;
    private int duracionSeg;
    private String intensidad;
    private String tiempo;

    private Long rutinaId;
    private Long ejercicioId;
    private String ejercicioNombre; // de solo lectura
}
