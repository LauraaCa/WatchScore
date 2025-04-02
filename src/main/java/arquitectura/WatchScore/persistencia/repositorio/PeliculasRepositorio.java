package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculasRepositorio extends JpaRepository<Peliculas,Long> {
}
