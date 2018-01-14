package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.*;
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

    public List<Ticket> getAllByStatus(Status status) {
        return ticketRepository.findAllByStatus(status);
    }

    @Transactional
    public Ticket update(Long id, Ticket uTicket) {
        Ticket oldTicket = getTicket(id);
        if (oldTicket == null)
            return null;
        oldTicket.update(uTicket);
        return oldTicket;
    }

    @Transactional
    public Ticket addComment(Long ticketId, Comment comment) {
        Ticket oldTicket = getTicket(ticketId);
        if (oldTicket == null)
            return null;
        oldTicket.addComment(comment);
        return oldTicket;
    }

    @Transactional
    public Ticket close(Long ticketId, ModificationInfo closingInfo) {
        Ticket ticketToClose = getTicket(ticketId);
        if (ticketToClose == null)
            return null;
        ticketToClose.close(closingInfo);
        return ticketToClose;
    }

    @Transactional
    public Ticket assign(Long ticketId, User assignee) {
        Ticket ticketToAssign = getTicket(ticketId);
        if (ticketToAssign == null)
            return null;
        ticketToAssign.assign(assignee);
        return ticketToAssign;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
