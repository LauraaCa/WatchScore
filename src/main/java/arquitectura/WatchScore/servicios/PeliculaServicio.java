package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PeliculaServicio {
    PeliculaRepositorio peliculasRepositorio;

    public PeliculasDTO crear(PeliculasDTO peliculasDTO){
        Pelicula peliculas = Pelicula.builder()
                .tituloPelicula(peliculasDTO.tituloPelicula())
                .directorPelicula(peliculasDTO.directorPelicula())
                .lanzamiento(peliculasDTO.lanzamiento())
                .duracion(peliculasDTO.duracion())
                .genero(peliculasDTO.genero())
                .sipnosis(peliculasDTO.sipnosis())
                .calificacion(peliculasDTO.calificacion())
                .build();

        if (peliculasRepositorio.save(peliculas).getIdPelicula()>0)
            return peliculasDTO;
        else return null;
    }

    public List<Pelicula> obtenerTodo(){
        return peliculasRepositorio.findAll();
    }
}
