package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.Lista;
import arquitectura.WatchScore.servicios.ListaServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/listas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ListaControlador {
    ListaServicio listaServicio;

    @PostMapping("/crear/{usuarioIdentificacion}")
    public ListaDTO crearLista(@PathVariable Long usuarioIdentificacion,@RequestBody ListaDTO lista) {
        if (usuarioIdentificacion == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }
        return listaServicio.crearLista(usuarioIdentificacion, lista);
    }

    @PostMapping("/agregar/{listaNombre}/peliculas/{titulo}")
    public ListaDTO agregarPeliculaALista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.agregarContenidoALista(listaNombre, titulo, true); // esPelicula = true
    }

    @PostMapping("/agregar/{listaNombre}/series/{titulo}")
    public ListaDTO agregarSerieALista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.agregarContenidoALista(listaNombre, titulo, false); // esPelicula = false
    }


    @GetMapping("/misListas/{usuarioIdentificacion}")
    public List<Lista> obtenerListasPorUsuario(@PathVariable Long usuarioIdentificacion) {
        return listaServicio.obtenerListasPorUsuario(usuarioIdentificacion);
    }

    @GetMapping("/todo")
    public List<ListaDTO> todo() {
        return listaServicio.todo();
    }

    @DeleteMapping("/eliminar/{listaNombre}/peliculas/{titulo}")
    public ListaDTO eliminarPeliculaDeLista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.eliminarContenidoDeLista(listaNombre, titulo, true);
    }

    @DeleteMapping("/eliminar/{listaNombre}/series/{titulo}")
    public ListaDTO eliminarSerieDeLista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.eliminarContenidoDeLista(listaNombre, titulo, false);
    }
    @DeleteMapping("/eliminar/{listaNombre}")
    public ResponseEntity<String> eliminarLista(@PathVariable String listaNombre) {
        try {
            listaServicio.eliminarListaPorNombre(listaNombre);
            return ResponseEntity.ok("Lista eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
