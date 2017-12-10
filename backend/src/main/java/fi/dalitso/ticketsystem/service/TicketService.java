package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.Status;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findOne(id);
    }

    public Ticket addNewTicket(Ticket t) {
        return ticketRepository.save(t);
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public Ticket update(Long id, Ticket uTicket) {
        Ticket oldTicket = ticketRepository.findOne(id);
        if (oldTicket == null)
            return null;
        oldTicket.update(uTicket);
        return oldTicket;
    }

    public List<Ticket> getAllByStatus(Status status) {
        return ticketRepository.findAllByStatus(status);
    }
}
