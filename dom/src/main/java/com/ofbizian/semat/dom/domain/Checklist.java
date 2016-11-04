package com.ofbizian.semat.dom.domain;

import javax.jdo.annotations.IdentityType;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.InvokeOn;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
public class Checklist extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Item item;

    @PropertyLayout(hidden = Where.PARENTED_TABLES)
    @javax.jdo.annotations.Column(allowsNull = "false")
    private State state;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean achieved;

    @MemberOrder(sequence = "2")
    @Property(
            editing = Editing.DISABLED,
            editingDisabledReason = "Use actions to change"
    )
    public boolean isAchieved() {
        return achieved;
    }

    @Programmatic
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    public Checklist achieve() {
        setAchieved(true);
        return this;
    }

    public String disableAchieve() {
        return isAchieved() ? "Already achieved" : null;
    }

    @Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    public Checklist unachieve() {
        setAchieved(false);
        return this;
    }

    public String disableUnachieve() {
        return isAchieved() ? null : "Already unachieved";
    }

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int sequence;

    @Title
    @PropertyLayout(named="Checklist Item")
    public String getItemName() {
        return item.getDescription();
    }

    @Programmatic
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Programmatic
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Programmatic
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @PropertyLayout(hidden = Where.ALL_TABLES)
    public String getStateName() {
        return state.getName();
    }
    @Override
    public int compareTo(AbstractPersistable other) {
        return new CompareToBuilder()
                .append(getSequence(), ((Checklist) other).getSequence() )
                .append(getClass().getName(), other.getClass().getName())
                .toComparison();
    }
}
