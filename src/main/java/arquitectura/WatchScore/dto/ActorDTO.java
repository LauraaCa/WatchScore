package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ActorDTO (@NotBlank String nombre,
                        LocalDate fechaNacimiento,
                        String nacionalidad,
                        String genero,
                        List<String> peliculas,
                        List<String> series) {
}