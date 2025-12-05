package pe.edu.upc.comebienapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rutinas")
public class Rutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String objetivo;
    private String nivel;
    private double duracionTotal;
    private String imagen;
    private LocalDate fechaCreacion;

    // Relación con EjerciciosRutina
    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EjercicioRutina> ejercicios;

    // Relación con Lista
    @ManyToOne
    @JoinColumn(name = "listas_id")
    private Lista lista;
}
