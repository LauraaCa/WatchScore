package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.PeliculasDTO;
import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.servicios.SerieServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<SeriesDTO> crearPelicula(@RequestBody SeriesDTO serie) {
        return ResponseEntity.ok(seriesServicio.crearSerie(serie));
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
}
