package arquitectura.WatchScore.persistencia.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    @Id
    private Long identificacion;

    @Column(unique = true)
    @Email(message = "El email no es valido")
    private String email;

    private String nombre;
    private String apellido;
    private Long celular;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private String ciudad;
    private String contrasena;
    private LocalDateTime fechaRegistro;
}
