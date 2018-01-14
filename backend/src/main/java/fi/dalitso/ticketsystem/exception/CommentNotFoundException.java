package fi.dalitso.ticketsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No comment found.")
public class CommentNotFoundException extends Exception {
}
