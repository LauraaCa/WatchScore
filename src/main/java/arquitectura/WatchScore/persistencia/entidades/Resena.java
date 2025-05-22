package arquitectura.WatchScore.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="resenas")
@Entity
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private float calificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_identificacion")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = true)
    private Pelicula pelicula;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = true)
    private Serie serie;



}
