package fi.dalitso.ticketsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ticket status is already same as requested.")
public class TicketStatusSameException extends Exception {
}
