package mz.com.MozTransAPI.MozTransAPI.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
  private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> addTicked(@RequestBody @Valid TicketRequestDTO dto, UriComponentsBuilder uriBuilder){
       TicketResponseDTO ticketResponseDTO=  ticketService.addTicket(dto);
        URI endereco=uriBuilder.path("/ticket/{id}").buildAndExpand(ticketResponseDTO.getId()).toUri();
        return ResponseEntity.created(endereco).body(ticketResponseDTO);

    }

    @GetMapping
    public ResponseEntity<Page<TicketResponseDTO>> getTickets(@PageableDefault(size=10)Pageable paginacao){
        Page<TicketResponseDTO> ticketResponseDTOS= ticketService.getTickets(paginacao);
        return  ResponseEntity.ok(ticketResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id){
        TicketResponseDTO ticketResponseDTO= ticketService.getTicketById(id);
        return  ResponseEntity.ok(ticketResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicketById(@PathVariable Long id){
         ticketService.deleteTicketById(id);

        return ResponseEntity.noContent().build();
    }


}
