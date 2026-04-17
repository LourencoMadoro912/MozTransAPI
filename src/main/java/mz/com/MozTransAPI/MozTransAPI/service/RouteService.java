package mz.com.MozTransAPI.MozTransAPI.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.RouteDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Route;
import mz.com.MozTransAPI.MozTransAPI.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<RouteDTO> getRoute(Pageable paginacao) {
        return routeRepository.findAll(paginacao)
                .map(p->modelMapper.map(p, RouteDTO.class));
    }

    public RouteDTO getRouteById(Long id) {
        Route route=routeRepository.findById(id).orElseThrow(() ->new EntityNotFoundException());
        return modelMapper.map(route, RouteDTO.class);
    }

    public RouteDTO updateRoute(Long id, RouteDTO dto) {
        Route route=modelMapper.map(dto, Route.class);
        route.setId(id);
        route=routeRepository.save(route);
        return  modelMapper.map(route,RouteDTO.class);
    }

    public String deteteRoute(Long id) {
        routeRepository.deleteById(id);

        return "Eliminado com sucesso!";
    }
}
