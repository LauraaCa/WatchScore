package arquitectura.WatchScore.controladores;


import arquitectura.WatchScore.dto.ListaDTO;
import arquitectura.WatchScore.persistencia.entidades.Lista;
import arquitectura.WatchScore.servicios.ListaServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class ListaControlador {
    ListaServicio listaServicio;

    @PostMapping("/usuario/{usuarioId}")
    public ListaDTO crearLista(@PathVariable Long usuarioId, @RequestBody ListaDTO lista) {
       return listaServicio.crearLista(usuarioId, lista);
    }


    @PostMapping("/agregar/{listaId}/peliculas/{peliculaId}")
    public ListaDTO agregarPeliculaALista(@PathVariable Long listaId, @PathVariable Long peliculaId) {
       return listaServicio.agregarPeliculaALista(listaId, peliculaId);
    }


    @GetMapping("/mis/{usuarioId}")
    public List<Lista> obtenerListasPorUsuario(@PathVariable Long usuarioId) {
        return listaServicio.obtenerListasPorUsuario(usuarioId);
    }

    @GetMapping("/todo")
    public List<ListaDTO> todo(){
        return listaServicio.todo();
    }

}
