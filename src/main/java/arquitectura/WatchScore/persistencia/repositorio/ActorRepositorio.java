package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Actor;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface ActorRepositorio extends JpaRepository<Actor,Long> {
    Optional<Actor> findByNombre(String nombre);

    List<Actor> findByNombreContainingIgnoreCase(String nombre);
    List<Actor> findAllByOrderByNombreAsc();
}
