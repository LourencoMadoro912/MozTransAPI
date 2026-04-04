package mz.com.MozTransAPI.MozTransAPI.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.CustomerDTO;
import mz.com.MozTransAPI.MozTransAPI.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custumer")
@RequiredArgsConstructor
public class CustomerController {

private final CustomerService customerService;

    @PostMapping
    public CustomerDTO addCustumer(@RequestBody @Valid CustomerDTO dto){

       return customerService.addCustumer(dto);
    }

    @GetMapping
    public List<CustomerDTO> getCustumer(){
       return customerService.getCustumer();
    }

}
