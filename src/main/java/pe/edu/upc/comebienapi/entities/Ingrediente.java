package pe.edu.upc.comebienapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alimentos")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "calorias_por_100g")
    private Double caloriasPor100g;

    @Column(name = "proteinas_por_100g")
    private Double proteinasPor100g;

    @Column(name = "carbohidratos_por_100g")
    private Double carbohidratosPor100g;

    @Column(name = "grasas_por_100g")
    private Double grasasPor100g;
}
