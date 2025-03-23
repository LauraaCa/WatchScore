package arquitectura.WatchScore.servicios;

import arquitectura.WatchScore.persistencia.entidades.Usuario;
import arquitectura.WatchScore.persistencia.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;
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

    public Usuario crear(Usuario usuario){
        if (!esEmailValido(usuario.getEmail())) { throw new IllegalArgumentException("El email no es valido"); }
        if (!validarContrasena(usuario.getContrasena())){ throw new IllegalArgumentException("Contraseña Invalida");}
        if (!validaCelular(usuario.getCelular())) { throw new IllegalArgumentException("Celular no valido"); }
        if( !fechaValida(usuario.getFechaNacimiento())) { throw new IllegalArgumentException("Edad no valida"); }
        return usuarioRepositorio.save(usuario);
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