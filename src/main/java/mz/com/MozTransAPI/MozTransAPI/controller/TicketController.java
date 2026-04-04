package mz.com.MozTransAPI.MozTransAPI.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketRequestDTO;
import mz.com.MozTransAPI.MozTransAPI.dto.TicketResponseDTO;
import mz.com.MozTransAPI.MozTransAPI.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
  private final TicketService ticketService;

    @PostMapping
    public TicketResponseDTO addTicked(@RequestBody @Valid TicketRequestDTO dto ){
       return  ticketService.addTicket(dto);

    }


}
