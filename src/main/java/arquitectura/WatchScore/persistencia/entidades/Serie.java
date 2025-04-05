package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long idSerie;
    private String tituloSerie;
    private String directorSerie;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lanzamiento;

    private Long temporadas;
    private Long capitulos;
    private String duracionCapitulo;
    private String sinopsis;
    private float calificacion;

    @ManyToMany
    @JoinTable(
            name = "serie_actor",
            joinColumns = @JoinColumn(name = "serie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actoresSerie= new HashSet<>();

}
