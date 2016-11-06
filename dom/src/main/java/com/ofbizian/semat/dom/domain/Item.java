package com.ofbizian.semat.dom.domain;

import javax.jdo.annotations.IdentityType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.util.ObjectContracts;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject
public class Item extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @PropertyLayout(multiLine=5)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(AbstractPersistable other) {
        return ObjectContracts.compare(this, other, "description", "id");
    }
}
