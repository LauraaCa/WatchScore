package arquitectura.WatchScore.dto;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioDTO (@NotBlank Long identificacion,
                          String email,
                          String nombre,
                          String apellido,
                          Long celular,
                          LocalDate fechaNacimiento,
                          String ciudad,
                          String contrasena) {
}
