package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.ActorDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor

public class ActorServicio {
    ActorRepositorio actorRepositorio;

    public ActorDTO crear(ActorDTO actorDTO){

        Actor actor = Actor.builder()
                .nombre(actorDTO.nombre())
                .fechaNacimiento(actorDTO.fechaNacimiento())
                .nacionalidad(actorDTO.nacionalidad())
                .genero(actorDTO.genero())
                .build();

        if (actorRepositorio.save(actor).getId()>0)
            return actorDTO;
        else return null;
    }

    public List<ActorDTO> obtener(){
        List<Actor> actores = actorRepositorio.findAllByOrderByNombreAsc();
        return actores.stream()
                .map(actor ->  {List<String> titulosPeliculas = actor.getPeliculas().stream()
                        .map(Pelicula::getTitulo)
                        .toList();

                    List<String> titulosSeries = actor.getSeries().stream()
                            .map(Serie::getTitulo)
                            .toList();

                    return new ActorDTO(
                            actor.getNombre(),
                            actor.getFechaNacimiento(),
                            actor.getNacionalidad(),
                            actor.getGenero(),
                            titulosPeliculas,
                            titulosSeries
                    );})
                .toList();
    }

    public List<Actor> obtenerXnombre(String palabraClave){
        return actorRepositorio.findByNombreContainingIgnoreCase(palabraClave);
    }

}