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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.isis.applib.annotation.DomainObject;

@javax.jdo.annotations.PersistenceCapable(
        identityType= IdentityType.DATASTORE,
        schema = "simple"
)
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "findByName", language = "JDOQL",
                value = "SELECT "
                        + "FROM Skill "
                        + "WHERE code.indexOf(:code) >= 0 ")
})
@DomainObject(
        autoCompleteRepository = ProjectRepository.class,
        autoCompleteAction = "findSkillsByName")
@XStreamAlias("Skill")
public class Skill extends AbstractPersistable {

    @javax.jdo.annotations.Column(allowsNull = "false")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

    public int compareTo(Skill other) {
        return new CompareToBuilder()
                .append(this.getClass().getName(), other.getClass().getName())
                .append(code, other.code)
                .toComparison();
    }

}
