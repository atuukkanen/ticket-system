package fi.dalitso.ticketsystem.domain;


import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Ticket extends AbstractPersistable<Long> {

    private String header;
    private String description;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(Ticket uTicket) {
        if (uTicket.getHeader() != null)
            setHeader(uTicket.getHeader());

        if (uTicket.getDescription() != null)
            setDescription(uTicket.getDescription());
    
        if (uTicket.getStatus() != null)
            setStatus(uTicket.getStatus());
    }
}
