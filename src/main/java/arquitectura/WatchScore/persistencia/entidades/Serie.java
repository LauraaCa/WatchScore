package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="series")

public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lanzamiento;

    private String genero;
    private Long temporadas;
    private Long capitulos;
    private String duracionCapitulo;
    private String sinopsis;
    private float calificacion;

    @ManyToMany
    @JoinTable(
            name="serie_actor",
            joinColumns = @JoinColumn(name="serie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    private Set<Actor> actores = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @JsonIgnore
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resena> resenas = new HashSet<>();
}