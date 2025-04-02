package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Series;
import arquitectura.WatchScore.persistencia.repositorio.SeriesRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeriesServicio {
    SeriesRepositorio seriesRepositorio;

    public SeriesDTO crear(SeriesDTO seriesDTO){
        Series series = Series.builder()
                .tituloSerie(seriesDTO.tituloSerie())
                .directorSerie(seriesDTO.directorSerie())
                .lanzamiento(seriesDTO.lanzamiento())
                .temporadas(seriesDTO.temporadas())
                .capitulos(seriesDTO.capitulos())
                .duracionCapitulo(seriesDTO.duracionCapitulo())
                .sinopsis(seriesDTO.sinopsis())
                .calificacion(seriesDTO.calificacion())
                .build();

        if (seriesRepositorio.save(series).getIdSerie()>0)
            return seriesDTO;
        else return null;
    }

    public List<Series> obtenerTodo(){
        return seriesRepositorio.findAll();
    }
}
