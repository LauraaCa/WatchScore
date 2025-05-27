package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.ActorDTO;
import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.servicios.ActorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actores")
@AllArgsConstructor
@CrossOrigin(origins = "*")

public class ActorControlador {
    ActorServicio actorServicio;

    @PostMapping("/")
    public ActorDTO crear(@RequestBody ActorDTO actor){
        return actorServicio.crear(actor);
    }

    @GetMapping("/")
    public List<ActorDTO> obtener(){
        return actorServicio.obtener();
    }

    @GetMapping("/buscar/")
    public List<Actor> buscarXnombre(@RequestParam String nombre){
        return actorServicio.obtenerXnombre(nombre);
    }

}
