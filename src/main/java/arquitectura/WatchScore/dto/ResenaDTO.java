package arquitectura.WatchScore.dto;

import arquitectura.WatchScore.persistencia.entidades.Usuario;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public record ResenaDTO(
        @NotBlank String comentario,
        float calificacion,
        String nombreUsuario
) {}

