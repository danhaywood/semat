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

import com.ofbizian.semat.dom.domain.ProjectMenu;
import com.ofbizian.semat.dom.domain.Skill;
import com.ofbizian.semat.fixture.scenarios.MyFixtureScript;

public class SkillCreate extends MyFixtureScript {

    //region > name (input)
    private String code;
    /**
     * Name of the object (required)
     */
    public String getCode() {
        return code;
    }

    public SkillCreate setName(final String code) {
        this.code = code;
        return this;
    }
    //endregion


    //region > nurse (output)
    private Skill skill;

    /**
     * The created simple object (output).
     * @return
     */
    public Skill getSkill() {
        return skill;
    }
    //endregion

    @Override
    protected void doExecute(final ExecutionContext ec) {

        String code = checkParam("code", ec, String.class);

        this.skill = wrap(menu).createSkill(code);

        // also make available to UI
        ec.addResult(this, skill);
    }

    @javax.inject.Inject
    private ProjectMenu menu;

}
