package mz.com.MozTransAPI.MozTransAPI.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.CustomerDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CustomerDTO> getCustumer(Pageable paginacao) {
       return  customerRepository.findAll(paginacao)
               .map(p-> modelMapper.map(p,CustomerDTO.class));
    }

    public CustomerDTO getCustumerId(Long id) {
       Custumer custumer=customerRepository.findById(id).orElseThrow(() ->new EntityNotFoundException());
       return modelMapper.map(custumer,CustomerDTO.class);
    }

    public CustomerDTO actualiarCustumer(Long id, @Valid CustomerDTO dto) {
        Custumer custumer =modelMapper.map(dto,Custumer.class);
        custumer.setId(id);
        custumer=customerRepository.save(custumer);
        return modelMapper.map(custumer,CustomerDTO.class);
    }

    public String eliminar(Long id) {
        customerRepository.deleteById(id);

        return "Eliminado";
    }
}




