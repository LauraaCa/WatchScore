package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;
import java.util.Set;

public record ActorDTO (@NotBlank String nombre,
                        List<String> peliculas,
                        List<String> series) {
}
