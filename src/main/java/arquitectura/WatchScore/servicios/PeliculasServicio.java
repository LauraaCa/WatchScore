package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Peliculas;
import arquitectura.WatchScore.persistencia.repositorio.PeliculasRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PeliculasServicio {
    PeliculasRepositorio peliculasRepositorio;

    public PeliculasDTO crear(PeliculasDTO peliculasDTO){
        Peliculas peliculas = Peliculas.builder()
                .idPelicula(peliculasDTO.idPelicula())
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

    public List<Peliculas> obtenerTodo(){
        return peliculasRepositorio.findAll();
    }
}
