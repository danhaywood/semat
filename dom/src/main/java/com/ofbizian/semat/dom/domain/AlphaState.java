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

    @Title(sequence = "1")
    @PropertyLayout(hidden = Where.PARENTED_TABLES)
    public Alpha getAlpha() {
        return alpha;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    @Title(sequence = "2")
    @MemberOrder(sequence = "1")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

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

    //TOOD use mixins
    @MemberOrder(sequence = "3")
    public String getSummary() {
        final SortedSet<Checklist> checklists = getState().getChecklists();
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


    @Override
    public int compareTo(AbstractPersistable other) {
        return new CompareToBuilder()
                .append(getSequence(), ((AlphaState) other).getSequence() )
                .append(getClass().getName(), other.getClass().getName())
                .toComparison();
    }
}
