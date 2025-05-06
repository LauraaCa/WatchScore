package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Director;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.DirectorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PeliculaServicio {
    PeliculaRepositorio peliculaRepositorio;
    ActorRepositorio actorRepositorio;
    DirectorRepositorio directorRepositorio;

    public PeliculasDTO crearPelicula(PeliculasDTO peliculasDTO) {
        if (peliculasDTO.actores() == null) return null;

        Set<Actor> actores = new HashSet<>();

        for (String nombre : peliculasDTO.actores()) {
            Optional<Actor> actorOpt = actorRepositorio.findByNombre(nombre);
            if (actorOpt.isPresent()) {
                actores.add(actorOpt.get());
            } else {
                throw new RuntimeException("Actor no encontrado: " + nombre);
            }
        }

        Director director = directorRepositorio.findByNombre(peliculasDTO.director())
                .orElseThrow(() -> new RuntimeException("Director no encontrado: " + peliculasDTO.director()));

        Pelicula pelicula = Pelicula.builder()
                .titulo(peliculasDTO.titulo())
                .director(director)
                .lanzamiento(peliculasDTO.lanzamiento())
                .duracion(peliculasDTO.duracion())
                .genero(peliculasDTO.genero())
                .sipnosis(peliculasDTO.sipnosis())
                .calificacion(peliculasDTO.calificacion())
                .actores(actores)
                .build();

        Pelicula guardada = peliculaRepositorio.save(pelicula);

        List<String> nombresActores = guardada.getActores().stream()
                .map(Actor::getNombre)
                .toList();

        return new PeliculasDTO(
                guardada.getTitulo(),
                guardada.getDirector().getNombre(),
                guardada.getLanzamiento(),
                guardada.getDuracion(),
                guardada.getGenero(),
                guardada.getSipnosis(),
                guardada.getCalificacion(),
                nombresActores
        );
    }


    public List<Pelicula> listarPeliculas() {
        return peliculaRepositorio.findAll();
    }

    public Pelicula agregarActorAPelicula(Long peliculaId, Long actorId) {
        Optional<Pelicula> peliculaOpt = peliculaRepositorio.findById(peliculaId);
        Optional<Actor> actorOpt = actorRepositorio.findById(actorId);

        if (peliculaOpt.isPresent() && actorOpt.isPresent()) {
            Pelicula pelicula = peliculaOpt.get();
            Actor actor = actorOpt.get();

            pelicula.getActores().add(actor);
            actor.getPeliculas().add(pelicula);

            peliculaRepositorio.save(pelicula);
            return pelicula;
        }
        return null;
    }

    public Pelicula obtenerXtitulo (String titulo){
        return peliculaRepositorio.findByTitulo(titulo);
    }
}