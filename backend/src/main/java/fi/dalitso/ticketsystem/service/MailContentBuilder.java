package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

public class MailContentBuilder {

    private TemplateEngine templateEngine;
    private String ticketCreatedTemplateName;
    private String ticketUpdatedTemplateName;
    private String commentCreatedTemplateName;
    private String commentUpdatedTemplateName;
    private String assigneeChangedTemplateName;
    private String ticketClosedName;

    public String build(Action action, Map<String, Object> content) {
        Context context = new Context();
        context.setVariables(content);
        String templateName = getTemplateName(action);
        return templateName != null ?
                templateEngine.process(templateName, context) : null;
    }

    private String getTemplateName(Action action) {
        switch(action) {
            case TICKET_CREATED:
                return ticketCreatedTemplateName;
            case COMMENT_CREATED:
                return commentCreatedTemplateName;
            case TICKET_UPDATED:
                return ticketUpdatedTemplateName;
            case COMMENT_UPDATED:
                return commentUpdatedTemplateName;
            case ASSIGNEE_CHANGED:
                return assigneeChangedTemplateName;
            case TICKET_CLOSED:
                return ticketClosedName;
        }
        return null;
    }

    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void setTicketCreatedTemplateName(String ticketCreatedTemplateName) {
        this.ticketCreatedTemplateName = ticketCreatedTemplateName;
    }

    public void setTicketUpdatedTemplateName(String ticketUpdatedTemplateName) {
        this.ticketUpdatedTemplateName = ticketUpdatedTemplateName;
    }

    public void setCommentCreatedTemplateName(String commentCreatedTemplateName) {
        this.commentCreatedTemplateName = commentCreatedTemplateName;
    }

    public void setCommentUpdatedTemplateName(String commentUpdatedTemplateName) {
        this.commentUpdatedTemplateName = commentUpdatedTemplateName;
    }

    public void setAssigneeChangedTemplateName(String assigneeChangedTemplateName) {
        this.assigneeChangedTemplateName = assigneeChangedTemplateName;
    }

    public void setTicketClosedName(String ticketClosedName) {
        this.ticketClosedName = ticketClosedName;
    }
}
