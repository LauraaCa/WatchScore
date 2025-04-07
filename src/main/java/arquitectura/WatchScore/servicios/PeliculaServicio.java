package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeliculaServicio {
    PeliculaRepositorio peliculaRepositorio;
    ActorRepositorio actorRepositorio;
    ActorServicio actorServicio;

    public Pelicula crearPelicula(Pelicula pelicula) {
        // Si la película tiene actores, los agregamos a la base de datos
        if (pelicula.getActores() != null) {
            Set<Actor> actores = new HashSet<>();
            for (Actor actor : pelicula.getActores()) {
                Optional<Actor> actorOpt = actorRepositorio.findByNombre(actor.getNombre());
                if (actorOpt.isPresent()) {
                    actores.add(actorOpt.get());
                } else {
                    // Manejo de error: actor no encontrado
                    throw new RuntimeException("Actor no encontrado: " + actor.getNombre());
                }
            }
            pelicula.setActores(actores);
        }
        return peliculaRepositorio.save(pelicula);
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
            actor.getPeliculas().add(pelicula); // Asegúrate de mantener la relación bidireccional

            peliculaRepositorio.save(pelicula);
            return pelicula;
        }
        return null; // O lanzar una excepción si prefieres
    }
}

