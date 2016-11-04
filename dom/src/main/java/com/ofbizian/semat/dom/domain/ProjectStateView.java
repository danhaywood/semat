package com.ofbizian.semat.dom.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Nature;

@DomainObject(nature = Nature.VIEW_MODEL)
public class ProjectStateView implements Comparable <ProjectStateView>{
    private Alpha alpha;
    private String concern;
    private State state;
    private boolean achieved;

    @MemberOrder(sequence="1")
    public Alpha getAlpha() {
        return alpha;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    @MemberOrder(sequence="2")
    public String getConcern() {
        return concern;
    }

    public void setConcern(String concern) {
        this.concern = concern;
    }

    @MemberOrder(sequence="3")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @MemberOrder(sequence="4")
    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Override
    public int compareTo(ProjectStateView other) {
        return new CompareToBuilder()
                .append(getConcern(), other.getConcern())
                .append(getAlpha(), other.getAlpha())
                .toComparison();
    }

    public String iconName() {
        return getAlpha().toString();
    }
}
