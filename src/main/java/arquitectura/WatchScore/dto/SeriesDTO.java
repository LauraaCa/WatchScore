package arquitectura.WatchScore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record SeriesDTO(@NotBlank String tituloSerie,
                        String directorSerie,
                        LocalDate lanzamiento,
                        Long temporadas,
                        Long capitulos,
                        String duracionCapitulo,
                        String sinopsis,
                        float calificacion,
                        List<String> actores) {
}
