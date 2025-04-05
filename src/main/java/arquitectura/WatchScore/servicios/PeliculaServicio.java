package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeliculaServicio {
    PeliculaRepositorio peliculasRepositorio;
    ActorRepositorio actorRepositorio;


    public List<Pelicula> obtenerTodo(){
        return peliculasRepositorio.findAll();
    }

    public PeliculasDTO crear(PeliculasDTO peliculasDTO) {
            Set<Actor> actores = peliculasDTO.actores().stream()
                    .map(nombre -> actorRepositorio.findByNombreActor(nombre)
                            .orElseThrow(() -> new RuntimeException("Actor no encontrado: " + nombre)))
                    .collect(Collectors.toSet());

            Pelicula pelicula = Pelicula.builder()
                    .tituloPelicula(peliculasDTO.tituloPelicula())
                    .directorPelicula(peliculasDTO.directorPelicula())
                    .lanzamiento(peliculasDTO.lanzamiento())
                    .duracion(peliculasDTO.duracion())
                    .genero(peliculasDTO.genero())
                    .sipnosis(peliculasDTO.sipnosis())
                    .calificacion(peliculasDTO.calificacion())
                    .actores(actores)
                    .build();

            Pelicula guardada = peliculasRepositorio.save(pelicula);

            List<String> nombresActores = guardada.getActores().stream()
                    .map(Actor::getNombreActor)
                    .toList();

            return new PeliculasDTO(
                    guardada.getTituloPelicula(),
                    guardada.getDirectorPelicula(),
                    guardada.getLanzamiento(),
                    guardada.getDuracion(),
                    guardada.getGenero(),
                    guardada.getSipnosis(),
                    guardada.getCalificacion(),
                    nombresActores
            );
        }

    public List<Pelicula> obtenerPeliculasPorActor(String nombreActor) {
        return peliculasRepositorio.findByActores(nombreActor);
    }

    public Pelicula obtenerTitulo(@PathVariable String tituloPelicula){
            return peliculasRepositorio.findByTituloPelicula(tituloPelicula);
    }

}
