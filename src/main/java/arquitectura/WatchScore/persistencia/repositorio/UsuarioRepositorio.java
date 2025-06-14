package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

    public interface UsuarioRepositorio extends JpaRepository <Usuario, Long> {
        Usuario findByEmail(String email);

        Usuario findByIdentificacion(Long identificacion);

        Usuario findByCelular(Long celular);
    }


