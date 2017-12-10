package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    private TicketService ticketService;

    @RequestMapping("/")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
