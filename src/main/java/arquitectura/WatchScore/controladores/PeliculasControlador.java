package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Peliculas;
import arquitectura.WatchScore.servicios.PeliculasServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Peliculas")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class PeliculasControlador {
    PeliculasServicio peliculasServicio;

    @GetMapping("/")
    public List<Peliculas> obtenerTodo(){
        return peliculasServicio.obtenerTodo();
    }

    @PostMapping("/")
    public PeliculasDTO crear(@RequestBody PeliculasDTO peliculas){
        return peliculasServicio.crear(peliculas);
    }
}
