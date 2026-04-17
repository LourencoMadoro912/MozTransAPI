package mz.com.MozTransAPI.MozTransAPI.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.entity.Custumer;
import mz.com.MozTransAPI.MozTransAPI.entity.Ticket;
import mz.com.MozTransAPI.MozTransAPI.entity.Trip;
import mz.com.MozTransAPI.MozTransAPI.repository.CustomerRepository;
import mz.com.MozTransAPI.MozTransAPI.repository.TicketRepository;
import mz.com.MozTransAPI.MozTransAPI.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final TripRepository tripRepository;
    private final CustomerRepository customerRepository;



    public TicketResponseDTO addTicket(TicketRequestDTO dto) {
        Ticket ticket=new Ticket();

        //sem FK
        ticket.setAssento(dto.getAssento());
        ticket.setPrice(dto.getPrice());

        //com FK

        Trip trip=tripRepository.findById(dto.getTripId()).
                orElseThrow(()-> new RuntimeException());
        Custumer custumer=customerRepository.findById(dto.getCustumerId()).
                orElseThrow(()-> new RuntimeException());

        //colocando no objecto
        ticket.setTrip(trip);
        ticket.setCustumer(custumer);


        Ticket saveTicket=ticketRepository.save(ticket);

        return toDTO(saveTicket);
    }

    private TicketResponseDTO toDTO(Ticket ticket) {

        TicketResponseDTO dto=new TicketResponseDTO();
        if(ticket.getCustumer()!=null) {
            dto.setName(ticket.getCustumer().getName());
            dto.setTelefone(ticket.getCustumer().getTelefone());
        }{
            dto.setName("ciente nao encontrado");
            dto.setTelefone("cliente nao encontrado");
        }
        dto.setAssento(ticket.getAssento());
        dto.setPrice(ticket.getPrice());

        if(ticket.getTrip().getRoute()!=null) {
            dto.setOrigin(ticket.getTrip().getRoute().getOrigin());
            dto.setDestination(ticket.getTrip().getRoute().getDestination());
        }{
            dto.setOrigin("viagem nao entrontrada");
            dto.setDestination("viagem nao encontrada");
        }
        return dto;
    }


    public Page<TicketResponseDTO> getTickets(Pageable paginacao) {
        return ticketRepository.findAll(paginacao)
                .map(this::toDTO);
    }

    public TicketResponseDTO getTicketById(Long id) {
        return toDTO(ticketRepository.findById(id).orElseThrow(()->new EntityNotFoundException()));
    }

    public String deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
        return "Ticket removido com sucesso";
    }
}
