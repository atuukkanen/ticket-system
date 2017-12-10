package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
