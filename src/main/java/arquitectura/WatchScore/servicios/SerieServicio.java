package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.persistencia.repositorio.SerieRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SerieServicio {
    SerieRepositorio seriesRepositorio;

    public SeriesDTO crear(SeriesDTO seriesDTO){
        Serie series = Serie.builder()
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

    public List<Serie> obtenerTodo(){
        return seriesRepositorio.findAll();
    }
}
