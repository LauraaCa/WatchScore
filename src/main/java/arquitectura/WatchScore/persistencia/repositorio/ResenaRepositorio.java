package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Resena;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepositorio extends JpaRepository<Resena,Long> {
    List<Resena> findBySerie(Serie serie);

    List<Resena> findByPelicula(Pelicula pelicula);
}
