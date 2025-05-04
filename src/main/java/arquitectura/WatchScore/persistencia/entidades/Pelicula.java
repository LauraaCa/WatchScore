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
@Builder
@Getter
@Setter
@Table(name="peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String director;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lanzamiento;

    private String duracion; //2h 30m
    private String genero;
    private String sipnosis;
    private float calificacion;

    @ManyToMany
    @JoinTable(
            name="pelicula_actor",
            joinColumns = @JoinColumn(name="pelicula_id"),
            inverseJoinColumns =  @JoinColumn(name="actor_id")
    )
    private Set<Actor> actores = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "peliculas")
    private Set<Lista> listas = new HashSet<>();


}
