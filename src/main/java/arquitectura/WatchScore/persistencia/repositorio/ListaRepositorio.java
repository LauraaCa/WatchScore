package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ListaRepositorio extends JpaRepository<Lista,Long> {
    List<Lista> findByUsuarioIdentificacion(Long usuarioIdentificacion);

    Optional<Lista> findByNombre(String listaNombre);

}
