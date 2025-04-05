package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

public record ActorDTO (@NotBlank String nombre,
                        List<String> peliculas) {
}
