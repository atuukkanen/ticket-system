package fi.dalitso.ticketsystem.domain;


import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket extends AbstractPersistable<Long> {

    private String header;
    private String description;
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creatorId")
    private ModificationInfo creator;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "creatorId")
    private List<ModificationInfo> editingInfos;

    public Ticket() {
        editingInfos = new ArrayList<>();
    }

    public List<ModificationInfo> getEditingInfos() {
        return editingInfos;
    }

    public void setEditingInfos(List<ModificationInfo> editingInfos) {
        this.editingInfos = editingInfos;
    }

    public ModificationInfo getCreator() {
        return creator;
    }

    public void setCreator(ModificationInfo creator) {
        this.creator = creator;
    }

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

    /**
     * Updates ticket's data. Retains the creator and editing history.
     * Adds the creator of the update ticket to editing history.
     * @param uTicket Ticket containing data to use for replacing current
     *                ticket's info.
     */
    public void update(Ticket uTicket) {
        if (uTicket.getHeader() != null)
            setHeader(uTicket.getHeader());

        if (uTicket.getDescription() != null)
            setDescription(uTicket.getDescription());

        if (uTicket.getStatus() != null)
            setStatus(uTicket.getStatus());

        editingInfos.add(uTicket.getCreator());
    }
}
