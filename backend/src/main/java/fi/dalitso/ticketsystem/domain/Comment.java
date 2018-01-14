package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment extends AbstractPersistable<Long> {

    String commentText;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "imageId")
    private List<Image> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creationId")
    private ModificationInfo creation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "editingId")
    private List<ModificationInfo> editingInfos;

    public Comment() {
        editingInfos = new ArrayList<>();
    }

    public void update(Comment uComment) {
        if (uComment.getCommentText() != null)
            setCommentText(uComment.getCommentText());

        if (uComment.getImages() != null)
            setImages(uComment.getImages());

        editingInfos.add(uComment.getCreation());
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public ModificationInfo getCreation() {
        return creation;
    }

    public void setCreation(ModificationInfo creator) {
        this.creation = creator;
    }

    public List<ModificationInfo> getEditingInfos() {
        return editingInfos;
    }

    public void setEditingInfos(List<ModificationInfo> editingInfos) {
        this.editingInfos = editingInfos;
    }
}
