package mz.com.MozTransAPI.MozTransAPI.controller;

import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.RouteDTO;
import mz.com.MozTransAPI.MozTransAPI.service.RouteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteDTO> addRoute(@RequestBody RouteDTO routeDTO, UriComponentsBuilder uriBuilder) {
       RouteDTO routeDTO1= routeService.addRoute(routeDTO);
        URI endereco=uriBuilder.path("/route/{id}").buildAndExpand(routeDTO1.getId()).toUri();
       return ResponseEntity.created(endereco).body(routeDTO1);
    }

    @GetMapping
    public ResponseEntity<Page<RouteDTO>> getRoute(@PageableDefault(size=10)Pageable paginacao) {

        Page<RouteDTO> routeDTOS= routeService.getRoute(paginacao);
        return ResponseEntity.ok(routeDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long id){
        RouteDTO routeDTO= routeService.getRouteById(id);
        return ResponseEntity.ok(routeDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable Long id,@RequestBody RouteDTO dto){
        RouteDTO routeDTO= routeService.updateRoute(id,dto);
        return ResponseEntity.ok(routeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id){
         routeService.deteteRoute(id);
         return ResponseEntity.noContent().build();
    }

}
