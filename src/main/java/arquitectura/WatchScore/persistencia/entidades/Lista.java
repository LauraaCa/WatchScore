package arquitectura.WatchScore.persistencia.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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
            name = "listas_personales",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private Set<Pelicula> peliculas = new HashSet<>();

}
