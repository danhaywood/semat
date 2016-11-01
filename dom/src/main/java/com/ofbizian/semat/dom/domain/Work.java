package com.ofbizian.semat.dom.domain;

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.DomainObject;

/**
 * Created by bibryam on 31/10/2016.
 */
@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject
public class Work extends Alpha {
    private Concern concern = Concern.ENDEAVOR;

    public Concern getConcern() {
        return concern;
    }
}
