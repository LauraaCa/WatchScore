package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.ActorDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class ActorServicio {
    ActorRepositorio actorRepositorio;
    PeliculaRepositorio peliculasRepositorio;

    public ActorDTO crear(ActorDTO actorDTO){

        Actor actor = Actor.builder()
                .nombreActor(actorDTO.nombreActor())
                .build();

        if (actorRepositorio.save(actor).getId()>0)
            return actorDTO;
        else return null;
    }


    public List<ActorDTO> obtener(){
        List<Actor> actores = actorRepositorio.findAll();
        return actores.stream()
                .map(actor ->  {List<String> titulosPeliculas = actor.getPeliculas().stream()
                        .map(Pelicula::getTituloPelicula)
                        .toList();

                    return new ActorDTO(
                            actor.getNombreActor(),
                            titulosPeliculas
                    );})
                .toList();
    }
}
