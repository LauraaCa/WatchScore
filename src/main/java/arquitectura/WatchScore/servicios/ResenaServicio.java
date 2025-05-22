package arquitectura.WatchScore.servicios;


import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.*;
import arquitectura.WatchScore.persistencia.repositorio.*;
import arquitectura.WatchScore.dto.ResenaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaServicio {

    private final ResenaRepositorio resenaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PeliculaRepositorio peliculaRepositorio;
    private final SerieRepositorio serieRepositorio;

    public List<ResenaDTO> obtenerResenasPorContenido(String titulo, boolean esPelicula) {
        List<Resena> resenas;

        if (esPelicula) {
            Pelicula pelicula = peliculaRepositorio.findByTitulo(titulo);
            if (pelicula == null) throw new RuntimeException("Película no encontrada");
            resenas = resenaRepositorio.findByPelicula(pelicula);
        } else {
            Serie serie = serieRepositorio.findByTitulo(titulo);
            if (serie == null) throw new RuntimeException("Serie no encontrada");
            resenas = resenaRepositorio.findBySerie(serie);
        }

        return resenas.stream()
                .map(resena -> new ResenaDTO(
                        resena.getComentario(),
                        resena.getCalificacion(),
                        resena.getUsuario().getNombre()
                ))

                .toList();
    }

    public Resena crearResenaParaPelicula(String tituloPelicula, Long usuarioId, ResenaDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Pelicula pelicula = peliculaRepositorio.findByTitulo(tituloPelicula);
        if (pelicula == null) {
            throw new RuntimeException("Película no encontrada con título: " + tituloPelicula);
        }

        Resena resena = new Resena();
        resena.setComentario(dto.comentario());
        resena.setCalificacion(dto.calificacion());
        resena.setUsuario(usuario);
        resena.setPelicula(pelicula);
        resena.setSerie(null); // para asegurarse

        return resenaRepositorio.save(resena);
    }

    public Resena crearResenaParaSerie(String tituloSerie, Long usuarioId, ResenaDTO dto) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Serie serie = serieRepositorio.findByTitulo(tituloSerie);
        if (serie == null) {
            throw new RuntimeException("Serie no encontrada con título: " + tituloSerie);
        }

        Resena resena = new Resena();
        resena.setComentario(dto.comentario());
        resena.setCalificacion(dto.calificacion());
        resena.setUsuario(usuario);
        resena.setSerie(serie);
        resena.setPelicula(null); // para asegurarse

        return resenaRepositorio.save(resena);
    }
    // ResenaService.java


}
