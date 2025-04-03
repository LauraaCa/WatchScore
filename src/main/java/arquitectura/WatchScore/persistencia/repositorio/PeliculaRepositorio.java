package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepositorio extends JpaRepository<Pelicula,Long> {
}
