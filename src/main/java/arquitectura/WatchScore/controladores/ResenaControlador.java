package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.ResenaDTO;
import arquitectura.WatchScore.persistencia.entidades.Resena;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.persistencia.repositorio.UsuarioRepositorio;
import arquitectura.WatchScore.servicios.ResenaServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ResenaControlador {

    UsuarioRepositorio usuarioRepositorio;
    ResenaServicio resenaServicio;
        @PostMapping("/peliculas/{tituloPelicula}/usuario/{usuarioIdentificacion}")
        public ResponseEntity<?> agregarResenaPelicula(@PathVariable String tituloPelicula,
                                                       @PathVariable Long usuarioIdentificacion,
                                                       @Valid @RequestBody ResenaDTO resenaDTO) {
            try {
                Resena resena = resenaServicio.crearResenaParaPelicula(tituloPelicula, usuarioIdentificacion, resenaDTO);
                return ResponseEntity.ok(resena);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PostMapping("/series/{tituloSerie}/usuario/{usuarioIdentificacion}")
        public ResponseEntity<?> agregarResenaSerie(@PathVariable String tituloSerie,
                                                    @PathVariable Long usuarioIdentificacion,
                                                    @Valid @RequestBody ResenaDTO resenaDTO) {
            try {
                Resena resena = resenaServicio.crearResenaParaSerie(tituloSerie, usuarioIdentificacion, resenaDTO);
                return ResponseEntity.ok(resena);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


    @GetMapping("/peliculas/{titulo}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenasPelicula(@PathVariable String titulo) {
        try {
            return ResponseEntity.ok(resenaServicio.obtenerResenasPorContenido(titulo, true));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/series/{titulo}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenasSerie(@PathVariable String titulo) {
        try {
            return ResponseEntity.ok(resenaServicio.obtenerResenasPorContenido(titulo, false));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    }


