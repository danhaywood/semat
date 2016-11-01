/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public String getSummary() {
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
