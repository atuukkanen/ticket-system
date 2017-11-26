package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    public List<Ticket> getAllTickets() {
        return new ArrayList<Ticket>();
    }
}
