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

import javax.jdo.annotations.IdentityType;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.Programmatic;
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

    @javax.jdo.annotations.Column(allowsNull = "false")
    private int sequence;

    @Title(sequence = "1")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Title(sequence = "2")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @Programmatic
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int compareTo(AbstractPersistable other) {
        return new CompareToBuilder()
                .append(getSequence(), ((Checklist) other).getSequence() )
                .append(getClass().getName(), other.getClass().getName())
                .toComparison();
    }
}
