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

import java.util.Set;

import com.ofbizian.semat.dom.domain.Alpha;
import com.ofbizian.semat.dom.domain.Concern;
import com.ofbizian.semat.dom.domain.Project;
import com.ofbizian.semat.dom.domain.ProjectMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript;

public class ProjectCreate extends FixtureScript {

    //region > name (input)
    private String name;
    private String code;
    private Set<Alpha> alphas;
    private Set<Concern> concerns;

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

    public Set<Alpha> getAlphas() {
        return alphas;
    }

    public void setAlphas(Set<Alpha> alphas) {
        this.alphas = alphas;
    }


    public Set<Concern> getConcerns() {
        return concerns;
    }

    public void setConcerns(Set<Concern> concerns) {
        this.concerns = concerns;
    }

    //region > nurse (output)
    private Project project;

    /**
     * The created simple object (output).
     * @return
     */
    public Project getProject() {
        return project;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        String name = checkParam("name", ec, String.class);
        String code = checkParam("code", ec, String.class);
        Set<Alpha> alphas = checkParam("alphas", ec, Set.class);

        this.project = wrap(menu).create(name, code);
        this.project.setAlphas(alphas);

        // also make available to UI
        ec.addResult(this, project);
    }

    @javax.inject.Inject
    private ProjectMenu menu;

}
