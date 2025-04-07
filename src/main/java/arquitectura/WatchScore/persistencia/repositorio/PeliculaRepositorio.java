package arquitectura.WatchScore.persistencia.repositorio;

import arquitectura.WatchScore.persistencia.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface PeliculaRepositorio extends JpaRepository<Pelicula,Long> {
    //List<Pelicula> findByActores(String nombreActor);
    //Pelicula findByTituloPelicula(String tituloPelicula);

}
