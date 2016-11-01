/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package com.ofbizian.semat.fixture.dom;

import com.ofbizian.semat.dom.domain.Checklist;
import com.ofbizian.semat.dom.domain.Item;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.dom.domain.State;
import com.ofbizian.semat.fixture.scenarios.AbstractFixtureScript;

public class ItemCreate extends AbstractFixtureScript {
    private String description;
    private boolean achieved;
    private int sequence;
    private State state;
    private Item item;
    private Checklist checklist;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {
        String description = checkParam("description", ec, String.class);
        State state = checkParam("state", ec, State.class);
        boolean achieved = checkParam("achieved", ec, Boolean.class);
        int sequence = checkParam("sequence", ec, Integer.class);

        this.item = wrap(repository).createItem(description);
        this.checklist = wrap(repository).createChecklist(state, item, achieved, sequence);

        ec.addResult(this, item);
        ec.addResult(this, checklist);
    }

    @javax.inject.Inject
    private ProjectRepository repository;

}
