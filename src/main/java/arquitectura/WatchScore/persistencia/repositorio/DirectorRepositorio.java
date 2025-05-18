package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DirectorRepositorio extends JpaRepository<Director,Long> {
    Optional<Director> findByNombre(String nombre);
    List<Director> findByNombreContainingIgnoreCase(String palabraClave);
    List<Director> findAllByOrderByNombreAsc();
}