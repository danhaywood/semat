package com.ofbizian.semat.dom.domain;

import java.util.SortedSet;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject
public class State extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @Title
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "false")
    @PropertyLayout(multiLine=5, hidden = Where.ALL_TABLES)
    private String description;

    private SortedSet<Checklist> checklists;

    public String getChecklistSummary() {
        final SortedSet<Checklist> checklists = getChecklists();
        if (checklists == null) {
            return "(0/0)";
        }

        int total = checklists.size();
        int achieved = 0;
        for (Checklist checklist : checklists) {
            if (checklist.isAchieved()) {
                achieved++;
            }
        }
        return "(" + achieved + "/" + total + ")";
    }

    @CollectionLayout(defaultView = "table")
    @javax.jdo.annotations.Persistent(mappedBy = "state", defaultFetchGroup = "true")
    public SortedSet<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(SortedSet<Checklist> checklists) {
        this.checklists = checklists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
