package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.SeriesDTO;
import arquitectura.WatchScore.persistencia.entidades.Series;
import arquitectura.WatchScore.servicios.SeriesServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Series")
@AllArgsConstructor
@CrossOrigin(origins="*")

public class SeriesControlador {
    SeriesServicio seriesServicio;

    @GetMapping("/")
    public List<Series> obtenerTodo(){
        return seriesServicio.obtenerTodo();
    }

    @PostMapping("/")
    public SeriesDTO crear(SeriesDTO series){
        return seriesServicio.crear(series);
    }
}
