package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="series")

public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //
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

}
