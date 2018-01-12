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
    @JoinColumn(name = "creatorId")
    private ModificationInfo creator;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorId")
    private List<ModificationInfo> editingInfos;

    public Comment() {
        editingInfos = new ArrayList<>();
    }

    public void update(Comment uComment) {
        if (uComment.getCommentText() != null)
            setCommentText(uComment.getCommentText());

        if (uComment.getImages() != null)
            setImages(uComment.getImages());

        editingInfos.add(uComment.getCreator());
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

    public ModificationInfo getCreator() {
        return creator;
    }

    public void setCreator(ModificationInfo creator) {
        this.creator = creator;
    }

    public List<ModificationInfo> getEditingInfos() {
        return editingInfos;
    }

    public void setEditingInfos(List<ModificationInfo> editingInfos) {
        this.editingInfos = editingInfos;
    }
}
