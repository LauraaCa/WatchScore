package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.ActorDTO;
import arquitectura.WatchScore.dto.DirectorDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Director;
import arquitectura.WatchScore.servicios.DirectorServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/director")
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class DirectorControlador {
    DirectorServicio directorServicio;

    @PostMapping("/")
    public DirectorDTO crear(@RequestBody DirectorDTO director){
        return directorServicio.crear(director);
    }

    @GetMapping("/")
    public List<DirectorDTO> obtener(){
        return directorServicio.obtener();
    }

    @GetMapping("/buscar/")
    public List<Director> buscarXnombre(@RequestParam String nombre){
        return directorServicio.obtenerXnombre(nombre);
    }

}