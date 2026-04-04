package mz.com.MozTransAPI.MozTransAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
    private String assento;
    private Double price;
    private Long tripId;
    private Long custumerId;
}
