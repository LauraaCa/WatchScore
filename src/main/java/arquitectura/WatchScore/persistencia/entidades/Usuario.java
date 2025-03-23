package arquitectura.WatchScore.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    @Id
    private Long id;

    @Column(unique = true)
    @Email(message = "El email no es valido")
    private String email;

    private String nombre;
    private String apellido;
    private Long celular;
    private LocalDate fechaNacimiento;
    private String ciudad;
    //private String foto;
    private String contrasena;

}
