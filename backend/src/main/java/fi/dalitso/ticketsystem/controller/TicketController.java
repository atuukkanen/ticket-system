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

    /**
     * Fetches all tickets in the Ticket System.
     * @return A complete list of the tickets in the system.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Fetches all tickets having the given status.
     * @param status The status to search for.
     * @return A list of all tickets having the given status.
     */
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Ticket> getAllByStatus(@PathVariable Status status) {
        return ticketService.getAllByStatus(status);
    }

    /**
     * Fetches the ticket with the given id.
     * @param id The id to search for.
     * @return The ticket with the given id, or null.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    /**
     * Creates a new ticket with correct creation info.
     * @param ticket The ticket to be created. Creator and creation time will
     *               be overwritten and may be null.
     * @return The created and saved ticket.
     */
    @RequestMapping(method = RequestMethod.POST)
    public Ticket createNewTicket(@RequestBody Ticket ticket) {
        // TODO: Get real creator.
        ticket.setCreator(new ModificationInfo("CreatorName"));
        return ticketService.addNewTicket(ticket);
    }

    /**
     * Updates the ticket with given id.
     * @param id The id of the ticket the caller wants to update.
     * @param uTicket A ticket object containing new data for the ticket.
     *                Non-null attributes apart from creator, creation time
     *                and comments will be used for replacing the old ones.
     * @return The ticket with given id and updated values.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket uTicket) {
        // TODO: Get real editing user.
        uTicket.setCreator(new ModificationInfo("EditingName"));
        return ticketService.update(id, uTicket);
    }

    /**
     * Simply sets the TicketService.
     * @param ticketService The TicketService to be set.
     */
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

}
