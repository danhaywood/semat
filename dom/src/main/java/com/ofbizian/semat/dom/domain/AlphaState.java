package com.ofbizian.semat.dom.domain;

import java.util.SortedSet;
import javax.jdo.annotations.IdentityType;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.*;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
public class AlphaState extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Alpha alpha;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private State state;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private boolean achieved;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int sequence;

    @PropertyLayout(hidden = Where.EVERYWHERE)
    public Alpha getAlpha() {
        return alpha;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    @Title()
    @PropertyLayout(hidden = Where.EVERYWHERE)
    public State getState() {
        return state;
    }

    @PropertyLayout(hidden = Where.ALL_TABLES)
    public String getAlphaName() {
        return alpha.toString();
    }

    @MemberOrder(sequence = "1")
    public String getStateName() {
        return state.getName();
    }

    @MemberOrder(sequence = "3")
    @PropertyLayout(hidden = Where.ALL_TABLES)
    public String getStateDescription() {
        return state.getDescription();
    }

    @Programmatic
    public void setState(State state) {
        this.state = state;
    }

    @MemberOrder(sequence = "4")
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
    public AlphaState achieve() {
        setAchieved(true);
        return this;
    }

    public String disableAchieve() {
        return isAchieved() ? "Already achieved" : null;
    }

    @Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    public AlphaState unachieve() {
        setAchieved(false);
        return this;
    }

    public String disableUnachieve() {
        return isAchieved() ? null : "Already unachieved";
    }

    @Programmatic
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @MemberOrder(sequence = "3")
    public String getChecklistSummary() {
       return getState().getChecklistSummary();
    }

    @CollectionLayout(defaultView = "table")
    public SortedSet<Checklist> getChecklists() {
        return getState().getChecklists();
    }

    @Override
    public int compareTo(AbstractPersistable other) {
        return new CompareToBuilder()
                .append(getSequence(), ((AlphaState) other).getSequence() )
                .append(getClass().getName(), other.getClass().getName())
                .toComparison();
    }
}
