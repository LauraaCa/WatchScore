package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="actores")

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String nacionalidad;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String genero;

    @JsonIgnore
    @ManyToMany(mappedBy ="actores", fetch = FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy ="actores")
    private Set<Serie> series = new HashSet<>();
}