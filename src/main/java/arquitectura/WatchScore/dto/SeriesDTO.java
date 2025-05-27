package arquitectura.WatchScore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record SeriesDTO(@NotBlank String titulo,
                        String director,
                        LocalDate lanzamiento,
                        Long temporadas,
                        Long capitulos,
                        String duracionCapitulo,
                        String sinopsis,
                        String genero,
                        float calificacion,
                        List<String> actores) {
}
