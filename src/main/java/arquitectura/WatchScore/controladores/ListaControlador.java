package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.Lista;
import arquitectura.WatchScore.servicios.ListaServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/listas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ListaControlador {
    ListaServicio listaServicio;

    @PostMapping("/usuario")
    public ListaDTO crearLista(@RequestBody ListaDTO lista, HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("idusuario");

        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }

        return listaServicio.crearLista(usuarioId, lista);
    }

    @PostMapping("/agregar/{listaNombre}/peliculas/{titulo}")
    public ListaDTO agregarPeliculaALista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.agregarContenidoALista(listaNombre, titulo, true); // esPelicula = true
    }

    @PostMapping("/agregar/{listaNombre}/series/{titulo}")
    public ListaDTO agregarSerieALista(@PathVariable String listaNombre, @PathVariable String titulo) {
        return listaServicio.agregarContenidoALista(listaNombre, titulo, false); // esPelicula = false
    }


    @GetMapping("/mis")
    public List<Lista> obtenerListasPorUsuario(HttpSession session) {
        Long usuarioId = (Long) session.getAttribute("idusuario");

        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no autenticado");
        }

        return listaServicio.obtenerListasPorUsuario(usuarioId);
    }

    @GetMapping("/todo")
    public List<ListaDTO> todo() {
        return listaServicio.todo();
    }
}
