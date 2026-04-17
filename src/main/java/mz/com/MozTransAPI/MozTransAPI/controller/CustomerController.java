package mz.com.MozTransAPI.MozTransAPI.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.CustomerDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.service.CustomerService;
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
@RequestMapping("/custumer")
@RequiredArgsConstructor
public class CustomerController {

private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustumer(@RequestBody @Valid CustomerDTO dto, UriComponentsBuilder uriBuilder){

       CustomerDTO customerDTO= customerService.addCustumer(dto);
        URI enderenco=uriBuilder.path("/custumer/{id}").buildAndExpand(customerDTO.getId()).toUri();

        return ResponseEntity.created(enderenco).body(customerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> getCustumer(@PageableDefault(size=10)Pageable paginacao){
       Page<CustomerDTO> customerDTO= customerService.getCustumer(paginacao);
       return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustumerId(@PathVariable @NotNull Long id){
        CustomerDTO custumer= customerService.getCustumerId(id);
        return ResponseEntity.ok(custumer);
    }

    @PutMapping("/{id}")
    public CustomerDTO actualizar(@PathVariable @NotNull Long id, @RequestBody @Valid CustomerDTO dto){
        return customerService.actualiarCustumer(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable @NonNull Long id){
         customerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
