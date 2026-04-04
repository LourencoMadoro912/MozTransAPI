package mz.com.MozTransAPI.MozTransAPI.controller;

import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TripRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TripResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.service.TripService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public TripResponseDTO addTrip(@RequestBody  TripRequestDTO tripRequestDTO){
        return  tripService.addTrip(tripRequestDTO);
    }
}
