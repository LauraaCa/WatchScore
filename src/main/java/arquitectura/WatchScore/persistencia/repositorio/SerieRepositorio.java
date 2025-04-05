package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SerieRepositorio extends JpaRepository<Serie,Long>{
    //List<Serie> findByActores(String nombre);
    //Serie findByTituloSerie(String tituloSerie);
}
