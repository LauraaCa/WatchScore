package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepositorio extends JpaRepository<Actor,Long> {
    Optional<Actor> findByNombreActor(String nombreActor);
}
