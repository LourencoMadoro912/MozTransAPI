package mz.com.MozTransAPI.MozTransAPI.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TripRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TripResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Route;
import mz.com.MozTransAPI.MozTransAPI.entity.Trip;
import mz.com.MozTransAPI.MozTransAPI.repository.RouteRepository;
import mz.com.MozTransAPI.MozTransAPI.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class TripService {

    private final TripRepository tripRepository;
    private final  ModelMapper modelMapper;
    private final RouteRepository routeRepository;


    public TripResponseDTO addTrip(TripRequestDTO dto) {



        Trip trip=new Trip();

        //sem fk
        trip.setDate(dto.getDate());
        trip.setTime(dto.getTime());

        //com fk
        Route route=routeRepository.findById(dto.getRouteId()).
                orElseThrow(()->new RuntimeException());

        trip.setRoute(route);

        Trip save=tripRepository.save(trip);

        return toDTO(save);
    }

    private TripResponseDTO toDTO(Trip trip){

        TripResponseDTO dto=new TripResponseDTO();

        dto.setDate(trip.getDate());
        dto.setTime(trip.getTime());

        if(trip.getRoute()!=null) {
            dto.setOrigin(trip.getRoute().getOrigin());
            dto.setDestination(trip.getRoute().getDestination());
        }else{
            dto.setOrigin("rota nao encontrada");
            dto.setDestination("rota nao encontrada");
        }
        return dto;
    }

    public Page<TripResponseDTO> getTrip(Pageable paginacao) {
        return tripRepository.findAll(paginacao)
                .map(this::toDTO);
    }

    public TripResponseDTO getTripById(Long id) {
        return toDTO(tripRepository.findById(id).orElseThrow(()->new EntityNotFoundException()));
    }

    public TripResponseDTO updateService(Long id, TripRequestDTO dto) {
         Trip trip=tripRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
           trip.setDate(dto.getDate());
           trip.setTime(dto.getTime());

           Route route=routeRepository.findById(dto.getRouteId()).
                   orElseThrow(()-> new EntityNotFoundException());

           trip.setRoute(route);

           Trip save=tripRepository.save(trip);
           return  toDTO(save);
    }

    public String deteleTrip(@NotNull Long id) {
        tripRepository.deleteById(id);

        return "Trip removido com sucesso";
    }
}
