package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.*;
import arquitectura.WatchScore.persistencia.repositorio.ListaRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ListaServicio {

    ListaRepositorio listaRepositorio;
    UsuarioRepositorio usuarioRepositorio;
    PeliculaRepositorio peliculaRepositorio;

    public ListaDTO crearLista(Long usuarioId, ListaDTO listaDTO) {
        Usuario usuario= usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Lista lista = Lista.builder()
                .nombre(listaDTO.nombre())
                .usuario(usuario)
                .build();

        Lista listaGuardada = listaRepositorio.save(lista);

        return new ListaDTO(
                listaGuardada.getNombre(),
                new ArrayList<>(),
                usuario.getIdentificacion()
        );
    }

    public ListaDTO agregarPeliculaALista(Long listaId, Long peliculaId) {
        Lista lista = listaRepositorio.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        Pelicula pelicula = peliculaRepositorio.findById(peliculaId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        lista.getPeliculas().add(pelicula);
        listaRepositorio.save(lista);

        List<String> titulos = lista.getPeliculas().stream()
                .map(Pelicula::getTitulo)
                .toList();

        return new ListaDTO(
                lista.getNombre(),
                titulos,
                lista.getUsuario().getIdentificacion());
    }


    public List<Lista> obtenerListasPorUsuario(Long usuarioId) {
        return listaRepositorio.findByUsuarioIdentificacion(usuarioId);
    }

    public List<ListaDTO> todo(){
        List<Lista> listas = listaRepositorio.findAll();
        return listas.stream()
                .map(lista ->  {List<String> titulosPeliculas = lista.getPeliculas().stream()
                        .map(Pelicula::getTitulo)
                        .toList();

                    return new ListaDTO(
                            lista.getNombre(),
                            titulosPeliculas,
                            lista.getUsuario().getIdentificacion()
                    );})
                .toList();
    }

}