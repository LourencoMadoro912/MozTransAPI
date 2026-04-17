package mz.com.MozTransAPI.MozTransAPI.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TripRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TripResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.service.TripService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponseDTO> addTrip(@RequestBody  TripRequestDTO tripRequestDTO, UriComponentsBuilder uriBuilder){
        TripResponseDTO tripResponseDTO=  tripService.addTrip(tripRequestDTO);
        URI endereco=uriBuilder.path("/trip/{id}").buildAndExpand(tripRequestDTO.getId()).toUri();

        return ResponseEntity.created(endereco).body(tripResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TripResponseDTO>> getTrip(@PageableDefault(size=10)Pageable paginacao){
        Page<TripResponseDTO> trip=tripService.getTrip(paginacao);
        return ResponseEntity.ok(trip);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponseDTO> getTripById(@PathVariable Long id){
        TripResponseDTO getById= tripService.getTripById(id);
        return  ResponseEntity.ok(getById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponseDTO>  updateTrip(@PathVariable Long id, @RequestBody  TripRequestDTO dto){
        TripResponseDTO actualizada= tripService.updateService(id,dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deteteTrip(@PathVariable @NotNull Long id){
         tripService.deteleTrip(id);
         return ResponseEntity.noContent().build();
    }

}
