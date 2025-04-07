package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record PeliculasDTO(@NotBlank String titulo,
                           String director,
                           LocalDate lanzamiento,
                           String duracion,
                           String genero,
                           String sipnosis,
                           float calificacion,
                           List<String> actores){
}
