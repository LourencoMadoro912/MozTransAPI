package mz.com.MozTransAPI.MozTransAPI.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.CustomerDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.ResponseCostumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.exception.ResouceNotFoundException;
import mz.com.MozTransAPI.MozTransAPI.repository.CustomerRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private  final PasswordEncoder passwordEncoder;

    public ResponseCostumer toDto(Custumer custumer){
        ResponseCostumer responseCostumer= new ResponseCostumer();

        if (custumer.getTelefone() != null){
            responseCostumer.setTelefone(custumer.getTelefone());
        }else{
            responseCostumer.setTelefone("celular nao encontrado");
        }
        responseCostumer.setName(custumer.getName());

        responseCostumer.setEmail(custumer.getEmail());

        return responseCostumer;
    }

    public ResponseCostumer addCustumer(CustomerDTO dto) {
        Custumer custumer = Custumer.builder()
                .name(dto.getName())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        Custumer save1=customerRepository.save(custumer);

         return toDto(save1);
    }

    public Page<CustomerDTO> getCustumer(Pageable paginacao) {
       return  customerRepository.findAll(paginacao)
               .map(p-> modelMapper.map(p,CustomerDTO.class));
    }

    public CustomerDTO getCustumerId(Long id) {
       Custumer custumer=customerRepository.findById(id).orElseThrow(() ->new ResouceNotFoundException("ciente nao encontrado com id"+id));
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




