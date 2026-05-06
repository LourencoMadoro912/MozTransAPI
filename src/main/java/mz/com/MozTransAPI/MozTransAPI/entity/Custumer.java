package mz.com.MozTransAPI.MozTransAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name="custumer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Custumer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String telefone;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
