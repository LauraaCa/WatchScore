package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    private String nombreActor;
    @JsonIgnore
    @ManyToMany(mappedBy = "actores")
    private Set<Pelicula> peliculas = new HashSet<>();
    @ManyToMany(mappedBy = "actoresSerie")
    private Set<Serie> series = new HashSet<>();
}
