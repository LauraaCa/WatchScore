package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepositorio extends JpaRepository<Serie,Long>{
}
