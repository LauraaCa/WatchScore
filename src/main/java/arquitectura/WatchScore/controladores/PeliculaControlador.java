package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.servicios.PeliculaServicio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/titulo/{tituloPelicula}")
    public Pelicula obtenerTitulo(@PathVariable String tituloPelicula){
        return peliculasServicio.obtenerTitulo(tituloPelicula);
    }

    @PostMapping("/")
    public PeliculasDTO crear(@RequestBody PeliculasDTO peliculas){
        return peliculasServicio.crear(peliculas);
    }

    @GetMapping("/actor/{nombre}")
    public List<Pelicula> obtenerPeliculasPorActor(@PathVariable String nombre) {
        List<Pelicula> peliculas = peliculasServicio.obtenerPeliculasPorActor(nombre);
        return peliculas;
    }

}
