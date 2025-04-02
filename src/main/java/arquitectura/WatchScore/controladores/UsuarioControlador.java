package arquitectura.WatchScore.controladores;

import arquitectura.WatchScore.dto.UsuarioDTO;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.servicios.UsuarioServicio;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/Usuarios")
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
    public Usuario autenticacion(@RequestBody Usuario usuario) {
        return usuarioServicio.autenticacion(usuario.getEmail(), usuario.getContrasena());
    }

}

