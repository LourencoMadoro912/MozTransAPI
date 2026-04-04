package mz.com.MozTransAPI.MozTransAPI.service;

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

        dto.setName(ticket.getCustumer().getName());
        dto.setTelefone(ticket.getCustumer().getTelefone());
        dto.setAssento(ticket.getAssento());
        dto.setPrice(ticket.getPrice());
        dto.setOrigin(ticket.getTrip().getRoute().getOrigin());
        dto.setDestination(ticket.getTrip().getRoute().getDestination());

        return dto;
    }


}
