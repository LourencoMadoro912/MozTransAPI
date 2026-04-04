package mz.com.MozTransAPI.MozTransAPI.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TripRequestDTO {
    private LocalDate date;
    private LocalTime time;
    private Long routeId;
}
