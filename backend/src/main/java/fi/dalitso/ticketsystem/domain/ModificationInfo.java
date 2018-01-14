package fi.dalitso.ticketsystem.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class ModificationInfo extends AbstractPersistable<Long> {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creatorId")
    private User creator;
    private Date creationTime;

    public ModificationInfo() {}

    public ModificationInfo(User creator) {
        this.creator = creator;
        creationTime = new Date();
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}