package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.ModificationInfo;
import fi.dalitso.ticketsystem.domain.Status;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Ticket> getAllByStatus(@PathVariable Status status) {
        return ticketService.getAllByStatus(status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Ticket createNewTicket(@RequestBody Ticket ticket) {
        // TODO: Get real creator.
        ticket.setCreator(new ModificationInfo("CreatorName"));
        return ticketService.addNewTicket(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket uTicket) {
        uTicket.setCreator(new ModificationInfo("EditingName"));
        return ticketService.update(id, uTicket);
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
