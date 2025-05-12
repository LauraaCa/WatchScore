package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.UsuarioDTO;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioServicio.obtenerTodos());
    }

    @PostMapping("/")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTO usuario) {
        try {
            UsuarioDTO creado = usuarioServicio.crear(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado al crear usuario");
        }
    }

    @PostMapping("/LogIn")
    public ResponseEntity<?> autenticacion(@RequestBody Usuario usuario, HttpSession session) {
        Usuario autenticado = usuarioServicio.autenticacion(usuario.getEmail(), usuario.getContrasena());
        if (autenticado != null) {
            session.setAttribute("idusuario", autenticado.getIdentificacion());
            return ResponseEntity.ok(autenticado);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}
