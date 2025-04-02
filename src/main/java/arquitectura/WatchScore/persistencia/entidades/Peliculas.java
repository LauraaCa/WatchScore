package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name="peliculas")
public class Peliculas {
    @Id
    private Long idPelicula;
    private String tituloPelicula;
    private String directorPelicula;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lanzamiento;

    private String duracion; //2h 30m
    private String genero;
    private String sipnosis;
    private float calificacion;

}
