package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.*;
import arquitectura.WatchScore.persistencia.repositorio.ListaRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.PeliculaRepositorio;
import arquitectura.WatchScore.persistencia.repositorio.SerieRepositorio;
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
    SerieRepositorio serieRepositorio;

    public ListaDTO crearLista(Long usuarioId, ListaDTO listaDTO) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Lista lista = Lista.builder()
                .nombre(listaDTO.nombre())
                .usuario(usuario)
                .peliculas(new ArrayList<>())  // Lista vacía de películas
                .series(new ArrayList<>())     // Lista vacía de series
                .build();

        Lista listaGuardada = listaRepositorio.save(lista);

        return new ListaDTO(
                listaGuardada.getNombre(),
                new ArrayList<>(),  // Lista vacía de títulos
                usuario.getIdentificacion()
        );
    }

    public ListaDTO agregarContenidoALista(String listaNombre, String contenidoTitulo, boolean esPelicula) {
        Lista lista = listaRepositorio.findByNombre(listaNombre)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        if (esPelicula) {
            Pelicula pelicula = peliculaRepositorio.findByTitulo(contenidoTitulo);
            if (pelicula == null) {
                throw new RuntimeException("Película no encontrada");
            }
            lista.getPeliculas().add(pelicula);
        } else {
            Serie serie = serieRepositorio.findByTitulo(contenidoTitulo);
            if (serie == null) {
                throw new RuntimeException("Serie no encontrada");
            }
            lista.getSeries().add(serie);
        }

        listaRepositorio.save(lista);

        List<String> titulos = new ArrayList<>();
        lista.getPeliculas().forEach(pelicula -> titulos.add(pelicula.getTitulo()));
        lista.getSeries().forEach(serie -> titulos.add(serie.getTitulo()));

        return new ListaDTO(
                lista.getNombre(),
                titulos,
                lista.getUsuario().getIdentificacion()
        );
    }

    public List<Lista> obtenerListasPorUsuario(Long usuarioId) {
        return listaRepositorio.findByUsuarioIdentificacion(usuarioId);
    }

    public List<ListaDTO> todo() {
        List<Lista> listas = listaRepositorio.findAll();
        return listas.stream()
                .map(lista -> {
                    List<String> titulos = new ArrayList<>();
                    lista.getPeliculas().forEach(pelicula -> titulos.add(pelicula.getTitulo()));
                    lista.getSeries().forEach(serie -> titulos.add(serie.getTitulo()));

                    return new ListaDTO(
                            lista.getNombre(),
                            titulos,
                            lista.getUsuario().getIdentificacion()
                    );
                })
                .toList();
    }

    public ListaDTO eliminarContenidoDeLista(String listaNombre, String contenidoTitulo, boolean esPelicula) {
        Lista lista = listaRepositorio.findByNombre(listaNombre)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        if (esPelicula) {
            Pelicula pelicula = peliculaRepositorio.findByTitulo(contenidoTitulo);
            if (pelicula == null) {
                throw new RuntimeException("Película no encontrada");
            }
            if (!lista.getPeliculas().remove(pelicula)) {
                throw new RuntimeException("Película no encontrada en la lista");
            }
        } else {
            Serie serie = serieRepositorio.findByTitulo(contenidoTitulo);
            if (serie == null) {
                throw new RuntimeException("Serie no encontrada");
            }
            if (!lista.getSeries().remove(serie)) {
                throw new RuntimeException("Serie no encontrada en la lista");
            }
        }

        listaRepositorio.save(lista);

        List<String> titulos = new ArrayList<>();
        lista.getPeliculas().forEach(pelicula -> titulos.add(pelicula.getTitulo()));
        lista.getSeries().forEach(serie -> titulos.add(serie.getTitulo()));

        return new ListaDTO(
                lista.getNombre(),
                titulos,
                lista.getUsuario().getIdentificacion()
        );
    }
    public void eliminarListaPorNombre(String listaNombre) {
        Lista lista = listaRepositorio.findByNombre(listaNombre)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));

        // Limpiar asociaciones (solo la relación, no los contenidos)
        lista.getPeliculas().clear();
        lista.getSeries().clear();

        listaRepositorio.delete(lista);
    }

}
