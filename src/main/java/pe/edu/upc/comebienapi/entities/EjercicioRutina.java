package pe.edu.upc.comebienapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ejercicios_rutina")
public class EjercicioRutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orden;
    private int series;
    private int repeticiones;

    @Column(name = "duracion_seg")
    private int duracionSeg;

    private String intensidad;
    private String tiempo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutinas_id")
    private Rutina rutina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ejercicios_id")
    private Ejercicio ejercicio;
}
