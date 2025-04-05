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
    private String nombre;

    @JsonIgnore
    @ManyToMany(mappedBy ="actores", fetch = FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy ="actores")
    private Set<Serie> series = new HashSet<>();
}

