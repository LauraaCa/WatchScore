package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import arquitectura.WatchScore.persistencia.entidades.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PeliculaRepositorio extends JpaRepository<Pelicula,Long> {
    @Query(value = """
            SELECT e FROM Pelicula e
            WHERE UPPER(e.titulo) = UPPER(?1)""")
    //List<Pelicula> findByActores(String nombreActor);
    Pelicula findByTitulo(String titulo);
    List<Pelicula> findAllByOrderByTituloAsc();

    boolean existsByTitulo(String titulo);
}
