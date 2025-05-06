package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.ActorDTO;
import arquitectura.WatchScore.dto.DirectorDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Director;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.persistencia.repositorio.DirectorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class DirectorServicio {
    DirectorRepositorio directorRepositorio;

    public DirectorDTO crear(DirectorDTO directorDTO){

        Director director= Director.builder()
                .nombre(directorDTO.nombre())
                .fechaNacimiento(directorDTO.fechaNacimiento())
                .nacionalidad(directorDTO.nacionalidad())
                .genero(directorDTO.genero())
                .build();

        if (directorRepositorio.save(director).getId()>0)
            return directorDTO;
        else return null;
    }

    public List<DirectorDTO> obtener(){
        List<Director> directores = directorRepositorio.findAll();
        return directores.stream()
                .map(director ->  {List<String> titulosPeliculas = director.getPeliculas().stream()
                        .map(Pelicula::getTitulo)
                        .toList();

                    List<String> titulosSeries = director.getSeries().stream()
                            .map(Serie::getTitulo)
                            .toList();

                    return new DirectorDTO(
                            director.getNombre(),
                            director.getFechaNacimiento(),
                            director.getNacionalidad(),
                            director.getGenero(),
                            titulosPeliculas,
                            titulosSeries
                    );})
                .toList();
    }

    public List<Director> obtenerXnombre(String palabraClave){
        return directorRepositorio.findByNombreContainingIgnoreCase(palabraClave);
    }

}