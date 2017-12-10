package fi.dalitso.ticketsystem.repository;

import fi.dalitso.ticketsystem.domain.Status;
import fi.dalitso.ticketsystem.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findAllByStatus(Status status);
}
