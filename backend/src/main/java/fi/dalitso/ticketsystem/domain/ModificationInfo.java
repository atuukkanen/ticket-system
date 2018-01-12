package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class ModificationInfo extends AbstractPersistable<Long> {
    private String creator;
    private Date creationTime;

    public ModificationInfo() {}

    public ModificationInfo(String creator) {
        this.creator = creator;
        creationTime = new Date();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}