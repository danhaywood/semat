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

import com.ofbizian.semat.dom.domain.Alpha;
import com.ofbizian.semat.dom.domain.AlphaState;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.dom.domain.State;
import com.ofbizian.semat.fixture.scenarios.AbstractFixtureScript;

public class StateCreate extends AbstractFixtureScript {

    private String name;
    private String description;
    private Alpha alpha;
    private boolean achieved;
    private int sequence;
    private State state;
    private AlphaState alphaState;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Alpha getAlpha() {
        return alpha;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
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

    public AlphaState getAlphaState() {
        return alphaState;
    }

    public void setAlphaState(AlphaState alphaState) {
        this.alphaState = alphaState;
    }

    @Override
    protected void doExecute(final ExecutionContext ec) {

        String name = checkParam("name", ec, String.class);
        String description = checkParam("description", ec, String.class);
        Alpha alpha = checkParam("alpha", ec, Alpha.class);
        boolean achieved = checkParam("achieved", ec, Boolean.class);
        int sequence = checkParam("sequence", ec, Integer.class);

        this.state = wrap(repository).createState(name, description);
        this.alphaState = wrap(repository).createAlphaState(alpha, state, achieved, sequence);

        // also make available to UI
        ec.addResult(this, state);
        ec.addResult(this, alphaState);
    }

    @javax.inject.Inject
    private ProjectRepository repository;

}
