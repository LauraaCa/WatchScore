package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.dto.UsuarioDTO;
import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.persistencia.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.*;
import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor

public class UsuarioServicio {
    UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodos(){
        return usuarioRepositorio.findAll();
    }

    public UsuarioDTO crear(UsuarioDTO usuarioDTO){
        Usuario usuario =Usuario.builder()
                .identificaion(usuarioDTO.identificacion())
                .nombre(usuarioDTO.nombre())
                .apellido(usuarioDTO.apellido())
                .email(usuarioDTO.email())
                .contrasena(BCrypt.hashpw(usuarioDTO.contrasena(), BCrypt.gensalt()))
                .ciudad(usuarioDTO.ciudad())
                .fechaNacimiento(usuarioDTO.fechaNacimiento())
                .fechaRegistro(LocalDateTime.now())
                .build();

        if (usuarioRepositorio.save(usuario).getIdentificaion()>0)
            return usuarioDTO;
        else return null;
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

}