package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId")
    private List<Comment> comments;

    public Ticket() {
        editingInfos = new ArrayList<>();
        images = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
     * Does not affect comments, they should be updated by other ways.
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

    /**
     * Appends a new comment to the ticket's comment chain.
     * @param newComment A new comment to be added.
     */
    public void addComment(Comment newComment) {
        comments.add(newComment);
    }
}
