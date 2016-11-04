package com.ofbizian.semat.dom.domain;

import java.util.SortedSet;
import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject
public class Alpha extends AbstractPersistable {

    private SortedSet<AlphaState> alphaStates;
    private Concern concern;
    private String name;

    public Alpha(String name, Concern concern) {
        this.name = name;
        this.concern = concern;
    }

    public Concern getConcern() {
        return concern;
    }

    @CollectionLayout(defaultView = "table")
    @javax.jdo.annotations.Persistent(mappedBy = "alpha", defaultFetchGroup = "true")
    public SortedSet<AlphaState> getAlphaStates() {
        return alphaStates;
    }

    public void setAlphaStates(SortedSet<AlphaState> alphaStates) {
        this.alphaStates = alphaStates;
    }

    public String iconName() {
        return name;
    }
}
