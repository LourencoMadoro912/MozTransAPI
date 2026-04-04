package mz.com.MozTransAPI.MozTransAPI.repository;

import mz.com.MozTransAPI.MozTransAPI.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
