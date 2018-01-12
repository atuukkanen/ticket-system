package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Ticket extends AbstractPersistable<Long> {

    private String header;
    private String description;
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "imageId")
    private List<Image> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creatorId")
    private ModificationInfo creator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "creatorId")
    private List<ModificationInfo> editingInfos;

    public Ticket() {
        editingInfos = new ArrayList<>();
        images = new ArrayList<>();
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

        if (uTicket.getImages() != null)
            setImages(uTicket.getImages());

        editingInfos.add(uTicket.getCreator());
    }
}
