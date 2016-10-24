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

import java.util.Set;
import java.util.TreeSet;
import javax.jdo.annotations.IdentityType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.SemanticsOf;

@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM Project "
                        + "WHERE name.indexOf(:name) >= 0 ")
})
@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@XStreamAlias("Project")
public class Project extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String name;

    @javax.jdo.annotations.Persistent(table = "EmployeeSkills")
    @javax.jdo.annotations.Join()
    private Set<Skill> skills = new TreeSet<>();

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Action
    public Project addSkill(Skill skill) {
        getSkills().add(skill);
        return this;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE)
    public Project removeSkill(Skill skill) {
        getSkills().remove(skill);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
