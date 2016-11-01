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

import com.ofbizian.semat.dom.domain.Project;
import com.ofbizian.semat.dom.domain.ProjectMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ProjectCreate extends FixtureScript {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Project project;

    public Project getProject() {
        return project;
    }

    @Override
    protected void execute(final ExecutionContext ec) {
        String name = checkParam("name", ec, String.class);
        String code = checkParam("code", ec, String.class);

        this.project = wrap(menu).create(name, code);
        ec.addResult(this, project);
    }

    @javax.inject.Inject
    private ProjectMenu menu;

}
