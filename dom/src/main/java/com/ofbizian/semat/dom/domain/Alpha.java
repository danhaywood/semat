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

import java.util.List;
import java.util.SortedSet;
import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Title;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@DomainObject
@XStreamAlias("Alpha")
public class Alpha extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    @Title
    private String name;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private Concern concern;

    private SortedSet<AlphaState> alphaStates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Concern getConcern() {
        return concern;
    }

    public void setConcern(Concern concern) {
        this.concern = concern;
    }

    @javax.jdo.annotations.Persistent(mappedBy = "alpha", defaultFetchGroup = "true")
    public SortedSet<AlphaState> getAlphaStates() {
        return alphaStates;
    }

    public void setAlphaStates(SortedSet<AlphaState> alphaStates) {
        this.alphaStates = alphaStates;
    }
}
