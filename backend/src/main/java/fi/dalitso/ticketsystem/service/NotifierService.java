package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.Action;
import fi.dalitso.ticketsystem.domain.Comment;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NotifierService {

    private MailContentBuilder mailContentBuilder;
    private MailService mailService;

    public void notify(Action whatHappened, Ticket ticket,
                       Comment comment,User authenticatedUser) {
        HashMap<String, Object> contentArguments = new HashMap<>();
        String subject = null, to = null;

        switch (whatHappened) {
            case COMMENT_CREATED:
                to = ticket.getCreation().getCreator().getEmail();
                subject = "New comment on ticket #" + ticket.getId() + ": '" + ticket.getHeader() + "'";
                contentArguments.put("username", authenticatedUser.getNickname());
                contentArguments.put("ticketid", ticket.getId());
                contentArguments.put("commentid", comment.getId());
                break;
            case TICKET_CLOSED:
                to = ticket.getCreation().getCreator().getEmail();
                subject = "Your ticket #" + ticket.getId() + ": '" + ticket.getHeader() + "' was closed";
                contentArguments.put("username", authenticatedUser.getNickname());
                contentArguments.put("ticketid", ticket.getId());
                break;
            default:
                return;
        }

        String content = mailContentBuilder.build(whatHappened, contentArguments);
        mailService.sendMessage(to, subject, content);
    }

    @Autowired
    public void setMailContentBuilder(MailContentBuilder mailContentBuilder) {
        this.mailContentBuilder = mailContentBuilder;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
