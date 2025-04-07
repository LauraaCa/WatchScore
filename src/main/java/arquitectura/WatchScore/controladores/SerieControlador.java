package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import arquitectura.WatchScore.servicios.SerieServicio;
import lombok.AllArgsConstructor;
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
        return seriesServicio.obtenerTodo();
    }

    @PostMapping("/")
    public SeriesDTO crear(@RequestBody SeriesDTO series){
        return seriesServicio.crear(series);
    }
}
