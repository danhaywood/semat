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

import com.ofbizian.semat.dom.domain.Concern;
import com.ofbizian.semat.dom.domain.ProjectRepository;
import com.ofbizian.semat.fixture.scenarios.AbstractFixtureScript;

public class ConcernCreate extends AbstractFixtureScript {

    private String name;
    private Concern concern;

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

    @Override
    protected void doExecute(final ExecutionContext ec) {

        String name = checkParam("name", ec, String.class);

        this.concern = wrap(repository).createConcern(name);

        // also make available to UI
        ec.addResult(this, concern);
    }

    @javax.inject.Inject
    private ProjectRepository repository;

}
