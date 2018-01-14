package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.*;
import fi.dalitso.ticketsystem.exception.TicketNotFoundException;
import fi.dalitso.ticketsystem.exception.TicketStatusSameException;
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
    public Ticket update(Long id, Ticket uTicket)
            throws TicketNotFoundException {
        Ticket oldTicket = getTicket(id);
        if (oldTicket == null)
            throw new TicketNotFoundException();
        oldTicket.update(uTicket);
        return oldTicket;
    }

    @Transactional
    public Ticket open(Long ticketId)
            throws TicketStatusSameException, TicketNotFoundException {
        Ticket ticketToOpen = getTicket(ticketId);
        if (ticketToOpen == null)
            throw new TicketNotFoundException();
        else if (ticketToOpen.getStatus() == Status.OPEN)
            throw new TicketStatusSameException();
        ticketToOpen.open();
        return ticketToOpen;
    }
    
    @Transactional
    public Ticket close(Long ticketId, ModificationInfo closingInfo)
            throws TicketStatusSameException, TicketNotFoundException {
        Ticket ticketToClose = getTicket(ticketId);
        if (ticketToClose == null)
            throw new TicketNotFoundException();
        else if (ticketToClose.getStatus() == Status.CLOSED)
            throw new TicketStatusSameException();
        ticketToClose.close(closingInfo);
        return ticketToClose;
    }

    @Transactional
    public Ticket assign(Long ticketId, User assignee)
            throws TicketNotFoundException {
        Ticket ticketToAssign = getTicket(ticketId);
        if (ticketToAssign == null)
            throw new TicketNotFoundException();
        ticketToAssign.assign(assignee);
        return ticketToAssign;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
