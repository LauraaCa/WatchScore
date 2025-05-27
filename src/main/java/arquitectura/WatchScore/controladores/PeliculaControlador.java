package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.servicios.PeliculaServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/peliculas")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PeliculaControlador {

    private final PeliculaServicio peliculaServicio;

    @PostMapping("/")
    public ResponseEntity<?> crearPelicula(@RequestBody PeliculasDTO pelicula) {
        try {
            PeliculasDTO creada = peliculaServicio.crearPelicula(pelicula);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear película");
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Pelicula>> listarPeliculas() {
        return ResponseEntity.ok(peliculaServicio.listarPeliculas());
    }

    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<?> agregarActorAPelicula(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        try {
            Pelicula peliculaActualizada = peliculaServicio.agregarActorAPelicula(peliculaId, actorId);
            return ResponseEntity.ok(peliculaActualizada);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar actor");
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> obtenerXtitulo(@PathVariable String titulo) {
        Pelicula pelicula = peliculaServicio.obtenerXtitulo(titulo);
        if (pelicula != null) {
            return ResponseEntity.ok(pelicula);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada");
        }
    }

    @PutMapping("/actualizar/{id}")
    public PeliculasDTO actualizarPelicula(@PathVariable Long id, @RequestBody PeliculasDTO peliculasDTO) {
        return peliculaServicio.actualizarPelicula(id, peliculasDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPelicula(@PathVariable Long id) {
        peliculaServicio.eliminarPelicula(id);
    }

}
