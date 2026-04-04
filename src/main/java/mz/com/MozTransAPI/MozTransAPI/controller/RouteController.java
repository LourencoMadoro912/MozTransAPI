package mz.com.MozTransAPI.MozTransAPI.controller;

import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.RouteDTO;
import mz.com.MozTransAPI.MozTransAPI.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public void addRoute(@RequestBody RouteDTO routeDTO) {

        routeService.addRoute(routeDTO);
    }

    @GetMapping
    public List<RouteDTO> getRoute() {

        return routeService.getRoute();
    }

}
