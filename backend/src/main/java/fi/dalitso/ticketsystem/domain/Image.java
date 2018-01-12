package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.*;

@Entity
public class Image extends AbstractPersistable<Long> {

    private byte[] picture;

    private String caption;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
