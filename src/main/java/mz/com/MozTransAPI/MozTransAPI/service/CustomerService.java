package mz.com.MozTransAPI.MozTransAPI.service;

import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.CustomerDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public CustomerDTO addCustumer(CustomerDTO dto) {
        Custumer custumer = modelMapper.map(dto, Custumer.class);
        customerRepository.save(custumer);

         return modelMapper.map(custumer, CustomerDTO.class);
    }

    public List<CustomerDTO> getCustumer() {
       return  customerRepository.findAll()
               .stream()
               .map(p-> modelMapper.map(p,CustomerDTO.class))
               .collect(Collectors.toList());
    }
}




