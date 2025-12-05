package pe.edu.upc.comebienapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "listas")
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    // 🔹 Relación con cliente (muchas listas pueden pertenecer a un cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientes_id", nullable = false)
    private Cliente cliente;

    // 🔹 Relación hacia una receta (cada lista puede tener 1 receta asociada)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recetas_id")
    private Receta receta;

    // 🔹 Relación hacia una rutina (cada lista puede tener 1 rutina asociada)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rutinas_id")
    private Rutina rutina;

    // 🔹 Calificaciones asociadas a esta lista
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Calificacion> calificaciones;

    // 🔹 Favoritos asociados a esta lista
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos;
}
