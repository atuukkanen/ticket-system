package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.*;
import fi.dalitso.ticketsystem.service.NotifierService;
import fi.dalitso.ticketsystem.service.TicketService;
import fi.dalitso.ticketsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;
    private UserService userService;
    private NotifierService notifier;

    /**
     * Fetches all tickets in the Ticket System.
     * @return A complete list of the tickets in the system.
     */
    @ApiOperation(value = "Get all tickets.")
    @RequestMapping(method = RequestMethod.GET)
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Fetches all tickets having the given status.
     * @param status The status to search for.
     * @return A list of all tickets having the given status.
     */
    @ApiOperation(value = "Get all tickets with the given status.")
    @RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
    public List<Ticket> getAllByStatus(@PathVariable Status status) {
        return ticketService.getAllByStatus(status);
    }

    /**
     * Fetches the ticket with the given id.
     * @param id The id to search for.
     * @return The ticket with the given id, or null.
     */
    @ApiOperation(value = "Get the ticket with given id.")
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
    @ApiOperation(value = "Create a new ticket.")
    @RequestMapping(method = RequestMethod.POST)
    public Ticket createNewTicket(@RequestBody Ticket ticket) {
        User ticketCreator = userService.getAuthenticatedUser();
        ticket.setCreation(new ModificationInfo(ticketCreator));

        ticket.setStatus(Status.OPEN);
        Ticket createdTicket = ticketService.addNewTicket(ticket);
        notifier.notify(Action.TICKET_CREATED, ticket, null, ticketCreator);
        return createdTicket;
    }

    /**
     * Updates the ticket with given id.
     * @param id The id of the ticket the caller wants to update.
     * @param uTicket A ticket object containing new data for the ticket.
     *                Non-null attributes apart from creator, creation time
     *                and comments will be used for replacing the old ones.
     * @return The ticket with given id and updated values.
     */
    @ApiOperation(value = "Edit the ticket with given id.")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket uTicket) {
        User updater = userService.getAuthenticatedUser();
        uTicket.setCreation(new ModificationInfo(updater));

        Ticket updatedTicket = ticketService.update(id, uTicket);
        notifier.notify(Action.TICKET_UPDATED, updatedTicket, null, updater);
        return updatedTicket;
    }

    /**
     * Closes the ticket with given id.
     * @param id The id of the ticket the caller wants to close.
     * @return The closed ticket.
     */
    @ApiOperation(value = "Close the ticket with given id.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Ticket closeTicket(@PathVariable Long id) {
        User closer = userService.getAuthenticatedUser();

        Ticket closedTicket = ticketService.close(id, new ModificationInfo(closer));
        notifier.notify(Action.TICKET_CLOSED, closedTicket, null, closer);
        return closedTicket;
    }

    /**
     * Simply sets the TicketService.
     * @param ticketService The TicketService to set.
     */
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Simply sets the UserService.
     * @param userService The UserService to set.
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Simply sets the NotifierService.
     * @param notifier The NotifierService to set.
     */
    @Autowired
    public void setNotifier(NotifierService notifier) {
        this.notifier = notifier;
    }
}
