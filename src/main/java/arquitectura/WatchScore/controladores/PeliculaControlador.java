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
    PeliculaServicio peliculaServicio;

    @PostMapping("/")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        return ResponseEntity.ok(peliculaServicio.crearPelicula(pelicula));
    }

    @GetMapping("/")
    public ResponseEntity<List<Pelicula>> listarPeliculas() {
        return ResponseEntity.ok(peliculaServicio.listarPeliculas());
    }

    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<Pelicula> agregarActorAPelicula(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        Pelicula peliculaActualizada = peliculaServicio.agregarActorAPelicula(peliculaId, actorId);
        if (peliculaActualizada != null) {
            return ResponseEntity.ok(peliculaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
