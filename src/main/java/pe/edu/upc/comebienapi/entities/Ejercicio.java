package pe.edu.upc.comebienapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "ejercicios")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String tipo;  // Ej: fuerza, cardio
    private String grupoMuscular;
    private String metricaBase;
    private String imagen; // ruta o base64
    private LocalDate fechaCreacion;

}
