package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.UsuarioDTO;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.persistencia.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UsuarioServicio {

    UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        if (!esEmailValido(usuarioDTO.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Correo inválido");
        }

        if (!validarContrasena(usuarioDTO.contrasena())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña debe tener entre 8 y 16 caracteres, incluir mayúsculas, minúsculas, un número y un carácter especial");
        }

        if (!validaCelular(usuarioDTO.celular())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de celular inválido");
        }

        if (!fechaValida(usuarioDTO.fechaNacimiento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario debe tener al menos 12 años");
        }

        if (usuarioRepositorio.findByEmail(usuarioDTO.email()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");
        }

        if (usuarioRepositorio.findByIdentificacion(usuarioDTO.identificacion()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La identificación ya está registrada");
        }

        if (usuarioRepositorio.findByCelular(usuarioDTO.celular()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de celular ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .identificacion(usuarioDTO.identificacion())
                .nombre(usuarioDTO.nombre())
                .apellido(usuarioDTO.apellido())
                .celular(usuarioDTO.celular())
                .email(usuarioDTO.email())
                .contrasena(BCrypt.hashpw(usuarioDTO.contrasena(), BCrypt.gensalt()))
                .ciudad(usuarioDTO.ciudad())
                .fechaNacimiento(usuarioDTO.fechaNacimiento())
                .fechaRegistro(LocalDateTime.now())
                .build();

        usuarioRepositorio.save(usuario);
        return usuarioDTO;
    }

    private boolean esEmailValido(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean validarContrasena(String contrasena) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,16}$";
        return contrasena.matches(regex);
    }

    private boolean validaCelular(Long celular) {
        String regex = "^3\\d{9}$";
        return String.valueOf(celular).matches(regex);
    }

    private static boolean fechaValida(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        return Period.between(fechaNacimiento, hoy).getYears() >= 12;
    }

    public Usuario autenticacion(String email, String contrasena) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        if (usuario != null && BCrypt.checkpw(contrasena, usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }
}
