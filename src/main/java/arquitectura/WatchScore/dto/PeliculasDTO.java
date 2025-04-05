package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record PeliculasDTO(@NotBlank String tituloPelicula,
                           String directorPelicula,
                           LocalDate lanzamiento,
                           String duracion,
                           String genero,
                           String sipnosis,
                           float calificacion,
                           List<String> actores){
}
