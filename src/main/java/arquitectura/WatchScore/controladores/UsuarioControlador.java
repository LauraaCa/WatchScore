package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.UsuarioDTO;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")

public class UsuarioControlador {
    UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public List<Usuario> obtenerUsuarios(){
        return usuarioServicio.obtenerTodos();
    }

    @PostMapping("/")
    public UsuarioDTO crearUsuario(@Valid @RequestBody UsuarioDTO usuario){
        return usuarioServicio.crear(usuario);
    }


    @PostMapping("/LogIn")
    public ResponseEntity<?> autenticacion(@RequestBody Usuario usuario, HttpSession session) {
        Usuario autenticado = usuarioServicio.autenticacion(usuario.getEmail(), usuario.getContrasena());
        if (autenticado != null) {
            session.setAttribute("idusuario", autenticado.getIdentificacion());
            return ResponseEntity.ok(autenticado);  // Retorna el usuario autenticado como respuesta
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }


}

