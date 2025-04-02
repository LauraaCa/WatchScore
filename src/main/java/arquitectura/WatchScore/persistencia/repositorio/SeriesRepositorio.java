package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepositorio extends JpaRepository<Series,Long>{
}
