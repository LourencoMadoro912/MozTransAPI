package mz.com.MozTransAPI.MozTransAPI.service;

import lombok.AllArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.RouteDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Route;
import mz.com.MozTransAPI.MozTransAPI.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteDTO addRoute(RouteDTO routeDTO) {
        Route route= modelMapper.map(routeDTO, Route.class);
        routeRepository.save(route);
        return modelMapper.map(route, RouteDTO.class);
    }

    public List<RouteDTO> getRoute() {
        return routeRepository.findAll()
                .stream()
                .map(p->modelMapper.map(p, RouteDTO.class))
                .collect(Collectors.toList());
    }
}
