package arquitectura.WatchScore.persistencia.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="listas")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_identificacion")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "listas_peliculas",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private List<Pelicula> peliculas;

    @ManyToMany
    @JoinTable(
            name = "listas_series",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "serie_id")
    )
    private List<Serie> series;

}
