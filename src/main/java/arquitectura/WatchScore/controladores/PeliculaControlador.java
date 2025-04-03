package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.servicios.PeliculaServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/peliculas")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class PeliculaControlador {
    PeliculaServicio peliculasServicio;

    @GetMapping("/")
    public List<Pelicula> obtenerTodo(){
        return peliculasServicio.obtenerTodo();
    }

    @PostMapping("/")
    public PeliculasDTO crear(@RequestBody PeliculasDTO peliculas){
        return peliculasServicio.crear(peliculas);
    }
}
