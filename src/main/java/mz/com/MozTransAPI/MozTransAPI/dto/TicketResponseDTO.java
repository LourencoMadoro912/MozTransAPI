package mz.com.MozTransAPI.MozTransAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketResponseDTO {
    private String name;
    private String telefone;
    private String assento;
    private String origin;
    private String destination;
    private Double price;
}
