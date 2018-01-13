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

    public String getMailContent(Action action, String userName, String url) {
        HashMap<String, Object> content = new HashMap<>();
        content.put("userName", userName);
        content.put("url", url);
        String templateName = getTemplateName(action);

        return templateName != null ? build(templateName, content) : null;
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
        }
        return null;
    }

    private String build(String templateName, Map<String, Object> content) {
        Context context = new Context();
        context.setVariables(content);
        return templateEngine.process(templateName, context);
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
}
