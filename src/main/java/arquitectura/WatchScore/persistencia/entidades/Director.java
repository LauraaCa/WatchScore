package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="directores")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String nacionalidad;
    private String genero;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @JsonIgnore
    @OneToMany (mappedBy ="director", fetch = FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy ="director", cascade= CascadeType.ALL)
    private Set<Serie> series = new HashSet<>();


}