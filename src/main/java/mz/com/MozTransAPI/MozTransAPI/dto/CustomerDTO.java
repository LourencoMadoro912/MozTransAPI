package mz.com.MozTransAPI.MozTransAPI.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    @NotBlank
    private String name;
    private String telefone;
}
