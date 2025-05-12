package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Director;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.persistencia.repositorio.ActorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.DirectorRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.SerieRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SerieServicio {
    SerieRepositorio seriesRepositorio;
    ActorRepositorio actorRepositorio;
    DirectorRepositorio directorRepositorio;

    public SeriesDTO crearSerie(SeriesDTO seriesDTO) {
        if (seriesDTO.actores() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe ingresar al menos un actor.");
        }

        if (seriesRepositorio.findByTitulo(seriesDTO.titulo()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La serie ya está registrada con ese título.");
        }

        Set<Actor> actores = new HashSet<>();

        for (String nombre : seriesDTO.actores()) {
            Optional<Actor> actorOpt = actorRepositorio.findByNombre(nombre);
            if (actorOpt.isPresent()) {
                actores.add(actorOpt.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor no encontrado: " + nombre);
            }
        }

        Director director = directorRepositorio.findByNombre(seriesDTO.director())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director no encontrado: " + seriesDTO.director()));


        Serie serie = Serie.builder()
                .titulo(seriesDTO.titulo())
                .director(director)
                .lanzamiento(seriesDTO.lanzamiento())
                .temporadas(seriesDTO.temporadas())
                .capitulos(seriesDTO.capitulos())
                .duracionCapitulo(seriesDTO.duracionCapitulo())
                .sinopsis(seriesDTO.sinopsis())
                .genero(seriesDTO.genero())
                .calificacion(seriesDTO.calificacion())
                .actores(actores)
                .build();

        Serie guardada = seriesRepositorio.save(serie);

        List<String> nombresActores = guardada.getActores().stream()
                .map(Actor::getNombre)
                .toList();

        return new SeriesDTO(
                guardada.getTitulo(),
                guardada.getDirector().getNombre(),
                guardada.getLanzamiento(),
                guardada.getTemporadas(),
                guardada.getCapitulos(),
                guardada.getDuracionCapitulo(),
                guardada.getSinopsis(),
                guardada.getGenero(),
                guardada.getCalificacion(),
                nombresActores
        );
    }


    public List<Serie> listarSeries(){
        return seriesRepositorio.findAllByOrderByTituloAsc();
    }

    public Serie agregarActorASerie(Long serieId, Long actorId) {
        Optional<Serie> serieOpt = seriesRepositorio.findById(serieId);
        Optional<Actor> actorOpt = actorRepositorio.findById(actorId);

        if (serieOpt.isPresent() && actorOpt.isPresent()) {
            Serie serie = serieOpt.get();
            Actor actor = actorOpt.get();

            serie.getActores().add(actor);
            actor.getSeries().add(serie);

            seriesRepositorio.save(serie);
            return serie;
        }
        return null;
    }

    public Serie obtenerXtitulo (String titulo){
        return seriesRepositorio.findByTitulo(titulo);
    }
}