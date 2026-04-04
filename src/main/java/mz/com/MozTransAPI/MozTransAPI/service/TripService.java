package mz.com.MozTransAPI.MozTransAPI.service;

import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TripRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TripResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Route;
import mz.com.MozTransAPI.MozTransAPI.entity.Trip;
import mz.com.MozTransAPI.MozTransAPI.repository.RouteRepository;
import mz.com.MozTransAPI.MozTransAPI.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        dto.setOrigin(trip.getRoute().getOrigin());
        dto.setDestination(trip.getRoute().getDestination());

        return dto;
    }
}
