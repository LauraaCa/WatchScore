package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.servicios.SerieServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/series")
@AllArgsConstructor
@CrossOrigin(origins="*")

public class SerieControlador {
    SerieServicio seriesServicio;

    @GetMapping("/")
    public List<Serie> obtenerTodo(){
        return seriesServicio.listarSeries();
    }

    @GetMapping("/titulo/{titulo}")
    public Serie obtenerXtitulo(@PathVariable String titulo){
        return seriesServicio.obtenerXtitulo(titulo);
    }

    @GetMapping("/id/{id}")
    public Serie obtenerXId(@PathVariable Long id){
        return seriesServicio.obtenerXId(id);
    }


    @PostMapping("/")
    public ResponseEntity<?> crearSerie(@RequestBody SeriesDTO serie) {
        try {
            SeriesDTO creada = seriesServicio.crearSerie(serie);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear serie");
        }
    }


    @PostMapping("/{serieId}/actores/{actorId}")
    public ResponseEntity<Serie> agregarActorASerie(@PathVariable Long serieId, @PathVariable Long actorId) {
        Serie serieActualizada = seriesServicio.agregarActorASerie(serieId, actorId);
        if (serieActualizada != null) {
            return ResponseEntity.ok(serieActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public SeriesDTO actualizarSerie(@PathVariable Long id, @RequestBody SeriesDTO seriesDTO) {
        return seriesServicio.actualizarSerie(id, seriesDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarSerie(@PathVariable Long id) {
        seriesServicio.eliminarSerie(id);
    }
}
